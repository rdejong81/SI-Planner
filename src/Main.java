import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    public AppFacade app;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        AppFacade app = new AppFacade();
        app.LoginProcedure();

    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
