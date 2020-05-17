package Splash;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class SplashLoader
{
    private Stage initStage;
    private Task<?> task;
    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private static final int SPLASH_WIDTH = 700;
    private static final int SPLASH_HEIGHT = 527;
    private int numberOfTasks;
    private InitCompletionHandler initCompletionHandler;

    public interface InitCompletionHandler {
        void complete();
    }

    public interface TaskHandler {
        String processTask(int taskNumber);
    }

    public SplashLoader(Stage initStage, InitCompletionHandler initCompletionHandler, int numberOfTasks, TaskHandler taskHandler)
    {
        this.numberOfTasks = numberOfTasks;
        ImageView splash = new ImageView(new Image(
                SplashLoader.class.getResourceAsStream("siplanner.png")
        ));
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
        progressText = new Label("Will find friends for peanuts . . .");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setStyle(
                "-fx-padding: 5; " +
                        "-fx-background-color: cornsilk; " +
                        "-fx-border-width:1; " +
                        "-fx-border-color: " +
                        "linear-gradient(" +
                        "to bottom, " +
                        "chocolate, " +
                        "derive(chocolate, 50%)" +
                        ");"
        );
        splashLayout.setEffect(new DropShadow());

        this.initStage = initStage;
        this.initCompletionHandler = initCompletionHandler;

        task = new Task<ObservableList<String>>() {
        @Override
        public ObservableList<String> call() throws InterruptedException {
            ObservableList<String> foundFriends =
                    FXCollections.<String>observableArrayList();
            for(int i=1;i<=numberOfTasks;i++)
            {
                updateMessage(taskHandler.processTask(i));
                updateProgress(i,numberOfTasks);
                Thread.sleep(1000);
            }

            updateProgress(numberOfTasks,numberOfTasks);
            Thread.sleep(2000);
            return foundFriends;
        }
    };

        showSplash();
        new Thread(task).start();
    }

    private void showSplash() {
        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                loadProgress.progressProperty().unbind();
                loadProgress.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            } // todo add code to gracefully handle other task states.
        });

        Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.setAlwaysOnTop(true);
        initStage.show();
    }

}
