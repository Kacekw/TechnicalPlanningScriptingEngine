package tpse.gui_controller.backend_tab;


import com.jacob.activeX.ActiveXComponent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import tpse.windows_layer_connector.sap.Sessions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BackendTabController {

    @FXML
    private ListView backendListView;

    @FXML
    private void initializeListOfSessions() {
        Map<ActiveXComponent, String> tmpListOfSessions = Sessions.getAllCurrentSessions();
        List<String> tmpListOfWindowHeaders = new ArrayList<>(tmpListOfSessions.values());
        System.out.println(tmpListOfWindowHeaders);
        backendListView.getItems().addAll(tmpListOfWindowHeaders);
        backendListView.autosize();
    }

}
