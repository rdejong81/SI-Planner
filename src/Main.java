import javafx.application.Application;
import javafx.stage.Stage;
import org.fusesource.jansi.AnsiConsole;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        AnsiConsole.systemInstall();
        if(program.AppFacade.ShowLogin()) {
            program.AppFacade.showMain();
        }
        AnsiConsole.systemUninstall();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
