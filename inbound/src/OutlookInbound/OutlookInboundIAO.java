/*
 *    Copyright © 2020 Richard de Jong
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package OutlookInbound;

import Data.*;
import Facade.AppFacade;
import Facade.ConfigurationSetting;
import Facade.IDataEntityPresenter;
import Planning.InboundIAO;
import Planning.Planning;
import Projects.ProjectTask;
import Timeregistration.Timeregistration;
import com.microsoft.aad.msal4j.*;
import com.microsoft.graph.models.extensions.Event;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.options.QueryOption;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import com.microsoft.graph.requests.extensions.ICalendarCollectionPage;
import com.microsoft.graph.requests.extensions.IEventCollectionPage;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalUnit;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;


public class OutlookInboundIAO implements InboundIAO
{
    final private static Set<String> SCOPES = Collections.singleton("https://graph.microsoft.com/calendars.read");
    final private static String CLIENTID = "7a61f5f9-9ff9-47ac-9eba-feaf13ffb9ae";
    final private static String SETTING_DEVICEFLOW = "deviceFlow";
    final private static String SETTING_LOOKUP_BEHIND = "lookBehind";
    final private static String SETTING_LOOKUP_AHEAD = "lookAhead";
    final private static String SETTING_INBOUND_TASK = "inboundTask";
    final private static String SETTING_CLEARCACHE = "clearCache";
    final private static String LABEL_AUTH = "Inbound Outlook synchronisation authenticated to: %s";
    private PublicClientApplication appRegistration;
    final private ConfigurationSetting<String> authinfoLabel;
    final private ConfigurationSetting<Boolean> deviceFlow,clearCache;
    final private ConfigurationSetting<Integer> lookupAhead,lookupBehind;
    final private ConfigurationSetting<ProjectTask> inboundTask;

    private FileTokenPersistence fileTokenPersistence;
    private IGraphServiceClient graphClient;

    public OutlookInboundIAO()
    {
        fileTokenPersistence = new FileTokenPersistence(CLIENTID);

        deviceFlow = new ConfigurationSetting<>(SETTING_DEVICEFLOW,
                "Authenticate on separate device to satisfy MFA",
                "Authentication",
                "Inbound authentication"
                ,false);

        authinfoLabel = new ConfigurationSetting<>("AUTHINFO",
            String.format(LABEL_AUTH,"n/a"),
            "Authentication",
            "Inbound authentication");
        clearCache = new ConfigurationSetting<>(SETTING_CLEARCACHE,
                "Remove authentication cache upon next sync",
                "Authentication",
                "Inbound authentication"
                ,false);



        lookupBehind = new ConfigurationSetting<>(SETTING_LOOKUP_BEHIND,
                "Months to search back for calendar events to synchronize",
                "Inbound connections",
                "Synchronization settings"
                ,2);

        lookupAhead = new ConfigurationSetting<>(SETTING_LOOKUP_AHEAD,
                "Months to search ahead for calendar events to synchronize",
                "Inbound connections",
                "Synchronization settings"
                ,2);
        inboundTask = new ConfigurationSetting<>(SETTING_INBOUND_TASK,
                "New calendar items assigned to this Project Task",
                "Inbound connections",
                "Synchronization settings",
                () -> AppFacade.appFacade.getDataSource().taskDao().findAll(),
                (task) -> String.format("%s - %s",task.getProject().getName(),task.getName()));
    }

    @Override
    public void initialize()
    {
        AppFacade.appFacade.broadcastStatusUpdate("Setup outlook sync", 0, 0);
        try
        {
            appRegistration = PublicClientApplication
                    .builder(CLIENTID)
                    .authority("https://login.microsoftonline.com/common/oauth2/v2.0/authorize")
                    .setTokenCacheAccessAspect(fileTokenPersistence)
                    .build();

            if(!appRegistration.getAccounts().join().isEmpty())
                authinfoLabel.setLabel(String.format(LABEL_AUTH,appRegistration.getAccounts().join().iterator().next().username()));
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void parse()
    {
        if(inboundTask.getValue()==null)
        {
            AppFacade.appFacade.broadcastStatusUpdate("Inbound task not configured, check preferences.",0,0);
            return;
        }
        try
        {
            AppFacade.appFacade.broadcastStatusUpdate("Starting outlook authentication...",0,0);

            if(clearCache.getValue())
            {
                 if(!appRegistration.getAccounts().join().isEmpty())
                    for(IAccount account : appRegistration.getAccounts().join())
                    {
                        appRegistration.removeAccount(account);
                    }
                fileTokenPersistence.clearCache();
                clearCache.setValue(false);
            }

            AuthenticationProvider authenticationProvider = new AuthenticationProvider(appRegistration,SCOPES,deviceFlow.getValue());
            graphClient = GraphServiceClient.builder().authenticationProvider(authenticationProvider).buildClient();
            if(!appRegistration.getAccounts().join().isEmpty())
            {
                authinfoLabel.setLabel(String.format(LABEL_AUTH,appRegistration.getAccounts().join().iterator().next().username()));
            } else
            {
                AppFacade.appFacade.broadcastStatusUpdate("Inbound outlook authentication cancelled or failed.",0,0);
                authinfoLabel.setLabel(String.format(LABEL_AUTH,"n/a"));
                return;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

        ZonedDateTime lookupStart = ZonedDateTime.now().minusMonths(lookupBehind.getValue());
        ZonedDateTime lookupEnd = ZonedDateTime.now().plusMonths(lookupAhead.getValue());

        LinkedList<Option> requestOptions = new LinkedList<>();
        requestOptions.add(new QueryOption("startDateTime", lookupStart.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
        requestOptions.add(new QueryOption("endDateTime", lookupEnd.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
        requestOptions.add(new QueryOption("$top","50"));
        String idStr = graphClient.me().calendar().buildRequest().get().name;
        String startString = lookupStart.toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        String stopString = lookupEnd.toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));

        AppFacade.appFacade.broadcastStatusUpdate("Retrieving data from outlook",0,100);
        IEventCollectionPage calendarView = graphClient.me().calendarView()
                .buildRequest( requestOptions )
                .get();


        List<Event> currentPage = calendarView.getCurrentPage();
        int existing = 0, added = 0;

        while (currentPage != null)
        {
            int size = currentPage.size();
            int position = 0;

            for (Event event : currentPage)
            {
                position++;
                ZonedDateTime startz = LocalDateTime.parse(event.start.dateTime,DateTimeFormatter.ISO_LOCAL_DATE_TIME).atZone(ZoneId.of(event.start.timeZone));
                ZonedDateTime endz = LocalDateTime.parse(event.end.dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME).atZone(ZoneId.of(event.end.timeZone));;
                AppFacade.appFacade.broadcastStatusUpdate(
                        String.format("Updating Calender events from outlook calendar (%s) begin date: %s end date: %s (existing found %d, newly added %d)",
                                idStr,
                                startString,
                                stopString,
                                existing,
                                added)
                        ,position,size);

                boolean found=false;
                for(Timeregistration timeregistration : AppFacade.appFacade.getDataSource().timeregistrationDao().findAll())
                {
                    if(timeregistration.getSynckey().equals(event.iCalUId))
                    {
                        if(timeregistration.isSynced())
                        {
                            if(event.isCancelled)
                            {
                                AppFacade.appFacade.broadcastHideDataEntity(timeregistration);
                                AppFacade.appFacade.getDataSource().timeregistrationDao().deleteTimeregistration(timeregistration);
                                break;
                            }
                            timeregistration.setStart(startz);
                            timeregistration.setEnd(endz);
                            syncEventAttributes(event,timeregistration);
                        }
                        found = true;
                        break;
                    }
                }

                for(Planning planning : AppFacade.appFacade.getDataSource().planningDao().findAll())
                {
                    if(planning.getSynckey().equals(event.iCalUId))
                    {
                        if(planning.isSynced())
                        {
                            if(event.isCancelled)
                            {
                                AppFacade.appFacade.broadcastHideDataEntity(planning);
                                AppFacade.appFacade.getDataSource().planningDao().deletePlanning(planning);
                                break;
                            }
                            planning.setStart(startz);
                            planning.setEnd(endz);
                            syncEventAttributes(event,planning);
                        }
                        found = true;
                        break;
                    }
                }

                if(found)
                {
                    existing++;
                    continue;
                }
                if(event.isCancelled) continue;
                added++;


                Planning planning = new Planning(AppFacade.appFacade.getDataSource().planningDao(),0,true,startz,endz
                        ,inboundTask.getValue(),AppFacade.appFacade.getLoggedinEmployee(),event.iCalUId);

                AppFacade.appFacade.getDataSource().planningDao().insertPlanning(planning);
                AppFacade.appFacade.broadcastShowDataEntity(planning);
                // Check properties
                syncEventAttributes(event,planning);

            }

            if(calendarView.getNextPage() != null)
                calendarView = calendarView.getNextPage() != null ? calendarView.getNextPage().buildRequest().get() : null;
            currentPage = calendarView.getNextPage() != null ? calendarView.getCurrentPage() : null;
        }

        AppFacade.appFacade.refreshData();


    }

    void syncEventAttributes(Event event, DataEntity dataEntity)
    {
        boolean attributeFound=false;
        for(Attribute attribute : inboundTask.getValue().getProject().getCustomer().getAttributeDefinitions())
        {
            if(attribute.getName().equalsIgnoreCase("remarks") && attribute.getEntityType().equals(EntityType.fromEntity(dataEntity)))
                attributeFound=true;
        }
        if(!attributeFound)
            AppFacade.appFacade.createAttributeDefinition(
                    inboundTask.getValue().getProject().getCustomer(),
                    "Remarks",
                    AttributeType.STRING,
                    EntityType.fromEntity(dataEntity)
            );

        for(Attribute attribute : dataEntity.getAttributes())
        {
            if(attribute.getName().equalsIgnoreCase("remarks"))
                attribute.setValue(event.subject);
        }

    }
}
