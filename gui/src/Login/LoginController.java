package Login;

import Facade.ILoginProcessor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Facade.ILoginController;
import javafx.stage.Modality;
import jdk.jfr.Category;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class LoginController extends Windows.Controller implements ILoginController
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

    private ILoginProcessor loginProcessor;

    private HashMap<String,Integer> dbTypes;

    public LoginController()
    {
        super("LoginWindow.fxml","Login to SI-Planner");

        //loginPreferences.getGroups().add(Group.of(Setting.of("Datasource type",dbType.idProperty())));

        this.getStage().setResizable(false);

        this.getStage().initModality(Modality.APPLICATION_MODAL);

        this.dbTypes = new HashMap<>();
     }
    public void loginError(String error){
        errorlabel.setText(error);
    }

    public void loginButtonPress(ActionEvent actionEvent)
    {
        if(loginProcessor.tryLogin(this))
        {
            this.submitted = true;
            super.getStage().close();
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

    public int getDBType(){
        if(dbType.getValue() == null) return -1;
        for(String type : dbTypes.keySet()) {
            if(dbType.getValue().equals(type)) return dbTypes.get(type);
        }
        return -1;
    }

    @Override
    public void showAndWait(ILoginProcessor loginProcessor)
    {
        this.loginProcessor = loginProcessor;
        showAndWait();
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

    @Override
    public void addDatabaseType(String type, int typeValue)
    {
        dbTypes.put(type,typeValue);
        dbType.getItems().add(type);
    }

    public boolean getCancelled(){
        return cancelled;
    }

    @Override
    public void refreshData()
    {

    }
}
