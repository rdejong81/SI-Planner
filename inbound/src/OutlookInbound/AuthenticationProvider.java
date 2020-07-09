
package OutlookInbound;

import Facade.AppFacade;
import com.microsoft.aad.msal4j.*;
import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.http.IHttpRequest;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import com.microsoft.graph.requests.extensions.ICalendarCollectionPage;

import java.net.URI;
import java.util.Set;
import java.util.function.Consumer;

public class AuthenticationProvider implements IAuthenticationProvider
{
    private IAuthenticationResult authenticationResult;

    public AuthenticationProvider(IPublicClientApplication publicClientApplication, Set<String> Scopes, Boolean deviceFlow)
    {

        Set<IAccount> accountsInCache = publicClientApplication.getAccounts().join();
        IAccount account = null;

        try {
            if (!accountsInCache.isEmpty())
                account = accountsInCache.iterator().next();

            SilentParameters silentParameters =
                    SilentParameters
                            .builder(Scopes, account)
                            .build();

            // try to acquire token silently. This call will fail since the token cache
            // does not have any data for the user you are trying to acquire a token for
            authenticationResult = publicClientApplication.acquireTokenSilently(silentParameters).join();
            IGraphServiceClient graphClient = GraphServiceClient.builder().authenticationProvider(this).buildClient();

            // test by reading calendars todo: find test that works with all scopes
            ICalendarCollectionPage calendarCollectionPage = graphClient.me().calendars().buildRequest().get();
            if(calendarCollectionPage.getCurrentPage().isEmpty())
                throw new Exception("Missing calendars");

        } catch (Exception e)
        {
            if(deviceFlow)
            {
                DeviceCodeFlowParameters parameters = null;
                try
                {
                    Consumer<DeviceCode> deviceCodeConsumer = (DeviceCode deviceCode) ->
                            AppFacade.appFacade.broadcastStatusUpdate(deviceCode.message(),0,0);

                    parameters =
                            DeviceCodeFlowParameters
                                    .builder(Scopes, deviceCodeConsumer)
                                    .build();

                    authenticationResult = publicClientApplication.acquireToken(parameters).join();
                    IGraphServiceClient graphClient = GraphServiceClient.builder().authenticationProvider(this).buildClient();

                    ICalendarCollectionPage calendarCollectionPage = graphClient.me().calendars().buildRequest().get();
                    if(calendarCollectionPage.getCurrentPage().isEmpty())
                        throw new Exception("Missing calendars");

                } catch (Exception ex)
                {
                    ex.printStackTrace();
                    new Exception("Unable to authenticate");

                }

            } else {
                InteractiveRequestParameters parameters = null;
                try
                {

                SystemBrowserOptions options = SystemBrowserOptions.builder()
                        .htmlMessageSuccess("SI-Planner will now read your calendar, you can close this browser tab.")
                        .htmlMessageError("Please restart SI-Planner to try to authenticate again")
                        .build();

                parameters = InteractiveRequestParameters
                        .builder(new URI("http://localhost"))
                        .scopes(Scopes)
                        .systemBrowserOptions(options)
                        .loginHint(account == null ? null : account.username())
                        .build();

                    authenticationResult = publicClientApplication.acquireToken(parameters).join();
                    IGraphServiceClient graphClient = GraphServiceClient.builder().authenticationProvider(this).buildClient();

                    ICalendarCollectionPage calendarCollectionPage = graphClient.me().calendars().buildRequest().get();
                    if(calendarCollectionPage.getCurrentPage().isEmpty())
                        throw new Exception("Missing calendars");
                } catch (Exception ex)
                {

                }

            }
        }


    }
    @Override
    public void authenticateRequest(IHttpRequest iHttpRequest)
    {
        iHttpRequest.addHeader("Authorization", "Bearer " + authenticationResult.accessToken());
    }
}
