package gui;

import com.sun.tools.javac.Main;
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
        super();
        this.errortext = errortext;
        assert(server != null && password != null && username != null && dbType != null && errorlabel != null);
        dbType.getItems().add("MySQL");
        dbType.setValue("MySQL");
    }
    public void setError(String error){
        errorlabel.setText(error);
    }

    public void login(ActionEvent actionEvent)
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
        // todo check if still needed.
    }

    @Override
    protected void onClosed()
    {
        if(!this.submitted) this.cancelled=true;
    }

    @Override
    protected String getWindowFXML()
    {
        return "LoginWindow.fxml";
    }

    @Override
    public String getWindowTitle()
    {
        return "Login";
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
