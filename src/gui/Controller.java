package gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class Controller implements Initializable
{

    private Stage stage;

    private Scene scene;

    @FXML
    private Pane pane;

    private FXMLLoader loader;

    Controller(String fxml, String windowTitle) throws IOException
    {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));

       // override creation of controller instance, use current instance instead of creating a new one.
        loader.setControllerFactory(c -> {
            return this;
        });

        Parent newParent = loader.<Parent>load();

        stage = new Stage();
        scene = new Scene(newParent);
        stage.setTitle(windowTitle);
        stage.setScene(scene);

    }

    @Override
    final public void initialize(URL url, ResourceBundle resourceBundle)
    {
        onLoaded(url,resourceBundle);

    }

    abstract protected void onLoaded(URL url, ResourceBundle resourceBundle);
    abstract protected void onClosed();

    final public void showAndWait()
    {
        this.stage.showAndWait();
        this.onClosed();
    }

    public Stage getStage()
    {
        return stage;
    }

    public Scene getScene()
    {
        return scene;
    }

    public Pane getPane()
    {
        return pane;
    }

}


