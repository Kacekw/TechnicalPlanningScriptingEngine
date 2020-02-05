package tpse.gui_controller.popups.update;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import tpse.updater.deploying.UpdaterDeploy;

import java.util.Optional;


public class Update {

    private final String UPDATE_POPUP_TITLE = "Newer version is available";
    private final String UPDATE_POPUP_HEADER = "Update is available, do you want to update?";
    private final String UPDATE_POPUP_CONTEXT = "Choose ok to download and restart the application.";

    public void showUpdatePopup() {
        Alert updatePopup = new Alert(Alert.AlertType.CONFIRMATION);
        updatePopup.setTitle(UPDATE_POPUP_TITLE);
        updatePopup.setHeaderText(UPDATE_POPUP_HEADER);
        updatePopup.setContentText(UPDATE_POPUP_CONTEXT);
//        updatePopup.initOwner(updatePopup.getOwner());

        Optional<ButtonType> result = updatePopup.showAndWait();
        if (result.get() == ButtonType.OK) {

            UpdaterDeploy ud = new UpdaterDeploy();
            ud.update();
            updatePopup.close();
        }
    }
}
