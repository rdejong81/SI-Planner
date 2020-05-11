package gui;

import Planning.Planning;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.DateControl;
import Data.*;
import facade.AppFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.ChoiceBoxTreeCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import Projects.Project;
import Projects.ProjectTask;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

// calendar.CalendarView;

public class MainWindowController extends Controller
{


   @FXML
   private CalendarView calendarView;


    @FXML TreeView<DataEntity> taskTreeView;

    @FXML
    private TreeView<String> employeeView;

    private TreeItem<String> employeeViewList;
    private TreeItem<DataEntity> taskTreeList;



    public MainWindowController()
    {
        super("MainWindow.fxml", "SI-Planner 0.1");




        calendarView.showWeekPage();
        calendarView.getCalendarSources().clear();

        calendarView.setEntryEditPolicy(param -> {
            param.getDateControl().findEntryView(param.getEntry()).setStyle(String.format("-style6-color: #%06X;",
                    (0xFFFFFF & ((Planning)param.getEntry().getUserObject()).getProjectTask().getProject().getColor())));
            Planning planning = (Planning)param.getEntry().getUserObject();
            if(planning != null)
            {
                planning.setStart(param.getEntry().getStartAsLocalDateTime());
                planning.setEnd(param.getEntry().getEndAsLocalDateTime());
            }

            return true;
        });


        //calendarView.getStyleableParent().getCssMetaData().re






        calendarView.setEntryFactory(param -> {
            DataEntity entity =  taskTreeView.getSelectionModel().getSelectedItem().getValue();
            if(taskTreeView.getSelectionModel().getSelectedItem() == null ||
                    !(entity instanceof ProjectTask)) return null;
            DateControl control = param.getDateControl();
            ZonedDateTime time = param.getZonedDateTime();
            ProjectTask task = (ProjectTask)taskTreeView.getSelectionModel().getSelectedItem().getValue();

            Entry<Planning> entry = new Entry<>(String.format("%s - %s",task.getProject().getName(),task.getName()));

            entry.changeStartDate(time.toLocalDate());
            entry.changeStartTime(time.toLocalTime());
            entry.changeEndDate(entry.getStartDate());
            entry.changeEndTime(entry.getStartTime().plusHours(1));

            entry.getStyleClass().add("style2");
            Planning planning = new Planning(AppFacade.appFacade.getDataSource(),0,false,entry.getStartAsLocalDateTime(),entry.getEndAsLocalDateTime()
            ,task,AppFacade.appFacade.getEmployeeList().getEmployees().iterator().next());
            AppFacade.appFacade.getDataSource().planningDao().insertPlanning(planning);
            entry.setUserObject(planning);

            param.getDefaultCalendar().addEntry(entry);
            entry.getStyleClass().add("test");
            control.findEntryView(entry).setStyle(String.format("-style6-color: #%06X; ",
                     (0xFFFFFF & planning.getProjectTask().getProject().getColor())));
            return null;
        });

        calendarView.setEntryDetailsPopOverContentCallback(param -> {

            return new Label("Test");
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
                        int rgb = ((Project) entity).getColor();
                        Color c = Color.valueOf(String.format("#%06X", (0xFFFFFF & rgb)));
                        setGraphic(new Rectangle(16,16, c));
                    }
                    if (entity instanceof Customer) setText(((Customer) entity).getName());
                }
            }
        });

        employeeViewList = new TreeItem<>("Employees");
        employeeView.setCellFactory(ChoiceBoxTreeCell.<String>forTreeView());

        employeeView.setShowRoot(false);
        employeeView.setEditable(true);

        taskTreeView.setShowRoot(false);
        taskTreeList = new TreeItem<>();


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
        employeeViewList.getChildren().clear();

        for (Employee employee : AppFacade.appFacade.getEmployeeList().getEmployees())
        {
            employeeViewList.getChildren().add(new TreeItem<>(employee.getName()));
        }

        employeeView.setRoot(employeeViewList);
        calendarView.setShowSourceTrayButton(true);
        calendarView.setShowToolBar(true);
        calendarView.getCalendarSources().clear();

        taskTreeList.getChildren().clear();
        for(Customer customer : AppFacade.appFacade.getCustomerList().getCustomers())
        {
            TreeItem<DataEntity> customerItem = new TreeItem<>(customer);
            taskTreeList.getChildren().add(customerItem);

            CalendarSource calendarSource = new CalendarSource(customer.getName());
            calendarView.getCalendarSources().add(calendarSource);

            for(Project project : customer.getProjects())
            {
                TreeItem<DataEntity> projectItem = new TreeItem<>(project);
                customerItem.getChildren().add(projectItem);
                Calendar calendar = new Calendar(project.getName());
                calendar.setStyle(Calendar.Style.STYLE6);
                calendar.setShortName(project.getShortName());

                calendarSource.getCalendars().add(calendar);

                for(ProjectTask projectTask : project.getProjectTasks())
                {
                    TreeItem<DataEntity> taskItem = new TreeItem<>(projectTask);
                    projectItem.getChildren().add(taskItem);

                    for(Planning planning : projectTask.getPlannings())
                    {
                        Entry<Planning> entry = new Entry<>(String.format("%s - %s",
                                planning.getProjectTask().getProject().getName(),planning.getProjectTask().getName()));
                        entry.setUserObject(planning);
                        entry.changeStartDate(planning.getStart().toLocalDate());
                        entry.changeStartTime(planning.getStart().toLocalTime());
                        entry.changeEndDate(planning.getEnd().toLocalDate());
                        entry.changeEndTime(planning.getEnd().toLocalTime());
                        calendar.addEntry(entry);/*
                        calendarView.findEntryView(entry).setStyle(String.format("-style6-color: #%06X; ",
                                (0xFFFFFF & planning.getProjectTask().getProject().getColor())));*/
                    }

                }
            }
        }
        taskTreeView.setRoot(taskTreeList);

    }
}
