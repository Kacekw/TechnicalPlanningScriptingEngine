package tpse.updater.new_version_checker;

import tpse.gui_controller.popups.update.Update;

import java.io.File;
import java.net.URISyntaxException;

public class CheckForUpdates {

    // TODO remote file path is hardcoded here as a proof of concept, put it somewhere else
    private static final String REMOTE_FILE_PATH = "\\\\DKRDSDFSROOT10\\Data\\_WFS\\PL_SDC_TPL\\SAP manuale i skrypty\\KAWIT\\TPSE.jar";
    private static long REMOTE_FILE_MODIFIED_DATE;
    private static long LOCAL_FILE_MODIFIED_DATE;


    public static void checkForUpdate() {
        getRemoteFileModifiedDate();
        getLocalFileModifiedDate();

        if (REMOTE_FILE_MODIFIED_DATE != LOCAL_FILE_MODIFIED_DATE) {
            if (REMOTE_FILE_MODIFIED_DATE != 0) {
                Update update = new Update();
                update.showUpdatePopup();
            }
        }
    }

    private static void getRemoteFileModifiedDate() {
        REMOTE_FILE_MODIFIED_DATE = new File(REMOTE_FILE_PATH).lastModified();
    }

    private static void getLocalFileModifiedDate() {
        try {
            File localFile = new File(CheckForUpdates.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            LOCAL_FILE_MODIFIED_DATE = localFile.lastModified();
        } catch (URISyntaxException use) {
            use.printStackTrace();
        }
    }
}
