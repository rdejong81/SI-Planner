import facade.DoLogin;
import gui.ConsoleIO;
import javafx.application.Application;
import javafx.stage.Stage;
import login.LoginController;
import org.fusesource.jansi.AnsiConsole;
import facade.AppFacade;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        AnsiConsole.systemInstall();
        DoLogin doLogin = new DoLogin();

        if(doLogin.DoLogin(new LoginController())) {
            AppFacade.showMain(new ConsoleIO(),doLogin.getSqlConnection());
        }
        AnsiConsole.systemUninstall();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
