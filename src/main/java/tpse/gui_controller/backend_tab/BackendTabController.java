package tpse.gui_controller.backend_tab;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import tpse.windows_layer_connector.sap.Sessions;
import tpse.windows_layer_connector.sap.Session;

import java.util.*;
import java.util.stream.Collectors;

public class BackendTabController {

    private static List<Session> tmpListOfSessions;

    @FXML
    private ListView backendListView;
    @FXML
    private Button connectToSapButton;

    @FXML
    private void populateListOfSapWindows() {
        backendListView.getItems().clear();
        backendListView.getItems().addAll(
                initializeListOfSessions().stream()
                        .map(Session::getWindowTitle)
                        .collect(Collectors.toList())
        );
        connectToSapButton.setText("Refresh");
    }

    private List<Session> initializeListOfSessions() {
        tmpListOfSessions = Sessions.getAllCurrentSessions();
        return tmpListOfSessions;
    }

    @FXML
    private void sessionsListOnMouseClick() {

        System.out.println(tmpListOfSessions.get(backendListView.getSelectionModel().getSelectedIndex()).getTransactionName());
    }


}


