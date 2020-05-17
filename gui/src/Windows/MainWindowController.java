package Windows;

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
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static javafx.collections.ObservableList.*;

import Calendar.EntryPopup;
// calendar.CalendarView;

public class MainWindowController extends Controller
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




        calendarView.setEntryFactory(param -> {

            if(taskTreeView.getSelectionModel().getSelectedItem() == null ||
                    !(taskTreeView.getSelectionModel().getSelectedItem().getValue() instanceof ProjectTask) ||
            employeeView.getSelectionModel().getSelectedItem()==null) return null;
            Employee employee =  employeeView.getSelectionModel().getSelectedItem();
            DateControl control = param.getDateControl();
            ZonedDateTime time = param.getZonedDateTime();
            ProjectTask task = (ProjectTask)taskTreeView.getSelectionModel().getSelectedItem().getValue();

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

            }

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

        // on changing employee
        employeeView.getSelectionModel().selectedItemProperty().addListener(param -> {
            if(updatingCalendar) return;
            refreshData();
        });


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
            refreshData();
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

    }

    @Override
    public void refreshData()
    {
        int selEmp=-1;
        updatingCalendar = true;
        if(employeeView.getSelectionModel().getSelectedItem() != null) selEmp = employeeView.getSelectionModel().getSelectedItem().getId();
        employeeView.getItems().clear();

        for (Employee employee : AppFacade.appFacade.getEmployeeList().getEmployees())
        {
            employeeView.getItems().add(employee);

            if(selEmp == employee.getId()) employeeView.getSelectionModel().select(employee);
            if(selEmp == -1 && employee.getSqlLoginName().equals(AppFacade.appFacade.getLoginController().getUserName()))
                employeeView.getSelectionModel().select(employee);

        }

        calendarView.setShowSourceTrayButton(true);
        calendarView.setShowToolBar(true);
        calendarView.getCalendarSources().clear();

        taskTreeList.getChildren().clear();
        availableProjects.clear();
        for(Customer customer : AppFacade.appFacade.getCustomerList().getCustomers())
        {
            if(!customer.getEmployees().contains(employeeView.getSelectionModel().getSelectedItem()))
                continue;
            TreeItem<DataEntity> customerItem = new TreeItem<>(customer);
            customerItem.setExpanded(true);
            taskTreeList.getChildren().add(customerItem);

            CalendarSource calendarSource = new CalendarSource(customer.getName());
            calendarView.getCalendarSources().add(calendarSource);

            for(Project project : customer.getProjects())
            {
                TreeItem<DataEntity> projectItem = new TreeItem<>(project);
                customerItem.getChildren().add(projectItem);
                projectItem.setExpanded(true);
                Calendar calendar = new Calendar(project.getName());
                calendar.setStyle(ProjectColor.fromId(project.getColor()).getType());
                calendar.setShortName(project.getShortName());



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

                calendarSource.getCalendars().add(calendar);

                for(ProjectTask projectTask : project.getProjectTasks())
                {
                    if(!availableProjects.contains(project)) availableProjects.add(project); // make project available if it has a task.
                    TreeItem<DataEntity> taskItem = new TreeItem<>(projectTask);
                    projectItem.getChildren().add(taskItem);

                    for(Planning planning : projectTask.getPlannings())
                    {
                        // only show if planning is from selected employee
                        if(employeeView.getSelectionModel().getSelectedItem() != planning.getEmployee()) continue;

                        Entry<DataEntity> entry = new Entry<>(String.format("%s - %s",
                                planning.getProjectTask().getProject().getName(),planning.getProjectTask().getName()));
                        entry.setUserObject(planning);
                        entry.setMinimumDuration(Duration.of(30,MINUTES));
                        entry.changeStartDate(planning.getStart().toLocalDate(),true);
                        entry.changeStartTime(planning.getStart().toLocalTime(),true);
                        entry.changeEndDate(planning.getEnd().toLocalDate(),false);
                        entry.changeEndTime(planning.getEnd().toLocalTime(),false);

                        calendar.addEntry(entry);

                    }
                    for(Timeregistration timeregistration : projectTask.getTimeregistrations())
                    {
                        // only show if planning is from selected employee
                        if(employeeView.getSelectionModel().getSelectedItem() != timeregistration.getEmployee()) continue;

                        Entry<DataEntity> entry = new Entry<>(String.format("%s - %s",
                                timeregistration.getProjectTask().getProject().getName(),timeregistration.getProjectTask().getName()));
                        entry.setUserObject(timeregistration);
                        entry.setMinimumDuration(Duration.of(30,MINUTES));
                        entry.changeStartDate(timeregistration.getStart().toLocalDate(),true);
                        entry.changeStartTime(timeregistration.getStart().toLocalTime(),true);
                        entry.changeEndDate(timeregistration.getEnd().toLocalDate(),false);
                        entry.changeEndTime(timeregistration.getEnd().toLocalTime(),false);

                        calendar.addEntry(entry);

                    }


                }
            }
        }
        taskTreeView.setRoot(taskTreeList);
        updatingCalendar = false;
    }

    private CalendarEvent eventHandler()
    {
        return null;
    }

}
