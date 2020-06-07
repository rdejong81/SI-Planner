package Windows;

import Data.Attribute;
import Data.Customer;
import Data.DaoResult;
import Data.DataEntity;
import Facade.IDataEntityPresenter;
import Projects.Project;
import Projects.ProjectTask;
import Facade.AppFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageProjectsController extends Controller implements IDataEntityPresenter
{
    @FXML
    private TableView<Project> projectTableView;

    @FXML private TableView<Attribute> attributeView;

    @FXML private TableColumn<Attribute,String> attributeColumn;
    @FXML private TableColumn<Attribute,Object> attributeValueColumn;

    @FXML
    private TableColumn<String, Project> shortNameColumn,nameColumn;

    @FXML
    private TextField nameField,shortNameField,taskNameField;

    @FXML
    private ChoiceBox<Customer> customerField;

    @FXML
    private ComboBox<ProjectColor> colorField;

    @FXML
    private CheckBox invoiceField,taskCompletedField;

    @FXML
    private ListView<ProjectTask> taskListView;

    private Project lastSelected;


    protected ManageProjectsController(Controller owner)
    {
        super("ManageProjects.fxml", "Manage Projects");
        getStage().initOwner(owner.getStage());
        getStage().initModality(Modality.WINDOW_MODAL);
        this.getStage().setResizable(false);

        attributeColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        attributeValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        attributeValueColumn.setCellFactory(ObjectTableCell.forTableColumn());
        attributeValueColumn.setOnEditCommit(value -> {
            value.getRowValue().setValue(value.getNewValue());
        });

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        shortNameColumn.setCellValueFactory(new PropertyValueFactory<>("shortName"));


        // color dropdownlist
        for(ProjectColor projectColor : ProjectColor.ids.values())
        {
            colorField.getItems().add(projectColor);
        }

        colorField.setCellFactory(param -> {
            return new ListCell<>() {
                @Override protected void updateItem(ProjectColor item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        setText(item.getName());
                        setGraphic(new Rectangle(10,10,item.getColor()));
                    }
                }

            };
        });
        colorField.setButtonCell(colorField.getCellFactory().call(null));


        // convert task to text
        taskListView.setCellFactory(param -> new ListCell<>(){
            @Override
            protected void updateItem(ProjectTask item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    if(item.isCompleted())
                    {
                        setStyle("-fx-font-style: italic; ");
                        setTextFill(Color.valueOf("#9e9c98"));
                    } else {
                        setStyle(null);
                        setTextFill(Color.BLACK);
                    }
                    setText(item.getName());
                }
            }
        });

        // update task controls when task selection changes
        taskListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection == null) return;
            taskNameField.setText(newSelection.getName());
            taskCompletedField.setSelected(newSelection.isCompleted());

        });

        // convert Customer to text
        customerField.setConverter(new StringConverter<Customer>()
        {
            @Override
            public String toString(Customer object)
            {
                return object.getName();
            }

            @Override
            public Customer fromString(String string)
            {
                for (Customer customer : customerField.getItems())
                {
                    if(customer.getName().equals(string)) return customer;
                }
                return null;
            }
        });


       projectTableView.getSelectionModel().selectedItemProperty().addListener( (obs, oldSelection, newSelection) ->
                {
                    if(newSelection == null) return;
                    nameField.setText(newSelection.getName());
                    shortNameField.setText(newSelection.getShortName());
                    colorField.setValue(ProjectColor.fromId(newSelection.getColor()));
                    customerField.setValue(newSelection.getCustomer());
                    invoiceField.setSelected(newSelection.isInvoice());

                    taskListView.getItems().clear();
                    for(ProjectTask projectTask : newSelection.getProjectTasks())
                    {
                        taskListView.getItems().add(projectTask);
                    }
                    attributeView.getItems().clear();
                    for(Attribute attribute : newSelection.getAttributes())
                    {
                        attributeView.getItems().add(attribute);
                    }
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



        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(projectTableView.getSelectionModel().getSelectedItem() == null) return;
            projectTableView.getSelectionModel().getSelectedItem().setName(newValue);
            projectTableView.refresh();
        });

        shortNameField.textProperty().addListener( (observable, oldValue, newValue) -> {
            if(projectTableView.getSelectionModel().getSelectedItem() == null) return;
            projectTableView.getSelectionModel().getSelectedItem().setShortName(newValue);
            projectTableView.refresh();
        });

        colorField.valueProperty().addListener( (observable, oldValue, newValue) -> {
            if(projectTableView.getSelectionModel().getSelectedItem() == null) return;
            projectTableView.getSelectionModel().getSelectedItem().setColor(newValue.getId());
        });

        invoiceField.selectedProperty().addListener( (observable, oldValue, newValue) -> {
            if(projectTableView.getSelectionModel().getSelectedItem() == null) return;
            projectTableView.getSelectionModel().getSelectedItem().setInvoice(newValue);
        });

        customerField.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
            if(projectTableView.getSelectionModel().getSelectedItem() == null ||
            newValue == projectTableView.getSelectionModel().getSelectedItem().getCustomer()
            || newValue == null) return;
            projectTableView.getSelectionModel().getSelectedItem().setCustomer(newValue);
        });

        taskCompletedField.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(taskListView.getSelectionModel().getSelectedItem() == null) return;
            taskListView.getSelectionModel().getSelectedItem().setCompleted(newValue);
            taskListView.refresh();
        });

        taskNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(taskListView.getSelectionModel().getSelectedItem() == null) return;
            taskListView.getSelectionModel().getSelectedItem().setName(newValue);
            taskListView.refresh();
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
        AppFacade.appFacade.addProject("new","NEW");
    }

    public void removeButtonClick(ActionEvent actionEvent)
    {
        if(projectTableView.getSelectionModel().getSelectedItem() != null)
        AppFacade.appFacade.removeProject(projectTableView.getSelectionModel().getSelectedItem());
    }

    public void addTaskButtonClick(ActionEvent actionEvent)
    {
        Project project = projectTableView.getSelectionModel().getSelectedItem();
        if(project == null) return;

        ProjectTask projectTask = new ProjectTask(AppFacade.appFacade.getDataSource().taskDao(),0,"new",false,project);
        if(AppFacade.appFacade.getDataSource().taskDao().insertTask(projectTask) == DaoResult.OP_OK)
            taskListView.getItems().add(projectTask);
    }

    public void removeTaskButtonClick(ActionEvent actionEvent)
    {
        if(taskListView.getSelectionModel().getSelectedItem() == null) return;

        if(AppFacade.appFacade.getDataSource().taskDao().deleteTask(taskListView.getSelectionModel().getSelectedItem()) == DaoResult.OP_OK)
        {
            taskListView.getItems().remove(taskListView.getSelectionModel().getSelectedItem());
        }
    }

    @Override
    public void showDataEntity(DataEntity dataEntity)
    {

        if(dataEntity instanceof Project)
        {
            projectTableView.getItems().add((Project) dataEntity);
            if(projectTableView.getItems().size() == 1 && lastSelected == null)
                projectTableView.getSelectionModel().select(0);  // select first item.
            if(dataEntity == lastSelected)
                projectTableView.getSelectionModel().select(lastSelected);
        }

        if(dataEntity instanceof Customer)
        {
            customerField.getItems().add((Customer) dataEntity);
        }
    }

    @Override
    public void hideDataEntity(DataEntity dataEntity)
    {
        if(dataEntity instanceof Project)
        {
            if(projectTableView.getSelectionModel().getSelectedItem() == dataEntity)
            {
                projectTableView.getSelectionModel().clearSelection();
                lastSelected = (Project) dataEntity;
            }

            projectTableView.getItems().remove(dataEntity);
        }

        if(dataEntity instanceof Customer)
        {
            customerField.getItems().remove(dataEntity);
        }

    }

}
