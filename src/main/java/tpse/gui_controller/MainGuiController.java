package tpse.gui_controller;

import com.jacob.activeX.ActiveXComponent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tpse.updater.new_version_checker.CheckForUpdates;
import tpse.windows_layer_connector.sap.SapConnection;

import java.net.URL;
import java.util.ResourceBundle;

public class MainGuiController implements Initializable {

    @FXML
    private Label BottomLeftLabel;
    @FXML
    private Label VersionLabel;
    @FXML
    private BorderPane MainBorderPane;
    @FXML
    private MenuItem StayOnTopMenuItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final String version = String.format("Ver. %s", this.getClass().getPackage().getImplementationVersion());
        VersionLabel.setText(version);
    }

    public void checkVersion() {
        //TODO remove that part when finished with configuring sap connections details
        System.out.println("Connecting to SAPGui");
        SapConnection sapConnection = new SapConnection();
        ActiveXComponent sapGui = null;

        try {
            sapGui = sapConnection.getSapGui();
        } catch (NoSuchFieldException e) {
            setBottomLabelText("Connecting to SAP failed!");
        }

        String connectionDetails = String.format("Connected to Sap version %s.%s", sapGui.getProperty("MajorVersion"), sapGui.getProperty("MinorVersion"));
        setBottomLabelText(connectionDetails);
        System.out.println(connectionDetails);
        sapConnection.disconnect();
        System.out.println("Disconnected from SAP");
    }

    public void setBottomLabelText(String textToShow) {
        BottomLeftLabel.setText(textToShow);
    }

    public void setToStayOnTop() {
        Stage stage = (Stage) MainBorderPane.getScene().getWindow();

        if (stage.alwaysOnTopProperty().getValue()) {
            stage.setAlwaysOnTop(false);
            StayOnTopMenuItem.setText("Stay on top");
        } else {

            stage.setAlwaysOnTop(true);
            StayOnTopMenuItem.setText("Turn off staying on top");
        }
    }

    public void checkForUpdates() {
        CheckForUpdates.checkForUpdate();
    }
}
