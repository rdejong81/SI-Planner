package gui;

import Data.Customer;
import Data.Employee;
import facade.AppFacade;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageCustomersController extends Controller
{    @FXML
private TableView<Customer> customerTableView;
    @FXML
    private ListView<Employee> employeeListView;

    @FXML
    private TableColumn<String, Customer> shortNameColumn;
    @FXML
    private TableColumn<String, Customer> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField shortNameField;





    public ManageCustomersController(Controller owner) throws IOException
    {
        super("ManageCustomers.fxml", "Manage Customers");
        getStage().initOwner(owner.getStage());
        getStage().initModality(Modality.WINDOW_MODAL);
        this.getStage().setResizable(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        shortNameColumn.setCellValueFactory(new PropertyValueFactory<>("shortName"));

        for(Customer customer : AppFacade.appFacade.getCustomerList().getCustomers())
        {
            customerTableView.getItems().add(customer);

        }

        for(Employee employee : AppFacade.appFacade.getEmployeeList().getEmployees())
        {
            employeeListView.getItems().add(employee);
        }



        customerTableView.getSelectionModel().selectedItemProperty().addListener( (obs, oldSelection, newSelection) ->
                {
                    nameField.setText(newSelection.getName());
                    shortNameField.setText(newSelection.getShortName());

                    // employee list, add checkboxes and change listener representing linked employees.
                    employeeListView.setCellFactory(CheckBoxListCell.forListView(new Callback<Employee, ObservableValue<Boolean>>()
                    {
                        @Override
                        public ObservableValue<Boolean> call(Employee employee)
                        {
                            BooleanProperty value = new SimpleBooleanProperty();
                            value.setValue(employee.getCustomers().contains(customerTableView.getSelectionModel().getSelectedItem()));
                            value.addListener((obs, oldValue, newValue) ->
                                    {
                                        if(customerTableView.getSelectionModel().getSelectedItem() == null) return;
                                        if (newValue)
                                        {
                                            customerTableView.getSelectionModel().getSelectedItem().addEmployee(employee);
                                        } else
                                        {
                                            customerTableView.getSelectionModel().getSelectedItem().removeEmployee(employee);
                                        }
                                    }
                            );
                            return value;
                        }
                    }, new StringConverter<Employee>() // represent objects as text by full employee names
                    {
                        @Override
                        public String toString(Employee object)
                        {
                            return object.getName();
                        }

                        @Override
                        public Employee fromString(String string)
                        {
                            for (Employee employee : AppFacade.appFacade.getEmployeeList().getEmployees())
                            {
                                if(employee.getName().equals(string)) return employee;
                            }
                            return null;
                        }
                    }));
                }

        );

        if(customerTableView.getItems().size() > 0)
            customerTableView.getSelectionModel().select(0);  // select first item.

        // only allow uppercase characters x 10
        shortNameField.setTextFormatter(new TextFormatter<>(change -> {

            if(change.getControlNewText().matches("([0-9A-Za-z]{0,10})?"))
            {
                change.setText(change.getText().toUpperCase());
                return change;
            }

            return null;
        }));

        shortNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(customerTableView.getSelectionModel().getSelectedItem() == null) return;
            customerTableView.getSelectionModel().getSelectedItem().setShortName(newValue);
            customerTableView.refresh();
        });

        nameField.textProperty().addListener( (observable, oldValue, newValue) -> {
            if(customerTableView.getSelectionModel().getSelectedItem() == null) return;
            customerTableView.getSelectionModel().getSelectedItem().setName(newValue);
            customerTableView.refresh();
        });



    }


    @FXML
    private void addButtonClick()
    {
        Customer newCustomer = AppFacade.appFacade.addCustomer("new","new");
        if(newCustomer != null) customerTableView.getItems().add(newCustomer);
    }

    @FXML
    private void removeButtonClick()
    {
        if(AppFacade.appFacade.removeCustomer(customerTableView.getSelectionModel().getSelectedItem()))
        {
            customerTableView.getItems().remove(customerTableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void closeButtonClick()
    {
        getStage().close();
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
}
