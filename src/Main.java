import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        if(program.AppFacade.ShowLogin()) {
            program.AppFacade.showMain();
        }
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
