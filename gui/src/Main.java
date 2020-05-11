import db.DatabaseFactory;
import gui.Controller;
import gui.MainWindowController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import login.LoginController;
import facade.AppFacade;
import splash.SplashLoader;

import java.io.IOException;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.getIcons().add(new Image(Controller.class.getResourceAsStream("siplanner-small.png")));
        new SplashLoader(primaryStage,() -> {

                if (AppFacade.appFacade.DoLogin(new LoginController()))
                {
                    AppFacade.appFacade.showMain(new MainWindowController());
                }

        },3, (int task) -> {  // program initialization steps.
            switch(task)
            {
                case 1: return "Loading windows";
                case 2: AppFacade.appFacade = new AppFacade(new DatabaseFactory());

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
