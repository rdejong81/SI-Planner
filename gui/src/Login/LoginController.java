package Login;

import Data.DaoResult;
import Facade.AppFacade;
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

    private String errortext;

    private boolean submitted;

    private boolean cancelled;

    private HashMap<String,Integer> dbTypes;

    public LoginController()
    {
        super("LoginWindow.fxml","Login to SI-Planner");

        //loginPreferences.getGroups().add(Group.of(Setting.of("Datasource type",dbType.idProperty())));

        this.getStage().setResizable(false);

        this.getStage().initModality(Modality.APPLICATION_MODAL);

        this.dbTypes = new HashMap<>();
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
            default:
                errorlabel.setText("Unknown error");
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
