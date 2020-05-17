package Windows;

import Data.Customer;
import Data.Employee;
import Facade.AppFacade;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageEmployeesController extends Controller
{
    @FXML
    private TableView<Employee> employeeTableView;
    @FXML
    private ListView<Customer> customerListView;

    @FXML
    private TableColumn<String, Employee> loginNameColumn;
    @FXML
    private TableColumn<String, Employee> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField loginNameField;



    protected ManageEmployeesController(Controller owner)
    {
        super("ManageEmployees.fxml", "ManageEmployees");
        getStage().initOwner(owner.getStage());
        getStage().initModality(Modality.WINDOW_MODAL);
        this.getStage().setResizable(false);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        loginNameColumn.setCellValueFactory(new PropertyValueFactory<>("SqlLoginName"));

        for(Employee employee : AppFacade.appFacade.getEmployeeList().getEmployees())
        {
            employeeTableView.getItems().add(employee);

        }

        for(Customer customer : AppFacade.appFacade.getCustomerList().getCustomers())
        {
            customerListView.getItems().add(customer);
        }



        employeeTableView.getSelectionModel().selectedItemProperty().addListener( (obs, oldSelection, newSelection) ->
                {
                    nameField.setText(newSelection.getName());
                    loginNameField.setText(newSelection.getSqlLoginName());

                    // employee list, add checkboxes and change listener representing linked employees.
                    customerListView.setCellFactory(CheckBoxListCell.forListView(new Callback<Customer, ObservableValue<Boolean>>()
                    {
                        @Override
                        public ObservableValue<Boolean> call(Customer customer)
                        {
                            BooleanProperty value = new SimpleBooleanProperty();
                            value.setValue(customer.getEmployees().contains(employeeTableView.getSelectionModel().getSelectedItem()));
                            value.addListener((obs, oldValue, newValue) ->
                                    {
                                        if(employeeTableView.getSelectionModel().getSelectedItem() == null) return;
                                        if (newValue)
                                        {
                                            employeeTableView.getSelectionModel().getSelectedItem().addCustomer(customer);
                                        } else
                                        {
                                            employeeTableView.getSelectionModel().getSelectedItem().removeCustomer(customer);
                                        }
                                    }
                            );
                            return value;
                        }
                    }, new StringConverter<Customer>() // represent objects as text by full employee names
                    {
                        @Override
                        public String toString(Customer object)
                        {
                            return object.getName();
                        }

                        @Override
                        public Customer fromString(String string)
                        {
                            for (Customer customer : AppFacade.appFacade.getCustomerList().getCustomers())
                            {
                                if(customer.getName().equals(string)) return customer;
                            }
                            return null;
                        }
                    }));
                }

        );

        if(employeeTableView.getItems().size() > 0)
            employeeTableView.getSelectionModel().select(0);  // select first item.


        loginNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(employeeTableView.getSelectionModel().getSelectedItem() == null) return;
            employeeTableView.getSelectionModel().getSelectedItem().setSqlLoginName(newValue);
            employeeTableView.refresh();
        });

        nameField.textProperty().addListener( (observable, oldValue, newValue) -> {
            if(employeeTableView.getSelectionModel().getSelectedItem() == null) return;
            employeeTableView.getSelectionModel().getSelectedItem().setName(newValue);
            employeeTableView.refresh();
        });



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

    }

    public void closeButtonClick(ActionEvent actionEvent)
    {
        getStage().close();
    }

    public void addButtonClick(ActionEvent actionEvent)
    {
        Employee newEmployee = AppFacade.appFacade.addEmployee("new","new");
        if(newEmployee != null) employeeTableView.getItems().add(newEmployee);
    }

    public void removeButtonClick(ActionEvent actionEvent)
    {
        if(AppFacade.appFacade.removeEmployee(employeeTableView.getSelectionModel().getSelectedItem()))
        {
            employeeTableView.getItems().remove(employeeTableView.getSelectionModel().getSelectedItem());
        }
    }
}
