package Windows;

import Data.*;
import Facade.AppFacade;
import Facade.IDataEntityPresenter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.lang.reflect.AnnotatedType;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

public class ManageCustomersController extends Controller implements IDataEntityPresenter
{
    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private TableView<Attribute> attributeDefView,attributeView;

    @FXML
    private TableColumn<Attribute,String> nameAttributeDefColumn,attributeColumn;

    @FXML private TableColumn<Attribute,Object> attributeValueColumn;

    @FXML private TableColumn<Attribute,AttributeType> typeAttributeDefColumn;

    @FXML private TableColumn<Attribute,EntityType> entityTypeAttributeDefColumn;

    @FXML
    private ListView<Employee> employeeListView;

    @FXML
    private TableColumn<String, Customer> shortNameColumn,nameColumn;

    @FXML
    private TextField nameField,shortNameField;

    private Customer lastSelected;

    public ManageCustomersController(Controller owner)
    {
        super("ManageCustomers.fxml", "Manage Customers");
        getStage().initOwner(owner.getStage());
        getStage().initModality(Modality.WINDOW_MODAL);
        this.getStage().setResizable(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        attributeValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        attributeValueColumn.setCellFactory(ObjectTableCell.forTableColumn());
        attributeValueColumn.setOnEditCommit(value -> {
            value.getRowValue().setValue(value.getNewValue());
        });
        attributeColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        shortNameColumn.setCellValueFactory(new PropertyValueFactory<>("shortName"));

        nameAttributeDefColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameAttributeDefColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameAttributeDefColumn.setOnEditCommit(value -> {
            value.getRowValue().setName(value.getNewValue());
        });

        entityTypeAttributeDefColumn.setCellValueFactory(new PropertyValueFactory<>("entityType"));
        entityTypeAttributeDefColumn.setCellFactory(ComboBoxTableCell.forTableColumn( new StringConverter<>()
        {

            @Override
            public String toString(EntityType object)
            {
                return object.toString();
            }

            @Override
            public EntityType fromString(String string)
            {
                return EntityType.valueOf(string);
            }
        }, FXCollections.observableArrayList(EntityType.entityTypesWithAttributes())));
        entityTypeAttributeDefColumn.setOnEditCommit(value -> {
            AppFacade.appFacade.reassignAttributeDefinition(value.getRowValue(),value.getNewValue());
        });

        typeAttributeDefColumn.setCellValueFactory(new PropertyValueFactory<>("attributeType"));
        typeAttributeDefColumn.setOnEditCommit(value -> {
            value.getRowValue().setAttributeType(value.getNewValue());
        });

        typeAttributeDefColumn.setCellFactory(ComboBoxTableCell.forTableColumn( new StringConverter<>()
        {

            @Override
            public String toString(AttributeType object)
            {
                return object.getFriendlyName();
            }

            @Override
            public AttributeType fromString(String string)
            {
                return AttributeType.fromFriendlyName(string);
            }
        }, FXCollections.observableArrayList( Arrays.asList(AttributeType.values()))));

        customerTableView.getSelectionModel().selectedItemProperty().addListener( (obs, oldSelection, newSelection) ->
                {
                    if(newSelection == null) return;
                    lastSelected = newSelection;
                    nameField.setText(newSelection.getName());
                    shortNameField.setText(newSelection.getShortName());

                    attributeDefView.getItems().clear();
                    for(Attribute attribute : newSelection.getAttributeDefinitions())
                    {
                        attributeDefView.getItems().add(attribute);
                    }

                    attributeView.getItems().clear();
                    for(Attribute attribute : newSelection.getAttributes())
                    {
                        attributeView.getItems().add(attribute);
                    }

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
                            for (Employee employee : employeeListView.getItems())
                            {
                                if(employee.getName().equals(string)) return employee;
                            }
                            return null;
                        }
                    }));
                }

        );


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

        AppFacade.appFacade.subscribeDataEntityPresenter(this);
    }


    @FXML
    private void addButtonClick()
    {
        AppFacade.appFacade.addCustomer("new","new");
    }

    @FXML
    private void removeButtonClick()
    {
        AppFacade.appFacade.removeCustomer(customerTableView.getSelectionModel().getSelectedItem());
    }

    @FXML private void addAttrButtonClick(ActionEvent actionEvent)
    {
        AppFacade.appFacade.createAttributeDefinition(
                customerTableView.getSelectionModel().getSelectedItem(),
                "new",
                AttributeType.STRING,
                EntityType.CUSTOMER
        );
    }

    @FXML private void removeAttrButtonClick(ActionEvent actionEvent)
    {
        AppFacade.appFacade.deleteAttributeDefinition(attributeDefView.getSelectionModel().getSelectedItem());
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
        AppFacade.appFacade.unsubscribeDataEntityPresenter(this);

    }


    @Override
    public void showDataEntity(DataEntity dataEntity)
    {

        if(dataEntity instanceof Customer && !customerTableView.getItems().contains(dataEntity))
        {
            customerTableView.getItems().add((Customer) dataEntity);
            if(lastSelected == dataEntity) customerTableView.getSelectionModel().select(lastSelected);
        }

        if(dataEntity instanceof Employee && !employeeListView.getItems().contains(dataEntity))
        {
            employeeListView.getItems().add((Employee) dataEntity);
        }


    }

    @Override
    public void hideDataEntity(DataEntity dataEntity)
    {
        if(dataEntity instanceof Customer)
        {
            if(lastSelected == dataEntity) customerTableView.getSelectionModel().clearSelection();
            customerTableView.getItems().remove(dataEntity);
        }

        if(dataEntity instanceof Employee)
        {
            employeeListView.getItems().remove(dataEntity);
        }
    }

}
