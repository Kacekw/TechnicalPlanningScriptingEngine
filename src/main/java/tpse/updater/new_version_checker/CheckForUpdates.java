package tpse.updater.new_version_checker;


import tpse.gui_controller.popups.update.Update;

import java.io.File;
import java.net.URISyntaxException;

public class CheckForUpdates {

    public static final String REMOTE_FILE_PATH = "\\\\DKRDSDFSROOT10\\Data\\_WFS\\PL_SDC_TPL\\SAP manuale i skrypty\\KAWIT\\TPSE.jar";
    public static String LOCAL_FILE_PATH;
    private static long REMOTE_FILE_MODIFIED_DATE;
    private static long LOCAL_FILE_MODIFIED_DATE;


    public static boolean updateIsAvailable() {
        return REMOTE_FILE_MODIFIED_DATE != LOCAL_FILE_MODIFIED_DATE && REMOTE_FILE_MODIFIED_DATE != 0;
    }

    private static void getRemoteFileModifiedDate() {
        REMOTE_FILE_MODIFIED_DATE = new File(REMOTE_FILE_PATH).lastModified();
    }

    private static void getLocalFileModifiedDate() {
        try {
            LOCAL_FILE_PATH = CheckForUpdates.class.getProtectionDomain().getCodeSource().getLocation().toURI().toString();
            File localFile = new File(CheckForUpdates.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            LOCAL_FILE_MODIFIED_DATE = localFile.lastModified();
        } catch (URISyntaxException use) {
            use.printStackTrace();
        }
    }

    public static void checkForUpdate() {
        getRemoteFileModifiedDate();
        getLocalFileModifiedDate();

        if (updateIsAvailable()) {
            Update update = new Update();
            update.showUpdatePopup();
        }
    }
}
