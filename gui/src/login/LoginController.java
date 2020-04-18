package login;

import gui.IGuiRunUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class LoginController extends gui.Controller
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

    private IGuiRunUseCase loginUseCase;

    private HashMap<String,Enum> dbTypes;

    public LoginController(IGuiRunUseCase loginUseCase, HashMap<String,Enum> dbTypes) throws IOException
    {
        super("LoginWindow.fxml","Login to SI-Planner");
        assert(server != null && password != null && username != null && dbType != null && errorlabel != null);

        for(String type : dbTypes.keySet()) {
            dbType.getItems().add(type);
        }
        this.loginUseCase = loginUseCase;
        this.dbTypes = dbTypes;
     }
    public void setError(String error){
        errorlabel.setText(error);
    }

    public void loginButtonPress(ActionEvent actionEvent)
    {
        if(loginUseCase.runUseCase(this))
        {
            this.submitted = true;
            super.getStage().close();
        }
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

    public Enum getDBType(){
        for(String type : dbTypes.keySet()) {
            if(dbType.getValue().equals(type)) return dbTypes.get(type);
        }
        return null;
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
