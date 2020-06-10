package Login;

import Data.DSCapability;
import Data.DaoResult;
import Data.IDataSource;
import Facade.AppFacade;
import Facade.ISQLConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class LoginController extends Windows.Controller
{
    @FXML
    private ChoiceBox<String> dbType;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField server;
    @FXML
    private TextField database;
    @FXML
    private Label errorlabel;

    private boolean submitted;

    private boolean cancelled;

    private HashMap<String,Integer> dbTypes;
    private final ISQLConnectionFactory sqlConnectionFactory;

    public LoginController(ISQLConnectionFactory sqlConnectionFactory)
    {
        super("LoginWindow.fxml","Login to SI-Planner");
        this.sqlConnectionFactory = sqlConnectionFactory;

        //loginPreferences.getGroups().add(Group.of(Setting.of("Datasource type",dbType.idProperty())));

        this.getStage().setResizable(false);

        this.getStage().initModality(Modality.APPLICATION_MODAL);

        this.dbTypes = new HashMap<>();

        dbType.getSelectionModel().selectedItemProperty().addListener(event ->
        {
            try
            {
                String selected = dbType.selectionModelProperty().getValue().getSelectedItem();
                IDataSource dataSource = sqlConnectionFactory.SQLFactoryCreate(sqlConnectionFactory.getDatabaseDrivers().get(selected),
                    null, null, null, null);
                username.setDisable(true);
                password.setDisable(true);
                database.setDisable(true);
                server.setDisable(true);


                for(DSCapability dsCapability : dataSource.getCapabilities())
                {
                    switch (dsCapability)
                    {
                        case LOGIN -> {
                            username.setDisable(false);
                            password.setDisable(false);
                        }
                        case DATABASENAME -> database.setDisable(false);
                        case SERVERLOCATION -> server.setDisable(false);
                    }
                }
            } catch (Exception e)
            {

            }


        });
     }

    public void loginButtonPress(ActionEvent actionEvent)
    {

        DaoResult result = AppFacade.appFacade.DoLogin(username.getText(),password.getText(),database.getText(),server.getText(),dbType.getValue());

        switch(result)
        {
            case OP_OK:
                this.submitted = true;
                super.getStage().close();
                break;
            case DS_DISCONNECTED:
                errorlabel.setText("Username or password is not correct.");break;
            default:
                errorlabel.setText("Unable to connect to data source.");
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
        if(!this.submitted) this.cancelled=true;
    }

    public boolean addDbType(String type)
    {
        return dbType.getItems().add(type);
    }

    public int getDBType(){
        if(dbType.getValue() == null) return -1;
        for(String type : dbTypes.keySet()) {
            if(dbType.getValue().equals(type)) return dbTypes.get(type);
        }
        return -1;
    }


    public String getUserName(){
        return username.getText();
    }

    public String getPassword(){
        return password.getText();
    }

    public String getServer(){
        return server.getText();
    }

    public String getDatabase(){
        return database.getText();
    }


    public boolean getCancelled(){
        return cancelled;
    }

}
