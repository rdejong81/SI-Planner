package gui;

import com.sun.tools.javac.Main;
import db.DBType;
import gui.Controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static db.MySQLConnection.MYSQL_TYPE_STR;

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

    public LoginController() throws IOException
    {
        super("LoginWindow.fxml","Login to SI-Planner");
        this.errortext = errortext;
        assert(server != null && password != null && username != null && dbType != null && errorlabel != null);
        dbType.getItems().add(MYSQL_TYPE_STR);
        dbType.setValue(MYSQL_TYPE_STR);
     }
    public void setError(String error){
        errorlabel.setText(error);
    }

    public void loginButtonPress(ActionEvent actionEvent)
    {
        if(program.AppFacade.tryLogin(this))
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

    public DBType getDBType(){
        if(dbType.getValue() == MYSQL_TYPE_STR) return DBType.MYSQL;
        return DBType.MYSQL;
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
