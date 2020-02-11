package tpse.gui_controller.backend_tab;


import com.jacob.com.ComFailException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private ImageView imagePreview;


    @FXML
    private void populateListOfSapWindows() {
        backendListView.getItems().clear();
        backendListView.getItems().addAll(
                initializeListOfSessions().stream()
                        .map(Session::getWindowTitle)
                        .collect(Collectors.toList())
        );

        if (!connectToSapButton.getText().equalsIgnoreCase("refresh")) {
            connectToSapButton.setText("Refresh");
        }
    }

    private List<Session> initializeListOfSessions() {
        tmpListOfSessions = Sessions.getAllCurrentSessions();
        return tmpListOfSessions;
    }

    @FXML
    private void sessionsListOnMouseClick(MouseEvent mouseEvent) {

        Integer selectedItemOnListIndex = backendListView.getSelectionModel().getSelectedIndex();

        try {
            String transaction = tmpListOfSessions.get(selectedItemOnListIndex).getTransactionName();
            String windowTitle = tmpListOfSessions.get(selectedItemOnListIndex).getWindowTitle();

            showPreviewImage(tmpListOfSessions.get(selectedItemOnListIndex));
        } catch (ComFailException cfe) {
            populateListOfSapWindows();
        }

    }

    private void showPreviewImage(Session session) {
        imagePreview.setPreserveRatio(true);
        imagePreview.setFitWidth(backendListView.getWidth());
        imagePreview.setImage(session.getPreviewImageOfSapSession());
    }
}


