package gui;

import javafx.fxml.FXML;
import javafx.stage.Modality;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageCustomersController extends Controller
{
    public ManageCustomersController(Controller owner) throws IOException
    {
        super("ManageCustomers.fxml", "Manage Customers");
        getStage().initOwner(owner.getStage());
        getStage().initModality(Modality.WINDOW_MODAL);
        this.getStage().setResizable(false);
    }

    @FXML
    private void closeButtonClick()
    {
        getStage().close();
    }

    @Override
    protected void onResize(double height, double width)
    {

    }

    @Override
    protected void onLoaded(URL url, ResourceBundle resourceBundle)
    {

    }

    @Override
    protected void onClosed()
    {

    }
}
