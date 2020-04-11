package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class Controller implements Initializable
{


    private Stage stage;

    private Scene scene;

    @FXML
    private Pane pane;


    @Override
    final public void initialize(URL url, ResourceBundle resourceBundle)
    {
        pane.sceneProperty().addListener(
                (scene, oldScene, newScene) ->
                {
                    if (oldScene == null && newScene != null)
                    {
                        this.scene = newScene;

                        this.scene.windowProperty().addListener(
                                (stage, oldStage, newStage) ->
                                {
                                    if (oldStage == null && newStage != null)
                                    {
                                        this.stage = (Stage) newStage;
                                        this.onLoaded(url,resourceBundle);
                                    }
                                }
                        );
                    }
                }
        );
    }

    abstract protected void onLoaded(URL url, ResourceBundle resourceBundle);

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


