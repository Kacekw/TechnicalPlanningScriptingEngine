package tpse.gui_controller.backend;

import com.jacob.activeX.ActiveXComponent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tpse.windows_layer_connector.sap.SapConnection;

public class BackendTabController {

    @FXML
    private Label sapConnectedBottomLeftLabel;

    public void checkVersion() {
        //TODO remove that part when finished with configuring sap connections details
        System.out.println("Connecting to SAPGui");
        SapConnection sapConnection = new SapConnection();
        ActiveXComponent sapGui = sapConnection.getSapGui();
        String connectionDetails = String.format("Connected to Sap version %s.%s", sapGui.getProperty("MajorVersion"), sapGui.getProperty("MinorVersion"));
        sapConnectedBottomLeftLabel.setText(connectionDetails);
        System.out.println(connectionDetails);
        sapConnection.disconnect();
        System.out.println("Disconnected from SAP");
    }
}
