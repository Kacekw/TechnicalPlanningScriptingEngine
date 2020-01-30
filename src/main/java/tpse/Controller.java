package tpse;

import com.jacob.activeX.ActiveXComponent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tpse.windows_layer_connector.sap.SapConnection;

public class Controller {

    @FXML
    private Button connectButton;
    @FXML
    private Label sapConnectedBottomLeftLabel;

    public void checkVersion() {
        //TODO remove that part when finished with configuring sap connections details
        connectButton.setOnAction(e -> {
            System.out.println("Connecting to SAPGui");
            SapConnection sapConnection = new SapConnection();
            ActiveXComponent sapGui = sapConnection.getSapGui();
            String connectionDetails = String.format("Connected to Sap version %s.%s", sapGui.getProperty("MajorVersion"), sapGui.getProperty("MinorVersion"));
            sapConnectedBottomLeftLabel.setText(connectionDetails);
            System.out.println(connectionDetails);
            sapConnection.disconnect();
            System.out.println("Disconnected from SAP");
        });
    }
}
