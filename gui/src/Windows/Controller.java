/*
 *    Copyright © 2020 Richard de Jong
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package Windows;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class Controller implements Initializable
{

    private Stage stage;

    private Scene scene;

    private Parent parent;

    private FXMLLoader loader;

    protected Controller(String fxml, String windowTitle)
    {
        try
        {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxml));

            // override creation of controller instance, use current instance instead of creating a new one.
            loader.setControllerFactory(c ->
            {
                return this;
            });

            parent = loader.load();


            stage = new Stage();
            scene = new Scene(parent);
            stage.setTitle(windowTitle);
            stage.setScene(scene);

            ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->
                    onResize(stage.getHeight(), stage.getWidth());

            stage.widthProperty().addListener(stageSizeListener);
            stage.heightProperty().addListener(stageSizeListener);

            stage.getIcons().add(new Image(Controller.class.getResourceAsStream("siplanner-small.png")));
        } catch (IOException e)
        {
            //todo handle fxml load error.
            System.out.println(e.getMessage());
            System.exit(1); // do not continue.
        }
    }

    abstract protected void onResize(double height, double width);



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

    public Parent getParent()
    {
        return parent;
    }

    protected FXMLLoader getLoader(){
        return loader;
    }

}


