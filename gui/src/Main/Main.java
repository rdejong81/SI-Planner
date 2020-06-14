package Main;

import Database.DatabaseFactory;
import Facade.ISQLConnectionFactory;
import Facade.InvoiceConnectionFactory;
import Invoice.InvoiceFactory;
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

        final ISQLConnectionFactory[] sqlConnectionFactory = new ISQLConnectionFactory[1];
        final InvoiceConnectionFactory[] invoiceConnectionFactory = new InvoiceConnectionFactory[1];

        primaryStage.getIcons().add(new Image(Controller.class.getResourceAsStream("siplanner-small.png")));
        new SplashLoader(primaryStage,() -> {
            LoginController login = new LoginController(sqlConnectionFactory[0]);
            for(String dbType : AppFacade.appFacade.getDatabaseTypes())
                login.addDbType(dbType);
            login.showAndWait();
                if (!login.getCancelled())
                {
                    MainWindowController mainWindowController = new MainWindowController();
                    mainWindowController.showAndWait();
                    AppFacade.appFacade.getDataSource().closeConnection();
                }

        },3, (int task) -> {  // program initialization steps.
            switch(task)
            {
                case 1: sqlConnectionFactory[0] = new DatabaseFactory();
                invoiceConnectionFactory[0] = new InvoiceFactory();
                return "Loading windows";
                case 2: AppFacade.appFacade = new AppFacade(sqlConnectionFactory[0],invoiceConnectionFactory[0]);

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
