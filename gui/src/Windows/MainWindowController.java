package Windows;

import Facade.IDataEntityPresenter;
import Planning.Planning;
import Timeregistration.Timeregistration;
import com.calendarfx.model.*;

import com.calendarfx.view.CalendarView;
import com.calendarfx.view.DateControl;
import Data.*;
import Facade.AppFacade;
import com.calendarfx.view.popover.EntryPopOverContentPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import Projects.Project;
import Projects.ProjectTask;

import java.net.URL;
import java.time.*;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static javafx.collections.ObservableList.*;

import Calendar.EntryPopup;
// calendar.CalendarView;

public class MainWindowController extends Controller implements IDataEntityPresenter
{
   @FXML
   private CalendarView calendarView;

    @FXML
    private TreeView<DataEntity> taskTreeView;

    @FXML
    private ListView<Employee> employeeView;

    private TreeItem<DataEntity> taskTreeList;
    private boolean updatingCalendar;
    private ArrayList<Project> availableProjects;

    public MainWindowController()
    {
        super("MainWindow.fxml", "SI-Planner 0.1");
        availableProjects = new ArrayList<>();

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
            DateControl control = param.getDateControl();
            ZonedDateTime time = param.getZonedDateTime();
            ProjectTask task = (ProjectTask)taskTreeView.getSelectionModel().getSelectedItem().getValue();

            AppFacade.appFacade.createPlanning(time.toLocalDateTime(),time.toLocalDateTime().plusHours(1),
                    task,employee);
/*
            Entry<DataEntity> entry = new Entry<>(String.format("%s - %s",task.getProject().getName(),task.getName()));

            entry.setMinimumDuration(Duration.of(30,MINUTES));
            entry.changeStartDate(time.toLocalDate(),true);
            entry.changeStartTime(time.toLocalTime(),true);
            entry.changeEndDate(time.toLocalDate(),false);
            entry.changeEndTime(time.toLocalTime().plusHours(1),false);

            //entry.getStyleClass().add("style2");

            Planning planning = new Planning(AppFacade.appFacade.getDataSource().planningDao(),0,false,entry.getStartAsLocalDateTime(),entry.getEndAsLocalDateTime()
            ,task,employee);
             entry.setUserObject(planning);

            for(Calendar calendar : calendarView.getCalendars())
            {
                if(calendar.getShortName().equals(planning.getProjectTask().getProject().getShortName()) &&
                        calendar.getName().equals(planning.getProjectTask().getProject().getName()))
                {
                    calendar.addEntry(entry);
                    AppFacade.appFacade.getDataSource().planningDao().insertPlanning(planning);
                }

            }*/

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
                        setText(null);
                    } else {
                        setText(item.getName());
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
        for(CalendarSource calendarSource : calendarView.getCalendarSources())
        {
            if (calendarSource.getName().equals(project.getCustomer().getName()))
            {
                for(Calendar calendar : calendarSource.getCalendars())
                {
                    if(calendar.getName().equals(project.getName())
                    && calendar.getShortName().equals(project.getShortName()))
                        return calendar;
                }

            }
        }
        return null;
    }

    @Override
    public void showDataEntity(DataEntity dataEntity)
    {
        updatingCalendar = true;
        int selEmp=-1;
        if(employeeView.getSelectionModel().getSelectedItem() != null) selEmp = employeeView.getSelectionModel().getSelectedItem().getId();

        if(dataEntity instanceof Employee)
        {
            employeeView.getItems().add((Employee)dataEntity);
            if(selEmp == dataEntity.getId()) employeeView.getSelectionModel().select((Employee)dataEntity);
            if(selEmp == -1 && dataEntity == AppFacade.appFacade.getLoggedinEmployee())
                employeeView.getSelectionModel().select((Employee)dataEntity);
        }


        if(dataEntity instanceof Customer)
        {
            TreeItem<DataEntity> customerItem = new TreeItem<>(dataEntity);
            customerItem.setExpanded(true);
            taskTreeList.getChildren().add(customerItem);

            CalendarSource calendarSource = new CalendarSource(((Customer) dataEntity).getName());
            calendarView.getCalendarSources().add(calendarSource);
        }

        if(dataEntity instanceof Project)
        {
            // search for customer tree item
            TreeItem<DataEntity> treeItem = searchTreeItem(((Project)dataEntity).getCustomer(),taskTreeList.getChildren());
            TreeItem<DataEntity> projectItem = new TreeItem<>(dataEntity);
            projectItem.setExpanded(true);
            treeItem.getChildren().add(projectItem);


            // search for calendar source
            for(CalendarSource calendarSource : calendarView.getCalendarSources())
            {
                if(calendarSource.getName().equals(((Project) dataEntity).getCustomer().getName()))
                {
                    Calendar calendar = new Calendar(((Project) dataEntity).getName());
                    calendar.setStyle(ProjectColor.fromId(((Project) dataEntity).getColor()).getType());
                    calendar.setShortName(((Project) dataEntity).getShortName());
                    calendarSource.getCalendars().add(calendar);
                    calendar.addEventHandler(new EventHandler<CalendarEvent>()
                    {
                        @Override
                        public void handle(CalendarEvent event)
                        {
                            if(updatingCalendar) return;
                            // delete event
                            if(event.getEventType() == CalendarEvent.ENTRY_CALENDAR_CHANGED &&
                                    event.getCalendar() == null && event.getEntry() != null)
                            {
                                if(event.getEntry().getUserObject() instanceof Planning)
                                    if(
                                            AppFacade.appFacade.getDataSource().planningDao().deletePlanning(
                                                    (Planning)event.getEntry().getUserObject()) == DaoResult.OP_OK
                                    )
                                        return;
                                if(event.getEntry().getUserObject() instanceof Timeregistration)
                                    if(
                                            AppFacade.appFacade.getDataSource().timeregistrationDao().deleteTimeregistration(
                                                    (Timeregistration) event.getEntry().getUserObject()) == DaoResult.OP_OK
                                    )
                                        return;
                            }
                            // change project calendar
                            if(event.getEventType() == CalendarEvent.ENTRY_CALENDAR_CHANGED && event.getOldCalendar() != null)
                            {
                                updatingCalendar = true;

                                for(Project proj : availableProjects)
                                {
                                    if (proj.getName().equals(event.getCalendar().getName())
                                            && proj.getShortName().equals(event.getCalendar().getShortName()))
                                    {
                                        if(event.getEntry().getUserObject() instanceof Planning)
                                            ((Planning) event.getEntry().getUserObject()).setProjectTask(proj.getProjectTasks().iterator().next()); else
                                            ((Timeregistration) event.getEntry().getUserObject()).setProjectTask(proj.getProjectTasks().iterator().next());

                                        event.getEntry().setTitle(String.format("%s - %s",
                                                proj.getName(),
                                                proj.getProjectTasks().iterator().next().getName()
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
                            if(event.getEventType().getSuperType() == CalendarEvent.ENTRY_CHANGED)
                            {
                                if(event.getEntry().getUserObject() instanceof Planning)
                                {
                                    ((Planning)event.getEntry().getUserObject()).setStart(event.getEntry().getStartAsLocalDateTime());
                                    ((Planning)event.getEntry().getUserObject()).setEnd(event.getEntry().getEndAsLocalDateTime());
                                } else {
                                    ((Timeregistration)event.getEntry().getUserObject()).setStart(event.getEntry().getStartAsLocalDateTime());
                                    ((Timeregistration)event.getEntry().getUserObject()).setEnd(event.getEntry().getEndAsLocalDateTime());
                                }
                            }
                        }
                    });
                }
            }
        }

        if(dataEntity instanceof ProjectTask)
        {
            // search project list item
            searchTreeItem(((ProjectTask) dataEntity).getProject(),taskTreeList.getChildren()).getChildren().add(
                    new TreeItem<>(dataEntity)
            );


            if(!availableProjects.contains(((ProjectTask) dataEntity).getProject()))
                availableProjects.add(((ProjectTask) dataEntity).getProject()); // make project available if it has a task.

        }

        if(dataEntity instanceof Planning)
        {
            // search calendar
            Calendar calendar = searchCalendar(((Planning) dataEntity).getProjectTask().getProject());
            //if(calendar == null) return; // todo: handle unable to find calendar.

            Entry<DataEntity> entry = new Entry<>(String.format("%s - %s",
                    ((Planning) dataEntity).getProjectTask().getProject().getName(),
                    ((Planning) dataEntity).getProjectTask().getName()));
            entry.setUserObject(dataEntity);
            entry.setMinimumDuration(Duration.of(30,MINUTES));
            entry.changeStartDate(((Planning) dataEntity).getStart().toLocalDate(),true);
            entry.changeStartTime(((Planning) dataEntity).getStart().toLocalTime(),true);
            entry.changeEndDate(((Planning) dataEntity).getEnd().toLocalDate(),false);
            entry.changeEndTime(((Planning) dataEntity).getEnd().toLocalTime(),false);

            calendar.addEntry(entry);
        }

        if(dataEntity instanceof Timeregistration)
        {
            // only show if planning is from selected employee
            Calendar calendar = searchCalendar(((Timeregistration) dataEntity).getProjectTask().getProject());

            Entry<DataEntity> entry = new Entry<>(String.format("%s - %s",
                    ((Timeregistration) dataEntity).getProjectTask().getProject().getName(),
                    ((Timeregistration) dataEntity).getProjectTask().getName()));
            entry.setUserObject(dataEntity);
            entry.setMinimumDuration(Duration.of(30,MINUTES));
            entry.changeStartDate(((Timeregistration) dataEntity).getStart().toLocalDate(),true);
            entry.changeStartTime(((Timeregistration) dataEntity).getStart().toLocalTime(),true);
            entry.changeEndDate(((Timeregistration) dataEntity).getEnd().toLocalDate(),false);
            entry.changeEndTime(((Timeregistration) dataEntity).getEnd().toLocalTime(),false);

            calendar.addEntry(entry);

        }

        updatingCalendar = false;
    }

    @Override
    public void hideDataEntity(DataEntity dataEntity)
    {
        updatingCalendar = true;

        if(dataEntity instanceof Employee)
        {
            employeeView.getItems().remove((Employee)dataEntity);
        }

        if(dataEntity instanceof Customer)
        {
            TreeItem<DataEntity> customerItem = searchTreeItem(dataEntity,taskTreeList.getChildren());
            if(customerItem != null)
                customerItem.getParent().getChildren().removeAll(customerItem);

            calendarView.getCalendarSources().removeIf(
                    calendarSource -> calendarSource.getName().equals(((Customer) dataEntity).getName()));
        }

        if(dataEntity instanceof Project)
        {
            // search for customer tree item
            TreeItem<DataEntity> treeItem = searchTreeItem(dataEntity, taskTreeList.getChildren());
            if(treeItem != null)
                treeItem.getParent().getChildren().removeAll(treeItem);


            Calendar calendar = searchCalendar((Project) dataEntity);
            // search for calendar source
            for(CalendarSource calendarSource : calendarView.getCalendarSources())
            {
                calendarSource.getCalendars().removeIf(sourceCalendar -> sourceCalendar == calendar);
            }
        }

        if(dataEntity instanceof ProjectTask)
        {
            // search for customer tree item
            TreeItem<DataEntity> treeItem = searchTreeItem(dataEntity, taskTreeList.getChildren());
            if(treeItem != null)
                treeItem.getParent().getChildren().removeAll(treeItem);

            if(((ProjectTask) dataEntity).getProject().getProjectTasks().size() < 2)
            {
                availableProjects.remove(((ProjectTask) dataEntity).getProject());
            } // make project unavailable if it will have no more tasks.

        }

        if(dataEntity instanceof Planning || dataEntity instanceof Timeregistration)
        {
            // search calendar
            Calendar calendar;
            if(dataEntity instanceof Planning)
                calendar = searchCalendar((((Planning) dataEntity).getProjectTask().getProject())); else
                calendar = searchCalendar((((Timeregistration) dataEntity).getProjectTask().getProject()));
            //if(calendar == null) throw new RuntimeException("Unable to find calendar to remove time from"); // todo: handle unable to find calendar.

            if(calendar != null) // sometimes the whole calendar is already removed.
            for(List<Entry<?>> entries : calendar.findEntries(LocalDate.of(1900,1,1),LocalDate.of(2400,1,1), ZoneId.systemDefault()).values())
            {
                for(Entry<?> entry : entries)
                {
                    if(entry.getUserObject() == dataEntity)
                    {
                        entry.setUserObject(null);
                        calendar.removeEntry(entry);
                    }
                }
            }

        }

        updatingCalendar = false;
    }
}
