package tpse.updater.new_version_checker;


import tpse.gui_controller.popups.update.Update;

import java.io.File;
import java.net.URISyntaxException;

public class CheckForUpdates {

    private static final String REMOTE_FILE_PATH = "C:\\IntelliJProjects\\TechnicalPlanningScriptingEngine\\target\\TPSE-0.0.1-SNAPSHOT.jar";
    private static final long REMOTE_FILE_MODIFIED_DATE = new File(REMOTE_FILE_PATH).lastModified();
    private static final long LOCAL_FILE_MODIFIED_DATE = getLocalFileModifiedDate();

    public static boolean updateIsAvailable() {
        return REMOTE_FILE_MODIFIED_DATE != LOCAL_FILE_MODIFIED_DATE && REMOTE_FILE_MODIFIED_DATE != 0;
    }

    private static long getLocalFileModifiedDate() {
        try {
            File localFile = new File(CheckForUpdates.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            return localFile.lastModified();
        } catch (URISyntaxException use) {
            use.printStackTrace();
            return 0;
        }
    }

    public static void handleVersionControl() {
        if (updateIsAvailable()) {
            Update update = new Update();
            update.showUpdatePopup();
        }
    }
}
