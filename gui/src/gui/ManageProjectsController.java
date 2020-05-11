package gui;

import Data.Customer;
import Projects.Project;
import Projects.ProjectTask;
import facade.AppFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageProjectsController extends Controller
{
    @FXML
    private TableView<Project> projectTableView;

    @FXML
    private TableColumn<String, Project> shortNameColumn,nameColumn;

    @FXML
    private TextField nameField,shortNameField,taskNameField;

    @FXML
    private ChoiceBox<Customer> customerField;

    @FXML
    private ColorPicker colorField;

    @FXML
    private CheckBox invoiceField,taskCompletedField;

    @FXML
    private ListView<ProjectTask> taskListView;



    protected ManageProjectsController(Controller owner)
    {
        super("ManageProjects.fxml", "Manage Projects");
        getStage().initOwner(owner.getStage());
        getStage().initModality(Modality.WINDOW_MODAL);
        this.getStage().setResizable(false);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        shortNameColumn.setCellValueFactory(new PropertyValueFactory<>("shortName"));

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
                for (Customer customer : AppFacade.appFacade.getCustomerList().getCustomers())
                {
                    if(customer.getName().equals(string)) return customer;
                }
                return null;
            }
        });

        for(Customer customer : AppFacade.appFacade.getCustomerList().getCustomers())
        {
            customerField.getItems().add(customer);

            for(Project project : customer.getProjects())
            {
                projectTableView.getItems().add(project);
            }
        }


       projectTableView.getSelectionModel().selectedItemProperty().addListener( (obs, oldSelection, newSelection) ->
                {
                    nameField.setText(newSelection.getName());
                    shortNameField.setText(newSelection.getShortName());
                    colorField.setValue(Color.valueOf(String.format("#%06X", (0xFFFFFF & newSelection.getColor()))));
                    customerField.setValue(newSelection.getCustomer());
                    invoiceField.setSelected(newSelection.isInvoice());

                    taskListView.getItems().clear();
                    for(ProjectTask projectTask : newSelection.getProjectTasks())
                    {
                        taskListView.getItems().add(projectTask);
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
            int r = (int) Math.round(newValue.getRed() * 255);
            int g = (int) Math.round(newValue.getGreen() * 255);
            int b = (int) Math.round(newValue.getBlue() * 255);
            projectTableView.getSelectionModel().getSelectedItem().setColor(((r&0x0ff)<<16)|((g&0x0ff)<<8)|(b&0x0ff));
        });

        invoiceField.selectedProperty().addListener( (observable, oldValue, newValue) -> {
            if(projectTableView.getSelectionModel().getSelectedItem() == null) return;
            projectTableView.getSelectionModel().getSelectedItem().setInvoice(newValue);
        });

        customerField.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
            if(projectTableView.getSelectionModel().getSelectedItem() == null) return;
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
        Customer customer = AppFacade.appFacade.getCustomerList().getCustomers().iterator().next();
        if(customer == null) return;
        Project project = new Project(AppFacade.appFacade.getDataSource(),0,"new",0,false,"new",customer);
        if(AppFacade.appFacade.getDataSource().projectDao().insertProject(project))
            projectTableView.getItems().add(project);
    }

    public void removeButtonClick(ActionEvent actionEvent)
    {
        if(AppFacade.appFacade.getDataSource().projectDao().deleteProject(projectTableView.getSelectionModel().getSelectedItem()))
        {
            projectTableView.getItems().remove(projectTableView.getSelectionModel().getSelectedItem());
        }
    }

    public void addTaskButtonClick(ActionEvent actionEvent)
    {
        Project project = projectTableView.getSelectionModel().getSelectedItem();
        if(project == null) return;

        ProjectTask projectTask = new ProjectTask(AppFacade.appFacade.getDataSource(),0,"new",false,project);
        if(AppFacade.appFacade.getDataSource().taskDao().insertTask(projectTask))
            taskListView.getItems().add(projectTask);
    }

    public void removeTaskButtonClick(ActionEvent actionEvent)
    {
        if(taskListView.getSelectionModel().getSelectedItem() == null) return;

        if(AppFacade.appFacade.getDataSource().taskDao().deleteTask(taskListView.getSelectionModel().getSelectedItem()))
        {
            taskListView.getItems().remove(taskListView.getSelectionModel().getSelectedItem());
        }
    }
}
