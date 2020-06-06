package Main;

import Database.DatabaseFactory;
import Windows.Controller;
import Windows.MainWindowController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Login.LoginController;
import Facade.AppFacade;
import Splash.SplashLoader;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage)
    {

        primaryStage.getIcons().add(new Image(Controller.class.getResourceAsStream("siplanner-small.png")));
        new SplashLoader(primaryStage,() -> {
            LoginController login = new LoginController();
            for(String dbType : AppFacade.appFacade.getDatabaseTypes())
                login.addDbType(dbType);
            login.showAndWait();
                if (!login.getCancelled())
                {
                    MainWindowController mainWindowController = new MainWindowController();
                    mainWindowController.showAndWait();
                }

        },3, (int task) -> {  // program initialization steps.
            switch(task)
            {
                case 1:// preferencesFx = PreferencesFx.of(Main.class,null);
               // preferencesFx.
                    Platform.runLater(()->{

                    });
                return "Loading windows";
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
