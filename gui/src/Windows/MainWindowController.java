package Windows;

import Facade.ConfigurationSetting;
import Facade.IDataEntityPresenter;
import Facade.IStatusPresenter;
import Planning.Planning;
import Timeregistration.*;
import com.calendarfx.model.*;

import com.calendarfx.model.Calendar;
import com.calendarfx.view.CalendarView;
import Data.*;
import Facade.AppFacade;
import com.dlsc.preferencesfx.PreferencesFx;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import Projects.Project;
import Projects.ProjectTask;

import java.io.File;

import java.io.FileOutputStream;
import java.net.URL;
import java.time.*;
import java.time.temporal.TemporalUnit;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static Calendar.EntryDetails.showNewDataEntityEditor;
import static Facade.AppFacade.APPVERSION;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static javafx.collections.ObservableList.*;

import Calendar.EntryPopup;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.controlsfx.control.StatusBar;
import com.dlsc.preferencesfx.model.*;
import com.dlsc.preferencesfx.util.*;
// calendar.CalendarView;

public class MainWindowController extends Controller implements IDataEntityPresenter, IStatusPresenter
{

    @FXML private CalendarView calendarView;

    @FXML private StatusBar statusBar;

    @FXML
    private TreeView<DataEntity> taskTreeView;

    @FXML
    private ListView<Employee> employeeView;

    private PreferencesFx preferencesFx;

    private TreeItem<DataEntity> taskTreeList;
    private boolean updatingCalendar;
    private ArrayList<Project> availableProjects;

    final private HashMap<Calendar,Project> calendarProjectHashMap;
    final private HashMap<CalendarSource,Customer> calendarSourceCustomerHashMap;
    final private HashMap<DataEntity,IEntityUpdateReceiver> updateReceiverHashMap;
    final private HashMap<ConfigurationSetting<?>,ObservableList<String>> configurationSettingLists;

    public MainWindowController()
    {
        super("MainWindow.fxml", "SI-Planner "+APPVERSION);
        availableProjects = new ArrayList<>();
        calendarProjectHashMap = new HashMap<>();
        calendarSourceCustomerHashMap = new HashMap<>();
        updateReceiverHashMap = new HashMap<>();

        // setup dynamic application preferences
        HashMap<String,ArrayList<String>> stringCategoryGroups = new HashMap<>();
        HashMap<String,ArrayList<Setting>> stringGroupSettings = new HashMap<>();

        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<Group> groups = new ArrayList<>();

        configurationSettingLists = new HashMap<>();

        for(ConfigurationSetting configurationSetting : AppFacade.appFacade.getConfigurationSettings())
        {
            String category;
            if(!stringCategoryGroups.containsKey(configurationSetting.getCategory()))
            {
                stringCategoryGroups.put(configurationSetting.getCategory(),
                        new ArrayList<>());
            }

            if(!stringCategoryGroups.get(configurationSetting.getCategory()).contains(configurationSetting.getGroup()))
            {
                stringCategoryGroups.get(configurationSetting.getCategory()).add(configurationSetting.getGroup());
                stringGroupSettings.put(configurationSetting.getCategory()+configurationSetting.getGroup(),
                        new ArrayList<>());
            }

            Setting setting = null;
            if(configurationSetting.getValue() instanceof Boolean && configurationSetting.getItems() == null)
            {
                SimpleBooleanProperty simpleBooleanProperty = new SimpleBooleanProperty((Boolean)configurationSetting.getValue());
                simpleBooleanProperty.addListener( (obs,oldValue,newValue)->
                        configurationSetting.setValue(newValue));

                ((ConfigurationSetting<Boolean>)configurationSetting).subscribeChanged(
                        (oldValue, newValue) -> {
                            simpleBooleanProperty.set(newValue);
                            if(preferencesFx != null)
                                preferencesFx.saveSettings();
                        });
                setting = Setting.of(configurationSetting.getLabel(),simpleBooleanProperty);
            }
            if(configurationSetting.getValue() instanceof Integer && configurationSetting.getItems() == null)
            {
                SimpleIntegerProperty simpleIntegerProperty = new SimpleIntegerProperty((Integer) configurationSetting.getValue());
                simpleIntegerProperty.addListener( (obs,oldValue,newValue)->
                        configurationSetting.setValue(newValue));
                ((ConfigurationSetting<Integer>)configurationSetting).subscribeChanged(
                        (oldValue, newValue) -> simpleIntegerProperty.set(newValue));
                setting = Setting.of(configurationSetting.getLabel(),simpleIntegerProperty);
            }
            if(configurationSetting.getValue() == null)
            {
                Label label = new Label(configurationSetting.getLabel());
                setting = Setting.of(label);
                configurationSetting.subscribeLabelChanged((oldValue,newValue) -> label.setText((String)newValue));
            }
            if(configurationSetting.getItems() != null)
            {
                ObservableList observableList = FXCollections.observableArrayList(
                        configurationSetting.getItems()
                );
                ObjectProperty<String> objectProperty = new SimpleObjectProperty<>((String)configurationSetting.getItems().iterator().next());
                objectProperty.addListener( (observable, oldValue, newValue) ->
                        configurationSetting.setSelected(newValue));
                configurationSetting.subscribeChanged((oldValue, newValue) ->
                        objectProperty.set(configurationSetting.convertObject(newValue)));

                setting = Setting.of(configurationSetting.getLabel(),observableList,objectProperty);
                configurationSettingLists.put(configurationSetting,observableList);


            }



            stringGroupSettings.get(configurationSetting.getCategory()+configurationSetting.getGroup())
                    .add(setting);

        }

        for(Map.Entry<String,ArrayList<String>> category : stringCategoryGroups.entrySet())
        {
            groups.clear();
            for(String group : category.getValue())
            {
                Setting[] settings = new Setting[0];
                settings = stringGroupSettings.get(category.getKey()+group).toArray(settings);
                groups.add(Group.of(group, settings));
            }
            Group[] g = new Group[0];
            g = groups.toArray(g);
            categories.add(Category.of(category.getKey(),g));
        }
        Category[] c = new Category[0];
        c = (Category[]) categories.toArray(c);

        preferencesFx = PreferencesFx.of(MainWindowController.class, // Save class (will be used to reference saved values of Settings to)
                c
        );
        preferencesFx.instantPersistent(true);
        preferencesFx.dialogIcon(new Image(Controller.class.getResourceAsStream("siplanner-small.png")));


        calendarView.getStylesheets().add(this.getClass().getResource("calendar.css").toExternalForm());
        calendarView.showWeekPage();
        calendarView.getCalendarSources().clear();
        calendarView.setWeekFields(WeekFields.of(DayOfWeek.MONDAY,7));
        calendarView.setShowSourceTrayButton(true);
        calendarView.setShowToolBar(true);
        calendarView.getCalendarSources().clear();



        calendarView.setEntryFactory(param -> {

            if(taskTreeView.getSelectionModel().getSelectedItem() == null ||
                    !(taskTreeView.getSelectionModel().getSelectedItem().getValue() instanceof ProjectTask) ||
            employeeView.getSelectionModel().getSelectedItem()==null) return null;
            Employee employee =  employeeView.getSelectionModel().getSelectedItem();
            ZonedDateTime time = param.getZonedDateTime();
            ProjectTask task = (ProjectTask)taskTreeView.getSelectionModel().getSelectedItem().getValue();

            AppFacade.appFacade.createPlanning(time,time.plusHours(1),
                    task,employee);

            return null;
        });

        calendarView.setEntryDetailsPopOverContentCallback(param -> {
            ObservableList<Project> projects = FXCollections.observableArrayList(availableProjects);
            EntryPopup entryPopup = new EntryPopup(param.getPopOver(), param.getDateControl(), (Entry<DataEntity>)param.getEntry(),
                    projects,calendarView);

            return entryPopup;
        });





        taskTreeView.setCellFactory(param -> new TreeCell<>() {
            @Override
            protected void updateItem(DataEntity entity, boolean empty)
            {
                super.updateItem(entity, empty);
                if (entity == null || empty)
                {
                    setText(null);
                    setGraphic(null);
                } else
                {
                    if (entity instanceof ProjectTask) {
                        if(((ProjectTask) entity).isCompleted())
                        {
                            setStyle("-fx-font-style: italic; ");
                            setTextFill(Color.valueOf("#9e9c98"));
                        } else {
                            setStyle(null);
                            setTextFill(Color.BLACK);
                        }
                        setText(((ProjectTask) entity).getName());
                    }
                    if (entity instanceof Project)
                    {
                        setText(((Project) entity).getName());
                        setGraphic(new Rectangle(16,16, ProjectColor.fromId(((Project) entity).getColor()).getColor()));
                    }
                    if (entity instanceof Customer) setText(((Customer) entity).getName());
                }
            }
        });

        employeeView.setCellFactory(param -> {
            return new ListCell<>() {
                @Override protected void updateItem(Employee item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        //setGraphic(null);
                        Platform.runLater(() ->setText(null));
                    } else {
                        Platform.runLater(() -> setText(item.getName()));
                        //setGraphic(null);
                    }
                }

            };
        });


        taskTreeView.setShowRoot(false);
        taskTreeList = new TreeItem<>();
        taskTreeView.setRoot(taskTreeList);

        // on changing employee
        employeeView.getSelectionModel().selectedItemProperty().addListener(param -> {
            if(updatingCalendar) return;
            AppFacade.appFacade.showCalendar(employeeView.getSelectionModel().getSelectedItem());
        });

        AppFacade.appFacade.subscribeDataEntityPresenter(this);
        AppFacade.appFacade.subscribeStatusPresenter(this);
    }

    @FXML
    private void manageCustomersButtonClicked()
    {
        try
        {
            ManageCustomersController window = new ManageCustomersController(this);
            window.showAndWait();
        } catch (Exception e) {

        }








    }

    @FXML
    private void manageEmployeesButtonClicked()
    {
        try
        {
            ManageEmployeesController window = new ManageEmployeesController(this);
            window.showAndWait();
        } catch (Exception e) {

        }

    }

    @FXML
    private void manageProjectsButtonClicked()
    {
        try
        {
            ManageProjectsController window = new ManageProjectsController(this);
            window.showAndWait();
            AppFacade.appFacade.refreshData();
        } catch (Exception e) {

        }
    }

    @FXML void generateInvoicesButtonClicked()
    {
        List<Invoice> invoices;
        final Window owner = this.getStage();

        Task task = new Task<List<Invoice>>() {

            @Override
            protected List<Invoice> call() throws Exception {

                return AppFacade.appFacade.generateInvoices(calendarView.getDate().withDayOfMonth(1),
                        calendarView.getDate().plusMonths(1).withDayOfMonth(1).minusDays(1));
            }

            @Override
            protected void done()
            {
                super.done();
                try
                {
                    for(Invoice invoice : this.get())
                    {
                        FileChooser fileChooser = new FileChooser();
                        String typeName=null;
                        for(Map.Entry<String,Integer> entry : AppFacade.appFacade.getInvoiceTypes().entrySet())
                        {
                            if(entry.getValue() == invoice.getDocumentTemplate().getType())
                                typeName = entry.getKey();
                        }
                        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(
                                typeName,"*."+AppFacade.appFacade.getInvoiceType(invoice.getDocumentTemplate().getType()).supportedFileExtension()));
                        final byte work[] = invoice.getDocument();
                        if (work == null) continue;

                        Platform.runLater(() -> {
                            File file = fileChooser.showSaveDialog(owner);
                            if(file == null) return;
                            try
                            {
                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                fileOutputStream.write(work);
                                fileOutputStream.close();

                            } catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        });

                    }
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                } catch (ExecutionException e)
                {
                    e.printStackTrace();
                }

            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();



    }

    @Override
    protected void onResize(double height, double width)
    {


    }

    @Override
    protected void onLoaded(URL url, ResourceBundle resourceBundle)
    {

    }

    @Override
    protected void onClosed()
    {
        AppFacade.appFacade.unsubscribeDataEntityPresenter(this);
        AppFacade.appFacade.unSubscribeStatusPresenter(this);
    }

    private TreeItem<DataEntity> searchTreeItem(DataEntity dataEntity,ObservableList<TreeItem<DataEntity>> searchList)
    {
        for(TreeItem<DataEntity> treeItem : searchList)
        {
            if(treeItem.getValue() == dataEntity) return treeItem;
            TreeItem<DataEntity> found = searchTreeItem(dataEntity,treeItem.getChildren());
            if(found != null) return found;
        }
        return null;
    }

    private Calendar searchCalendar(Project project)
    {
        for(Map.Entry<Calendar,Project> calendarProjectEntry : calendarProjectHashMap.entrySet())
        {
            if(calendarProjectEntry.getValue() == project)
                return calendarProjectEntry.getKey();
        }

        return null;
    }

    @Override
    public void showDataEntity(DataEntity dataEntity)
    {
        Platform.runLater( () ->
        {
            updatingCalendar = true;
            int selEmp = -1;
            if (employeeView.getSelectionModel().getSelectedItem() != null)
                selEmp = employeeView.getSelectionModel().getSelectedItem().getId();

            if (dataEntity instanceof Employee)
            {
                employeeView.getItems().add((Employee) dataEntity);
                if (selEmp == dataEntity.getId()) employeeView.getSelectionModel().select((Employee) dataEntity);
                if (selEmp == -1 && dataEntity == AppFacade.appFacade.getLoggedinEmployee())
                    employeeView.getSelectionModel().select((Employee) dataEntity);
            }


            if (dataEntity instanceof Customer)
            {
                TreeItem<DataEntity> customerItem = new TreeItem<>(dataEntity);
                customerItem.setExpanded(true);
                taskTreeList.getChildren().add(customerItem);

                if (!calendarSourceCustomerHashMap.containsValue(dataEntity))
                {
                    CalendarSource calendarSource = new CalendarSource(((Customer) dataEntity).getName());
                    calendarView.getCalendarSources().add(calendarSource);
                    calendarSourceCustomerHashMap.put(calendarSource, (Customer) dataEntity);
                } else
                {
                    for (java.util.Map.Entry<CalendarSource, Customer> entry : calendarSourceCustomerHashMap.entrySet())
                    {
                        if (entry.getValue() == dataEntity)
                            entry.getKey().setName(((Customer) dataEntity).getName());
                    }
                }

            }

            if (dataEntity instanceof Project)
            {
                // search for customer tree item
                TreeItem<DataEntity> treeItem = searchTreeItem(((Project) dataEntity).getCustomer(), taskTreeList.getChildren());
                TreeItem<DataEntity> projectItem = new TreeItem<>(dataEntity);
                projectItem.setExpanded(true);
                treeItem.getChildren().add(projectItem);


                // search for calendar source
                for (Map.Entry<CalendarSource, Customer> calendarSourceCustomerEntry : calendarSourceCustomerHashMap.entrySet())
                {

                    if (calendarSourceCustomerEntry.getValue().equals(((Project) dataEntity).getCustomer()))
                    {
                        Calendar calendar = new Calendar(((Project) dataEntity).getName());
                        calendarProjectHashMap.put(calendar, (Project) dataEntity);

                        // dynamic update properties
                        updateReceiverHashMap.put(dataEntity,
                                dataEntity.addUpdateListener(entity ->
                                {
                                    calendar.setStyle(ProjectColor.fromId(((Project) entity).getColor()).getType());
                                    calendar.setShortName(((Project) entity).getShortName());
                                    calendar.setName(((Project) entity).getName());
                                    calendarView.refreshData();
                                    taskTreeView.refresh();
                                }));

                        calendarSourceCustomerEntry.getKey().getCalendars().add(calendar);
                        calendar.addEventHandler(new EventHandler<CalendarEvent>()
                        {
                            @Override
                            public void handle(CalendarEvent event)
                            {
                                if (updatingCalendar) return;
                                // delete event
                                if (event.getEventType() == CalendarEvent.ENTRY_CALENDAR_CHANGED &&
                                        event.getCalendar() == null && event.getEntry() != null)
                                {
                                    if (event.getEntry().getUserObject() instanceof Planning)
                                        if (
                                                AppFacade.appFacade.getDataSource().planningDao().deletePlanning(
                                                        (Planning) event.getEntry().getUserObject()) == DaoResult.OP_OK
                                        )
                                            return;
                                    if (event.getEntry().getUserObject() instanceof Timeregistration)
                                        if (
                                                AppFacade.appFacade.getDataSource().timeregistrationDao().deleteTimeregistration(
                                                        (Timeregistration) event.getEntry().getUserObject()) == DaoResult.OP_OK
                                        )
                                            return;
                                }
                                // change project calendar
                                if (event.getEventType() == CalendarEvent.ENTRY_CALENDAR_CHANGED && event.getOldCalendar() != null)
                                {
                                    updatingCalendar = true;

                                    for (Project proj : availableProjects)
                                    {
                                        if (proj.getName().equals(event.getCalendar().getName())
                                                && proj.getShortName().equals(event.getCalendar().getShortName()))
                                        {
                                            if (event.getEntry().getUserObject() instanceof Planning)
                                                ((Planning) event.getEntry().getUserObject()).setProjectTask(proj.getProjectTasks().iterator().next());
                                            else
                                                ((Timeregistration) event.getEntry().getUserObject()).setProjectTask(proj.getProjectTasks().iterator().next());

                                            String subtitle = "";

                                            for (Attribute attribute : ((DataEntity) event.getEntry().getUserObject()).getAttributes())
                                            {
                                                if (attribute.getName().equalsIgnoreCase("remarks"))
                                                    subtitle = (String) attribute.getValue();
                                            }

                                            event.getEntry().setTitle(String.format("%s - %s\n%s",
                                                    proj.getName(),
                                                    proj.getProjectTasks().iterator().next().getName(),
                                                    subtitle
                                            ));

                                            event.consume();
                                            updatingCalendar = false;
                                            return; // sucessful change
                                        }
                                    }
                                    event.getEntry().setCalendar(event.getOldCalendar());
                                    event.consume();
                                    updatingCalendar = false;
                                    return; // prevent changing to invalid calendar.
                                }
                                // change event
                                if (event.getEventType().getSuperType() == CalendarEvent.ENTRY_CHANGED)
                                {
                                    if (event.getEntry().getUserObject() instanceof Planning)
                                    {
                                        ((Planning) event.getEntry().getUserObject()).setStart(event.getEntry().getStartAsZonedDateTime());
                                        ((Planning) event.getEntry().getUserObject()).setEnd(event.getEntry().getEndAsZonedDateTime());
                                    } else
                                    {
                                        ((Timeregistration) event.getEntry().getUserObject()).setStart(
                                                event.getEntry().getStartAsZonedDateTime());
                                        ((Timeregistration) event.getEntry().getUserObject()).setEnd(
                                                event.getEntry().getEndAsZonedDateTime());
                                    }
                                }
                            }
                        });
                    }
                }
            }

            if (dataEntity instanceof ProjectTask)
            {
                // search project list item
                searchTreeItem(((ProjectTask) dataEntity).getProject(), taskTreeList.getChildren()).getChildren().add(
                        new TreeItem<>(dataEntity)
                );


                if (!availableProjects.contains(((ProjectTask) dataEntity).getProject()))
                    availableProjects.add(((ProjectTask) dataEntity).getProject()); // make project available if it has a task.

            }

            if (dataEntity instanceof Planning)
            {
                // search calendar
                Calendar calendar = searchCalendar(((Planning) dataEntity).getProjectTask().getProject());
                //if(calendar == null) return; // todo: handle unable to find calendar.
                String subtitle = "";

                for (Attribute attribute : dataEntity.getAttributes())
                {
                    if (attribute.getName().equalsIgnoreCase("remarks"))
                        subtitle = (String) attribute.getValue();
                }

                Entry<DataEntity> entry = new Entry<>(String.format("%s - %s\n%s",
                        ((Planning) dataEntity).getProjectTask().getProject().getName(),
                        ((Planning) dataEntity).getProjectTask().getName(), subtitle));

                dataEntity.addUpdateListener((obs) ->
                {

                    Platform.runLater(() ->
                    {
                        String revisedSubtitle = "";

                        for (Attribute attribute : dataEntity.getAttributes())
                        {
                            if (attribute.getName().equalsIgnoreCase("remarks"))
                                revisedSubtitle = (String) attribute.getValue();
                        }
                        entry.setMinimumDuration(Duration.of(15, MINUTES));
                        entry.setInterval(((Planning) dataEntity).getStart(),
                                ((Planning) dataEntity).getEnd());

                        entry.setTitle(String.format("%s - %s\n%s",
                                ((Planning) dataEntity).getProjectTask().getProject().getName(),
                                ((Planning) dataEntity).getProjectTask().getName(), revisedSubtitle));
                        calendarView.refreshData();
                    });
                });

                entry.setUserObject(dataEntity);
                entry.setMinimumDuration(Duration.of(15, MINUTES));
                entry.setInterval(((Planning) dataEntity).getStart(),
                        ((Planning) dataEntity).getEnd());

                calendar.addEntry(entry);
                if (showNewDataEntityEditor == dataEntity)
                {
                    calendarView.refreshData();
                    calendarView.editEntry(entry);
                    showNewDataEntityEditor = null;
                }
            }

            if (dataEntity instanceof Timeregistration)
            {
                // only show if planning is from selected employee
                Calendar calendar = searchCalendar(((Timeregistration) dataEntity).getProjectTask().getProject());

                String subtitle = "";

                for (Attribute attribute : dataEntity.getAttributes())
                {
                    if (attribute.getName().equalsIgnoreCase("remarks"))
                        subtitle = (String) attribute.getValue();
                }

                Entry<DataEntity> entry = new Entry<>(String.format("%s - %s\n%s",
                        ((Timeregistration) dataEntity).getProjectTask().getProject().getName(),
                        ((Timeregistration) dataEntity).getProjectTask().getName(), subtitle));
                dataEntity.addUpdateListener((obs) ->
                {
                    Platform.runLater(() ->
                    {
                        String revisedSubtitle = "";

                        for (Attribute attribute : dataEntity.getAttributes())
                        {
                            if (attribute.getName().equalsIgnoreCase("remarks"))
                                revisedSubtitle = (String) attribute.getValue();
                        }

                        //entry.changeStartDate(((Timeregistration) dataEntity).getStart().toLocalDate(), false);
                        //entry.changeStartTime(((Timeregistration) dataEntity).getStart().toLocalTime(), false);
                        //entry.changeEndDate(((Timeregistration) dataEntity).getEnd().toLocalDate(), false);
                        //entry.changeEndTime(((Timeregistration) dataEntity).getEnd().toLocalTime(), false);
                        entry.setInterval(((Timeregistration) dataEntity).getStart(),
                                ((Timeregistration) dataEntity).getEnd());

                        entry.setTitle(String.format("%s - %s\n%s",
                                ((Timeregistration) dataEntity).getProjectTask().getProject().getName(),
                                ((Timeregistration) dataEntity).getProjectTask().getName(), revisedSubtitle));
                        calendarView.refreshData();
                    });
                });
                entry.setUserObject(dataEntity);
                entry.setMinimumDuration(Duration.of(15, MINUTES));
                entry.setInterval(((Timeregistration) dataEntity).getStart(),
                        ((Timeregistration) dataEntity).getEnd());
                //entry.changeStartDate(((Timeregistration) dataEntity).getStart().toLocalDate(), true);
                //entry.changeStartTime(((Timeregistration) dataEntity).getStart().toLocalTime(), true);
                //entry.changeEndDate(((Timeregistration) dataEntity).getEnd().toLocalDate(), false);
                //entry.changeEndTime(((Timeregistration) dataEntity).getEnd().toLocalTime(), false);

                calendar.addEntry(entry);
                if (showNewDataEntityEditor == dataEntity)
                {
                    calendarView.refreshData();
                    calendarView.editEntry(entry);
                    showNewDataEntityEditor = null;
                }

            }

            updatingCalendar = false;
        });
    }

    @Override
    public void hideDataEntity(DataEntity dataEntity)
    {
        Platform.runLater( () ->
        {
            updatingCalendar = true;

            if (dataEntity instanceof Employee)
            {
                employeeView.getItems().remove((Employee) dataEntity);
            }

            if (dataEntity instanceof Customer)
            {
                TreeItem<DataEntity> customerItem = searchTreeItem(dataEntity, taskTreeList.getChildren());
                if (customerItem != null)
                    customerItem.getParent().getChildren().removeAll(customerItem);

                for (Map.Entry<CalendarSource, Customer> calendarSourceCustomerEntry : new HashSet<>(calendarSourceCustomerHashMap.entrySet()))
                    if (calendarSourceCustomerEntry.getValue() == dataEntity)
                    {
                        calendarView.getCalendarSources().remove(calendarSourceCustomerEntry.getKey());
                        calendarSourceCustomerHashMap.remove(calendarSourceCustomerEntry.getKey());
                    }
            }

            if (dataEntity instanceof Project)
            {
                // search for customer tree item
                TreeItem<DataEntity> treeItem = searchTreeItem(dataEntity, taskTreeList.getChildren());
                if (treeItem != null)
                    treeItem.getParent().getChildren().removeAll(treeItem);

                dataEntity.removeUpdateListener(updateReceiverHashMap.get(dataEntity));

                Calendar calendar = searchCalendar((Project) dataEntity);
                calendarProjectHashMap.remove(calendar);
                // search for calendar source
                for (CalendarSource calendarSource : calendarView.getCalendarSources())
                    calendarSource.getCalendars().removeIf(sourceCalendar -> sourceCalendar == calendar);

            }

            if (dataEntity instanceof ProjectTask)
            {
                // search for customer tree item
                TreeItem<DataEntity> treeItem = searchTreeItem(dataEntity, taskTreeList.getChildren());
                if (treeItem != null)
                    treeItem.getParent().getChildren().removeAll(treeItem);

                if (((ProjectTask) dataEntity).getProject().getProjectTasks().size() < 2)
                {
                    availableProjects.remove(((ProjectTask) dataEntity).getProject());
                } // make project unavailable if it will have no more tasks.

            }

            if (dataEntity instanceof Planning || dataEntity instanceof Timeregistration)
            {
                // search calendar
                Calendar calendar;
                if (dataEntity instanceof Planning)
                    calendar = searchCalendar((((Planning) dataEntity).getProjectTask().getProject()));
                else
                    calendar = searchCalendar((((Timeregistration) dataEntity).getProjectTask().getProject()));
                //if(calendar == null) throw new RuntimeException("Unable to find calendar to remove time from"); // todo: handle unable to find calendar.

                if (calendar != null) // sometimes the whole calendar is already removed.
                    for (List<Entry<?>> entries : calendar.findEntries(LocalDate.of(1900, 1, 1), LocalDate.of(2400, 1, 1), ZoneId.systemDefault()).values())
                    {
                        for (Entry<?> entry : entries)
                        {
                            if (entry.getUserObject() == dataEntity)
                            {
                                entry.setUserObject(null);
                                calendar.removeEntry(entry);
                            }
                        }
                    }

            }

            updatingCalendar = false;
        });
    }

    @Override
    public void statusTick(String statusMessage, int progress, int total)
    {

        Platform.runLater(() -> {
            statusBar.setText(statusMessage);
            statusBar.setProgress((double)progress / (double)total);
        });

    }

    @FXML
    public void onPreferencesAction(ActionEvent actionEvent)
    {
        for(Map.Entry<ConfigurationSetting<?>,ObservableList<String>> key : configurationSettingLists.entrySet())
        {
            ArrayList<String> curItems = new ArrayList<>(key.getValue());
            for(String item : key.getKey().getItems())
            {
                if(!curItems.contains(item))
                {
                    key.getValue().add(item);
                } else {
                    curItems.remove(item);
                }
            }
            for(String item : curItems)
            {
                if(key.getValue().contains(item))
                    key.getValue().remove(item);
            }
        }
        preferencesFx.discardChanges(); // load changed list values, they are invalidated otherwise.
        preferencesFx.show(true);
    }

    @FXML private void onInboundSyncButtonClick(ActionEvent actionEvent)
    {

        Task task = new Task<>() {

            @Override
            protected Object call() throws Exception {

                AppFacade.appFacade.refreshInbound(1);
                return null;
            }

        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

    }

    @FXML private void onAboutClick(ActionEvent actionEvent)
    {
        new About(this).showAndWait();
    }

}
