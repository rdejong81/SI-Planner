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

package Windows;

import Data.*;
import Facade.AppFacade;
import Facade.IDataEntityPresenter;
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

public class ManageEmployeesController extends Controller implements IDataEntityPresenter
{
    @FXML private TableView<Attribute> attributeTableView;
    @FXML private TableColumn<Attribute,String> attributeNameColumn;
    @FXML private TableColumn<Attribute,Object> attributeValueColumn;

    @FXML private TableView<Employee> employeeTableView;

    @FXML private ListView<Customer> customerListView;

    @FXML
    private TableColumn<String, Employee> loginNameColumn;
    @FXML
    private TableColumn<String, Employee> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField loginNameField;

    @FXML
    private Label errorLabel;



    protected ManageEmployeesController(Controller owner)
    {
        super("ManageEmployees.fxml", "ManageEmployees");
        getStage().initOwner(owner.getStage());
        getStage().initModality(Modality.WINDOW_MODAL);
        this.getStage().setResizable(false);

        attributeValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        attributeValueColumn.setCellFactory(ObjectTableCell.forTableColumn());
        attributeValueColumn.setOnEditCommit(value -> {
            value.getRowValue().setValue(value.getNewValue());
        });
        attributeNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        loginNameColumn.setCellValueFactory(new PropertyValueFactory<>("SqlLoginName"));

        employeeTableView.getSelectionModel().selectedItemProperty().addListener( (obs, oldSelection, newSelection) ->
                {
                    nameField.setText(newSelection.getName());
                    loginNameField.setText(newSelection.getSqlLoginName());

                    attributeTableView.getItems().clear();
                    for(Customer customer : newSelection.getCustomers())
                    {
                        for(Attribute attribute : newSelection.getAttributes())
                        {
                            if(attribute.getParentDefinition().getParent() == customer)
                                attributeTableView.getItems().add(attribute);
                        }
                    }

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
                    }, new StringConverter<>() // represent objects as text by full employee names
                    {
                        @Override
                        public String toString(Customer object)
                        {
                            return object.getName();
                        }

                        @Override
                        public Customer fromString(String string)
                        {
                            for (Customer customer : customerListView.getItems())
                            {
                                if(customer.getName().equals(string)) return customer;
                            }
                            return null;
                        }
                    }));
                }

        );




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

        AppFacade.appFacade.subscribeDataEntityPresenter(this);



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


    public void closeButtonClick(ActionEvent actionEvent)
    {
        getStage().close();
    }

    public void addButtonClick(ActionEvent actionEvent)
    {
        AppFacade.appFacade.addEmployee("new","new");
    }

    public void removeButtonClick(ActionEvent actionEvent)
    {
        switch (AppFacade.appFacade.removeEmployee(employeeTableView.getSelectionModel().getSelectedItem()))
        {
            case OP_OK:
                errorLabel.setText("");break;
            case DAO_INUSE:
                errorLabel.setText("Unable to remove, database record is in use.");break;
            default:
                errorLabel.setText("Unknown error.");break;

        }
    }

    @Override
    public void showDataEntity(DataEntity dataEntity)
    {
        if(dataEntity instanceof Customer && !customerListView.getItems().contains(dataEntity))
        {
            customerListView.getItems().add((Customer) dataEntity);

        }

        if(dataEntity instanceof Employee && !employeeTableView.getItems().contains(dataEntity))
        {
            employeeTableView.getItems().add((Employee) dataEntity);
            if(employeeTableView.getItems().size() == 1)
                employeeTableView.getSelectionModel().select(0);  // select first item.
        }
    }

    @Override
    public void hideDataEntity(DataEntity dataEntity)
    {
        if(dataEntity instanceof Customer)
        {
            customerListView.getItems().remove(dataEntity);
        }

        if(dataEntity instanceof Employee)
        {
            employeeTableView.getItems().remove(dataEntity);
        }
    }

}
