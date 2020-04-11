import gui.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        //loader.setController(controller);
        loader.setLocation(getClass().getResource("sample\\sample.fxml"));

        Parent root = loader.<Parent>load();
        Controller controller = loader.getController();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));

        //ObservableList<String> items = FXCollections.observableArrayList("TEST","TEST");

        //controller.dbType.setItems(FXCollections.observableArrayList("TEST", "TEST2"));
        //dbType.setItems(items);
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
