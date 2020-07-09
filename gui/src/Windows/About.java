package Windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

import static Facade.AppFacade.APPVERSION;



public class About extends Controller
{
    public class Feature {
        private final String featureName,status;

        private Feature(String featureName, String status)
        {
            this.featureName = featureName;
            this.status = status;
        }

        public String getFeatureName()
        {
            return featureName;
        }

        public String getStatus()
        {
            return status;
        }
    }

    @FXML private TextArea abouttxt;
    @FXML private TableView<Feature> features;
    @FXML private TableColumn<Feature,String> featureColumn,statusColumn;

    protected About(Controller owner)
    {
        super("About.fxml", "About");
        getStage().initOwner(owner.getStage());
        getStage().initModality(Modality.WINDOW_MODAL);
        this.getStage().setResizable(false);
        this.getStage().initStyle(StageStyle.UTILITY);
        abouttxt.setText(String.format(abouttxt.getText(),APPVERSION));
        featureColumn.setSortable(false);
        statusColumn.setSortable(false);
        featureColumn.setCellValueFactory(new PropertyValueFactory<>("featureName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        features.getItems().add(new Feature("Invoice module: Office Excel","alpha (Office Excel 2010 or higher required)"));
        features.getItems().add(new Feature("Import module: Outlook Calendar online","alpha (Office 365, company and personal)"));
        features.getItems().add(new Feature("Storage module: Sqlite","alpha (local db storage)"));
        features.getItems().add(new Feature("Storage module: MySQL","defunct (shared db storage-needs rework)"));
    }

    @Override
    protected void onResize(double height, double width)
    {

    }

    @Override
    protected void onLoaded(URL url, ResourceBundle resourceBundle)
    {

    }

    @FXML private void onCloseBtn(ActionEvent actionEvent)
    {
        this.getStage().close();
    }

    @Override
    protected void onClosed()
    {

    }
}
