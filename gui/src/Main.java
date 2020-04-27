import db.DatabaseFactory;
import facade.DoLogin;
import gui.ConsoleIO;
import gui.MainWindowController;
import javafx.application.Application;
import javafx.stage.Stage;
import login.LoginController;
import org.fusesource.jansi.AnsiConsole;
import facade.AppFacade;
import splash.SplashLoader;

import java.io.IOException;

public class Main extends Application
{
    DoLogin doLogin;
    MainWindowController window;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        new SplashLoader(primaryStage,() -> {
                if (doLogin.DoLogin(new LoginController(), new DatabaseFactory()))
                {
                    window = new MainWindowController();
                    window.showAndWait();
                    //AppFacade.showMain(new ConsoleIO(),doLogin.getSqlConnection());
                }
                AnsiConsole.systemUninstall();

        },3, (int task) -> {  // program initialization steps.
            switch(task)
            {
                case 1: AnsiConsole.systemInstall(); return "Loading windows";
                case 2: doLogin = new DoLogin();

                    return "Loaded windows...";
                case 3:

                    return "Completed.";

            }
            return "";
        });

    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
