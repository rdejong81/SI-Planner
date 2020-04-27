package gui;

import calendar.CalendarView;
import calendar.DatePickerExpanded;
import data.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.ChoiceBoxTreeCell;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends Controller
{


    @FXML
    private CalendarView calendar;

    @FXML
    private DatePickerExpanded datePicker;

    @FXML
    private TreeView<String> employeeView;

    private TreeItem<String> employeeViewList;


    public MainWindowController()
    {
        super("MainWindow.fxml", "SI-Planner 0.1");

        datePicker.getSelectedValue().addListener((observable, oldValue, newValue) -> {
            calendar.setDate(newValue);
        });



        employeeViewList = new TreeItem<>("Employees");
        employeeView.setCellFactory(ChoiceBoxTreeCell.<String>forTreeView());

        employeeView.setShowRoot(false);
        employeeView.setEditable(true);

        for (Employee employee : facade.AppFacade.getEmployees())
        {
            employeeViewList.getChildren().add(new TreeItem<>(employee.getName()));
        }

        employeeView.setRoot(employeeViewList);

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
}
