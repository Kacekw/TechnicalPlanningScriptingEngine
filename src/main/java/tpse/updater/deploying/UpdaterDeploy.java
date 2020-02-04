package tpse.updater.deploying;

import tpse.updater.new_version_checker.CheckForUpdates;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class UpdaterDeploy {

    public static final String UPDATER_FILE_NAME = "updater.jar";
    public static final String PATH_TO_UPDATER = "/updater/" + UPDATER_FILE_NAME;
    private final String temporaryDirectory = System.getProperty("java.io.tmpdir");
    private final File updaterFileInTemporaryFolder = new File(temporaryDirectory + UPDATER_FILE_NAME);

    public void start() {
        if (!updaterIsDeployed()) {
            deployUpdater();
        }

        runUpdater();

        System.exit(0);
    }

    private boolean runUpdater() {
        String localPath = UpdaterDeploy.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm();
        System.out.println("java " + "-jar " + updaterFileInTemporaryFolder.getPath() + " " +
                localPath);

        try {
            ProcessBuilder pb = new ProcessBuilder("java " + "-jar " + updaterFileInTemporaryFolder.getPath() + " " +
                    localPath);
            pb.start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } /*catch (URISyntaxException use){
            use.printStackTrace();
            return false;
        }*/
    }

    private boolean deployUpdater() {
        try {
            InputStream inputStream = getClass().getResource(
                    PATH_TO_UPDATER + UPDATER_FILE_NAME
            ).openStream();

            Files.copy(inputStream, updaterFileInTemporaryFolder.toPath());

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean updaterIsDeployed() {
        return updaterFileInTemporaryFolder.exists();
    }
}
