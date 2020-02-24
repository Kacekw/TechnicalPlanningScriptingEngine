package tpse.updater.deploying;

import javafx.application.Platform;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class UpdaterDeploy {

    /**
     * Class used strictly for deploying another java file to windows temporary folder.
     * That java file has the ability to download and replace this file so that it is a simple updater engine.
     * Updater.jar takes one parameter that should contain path to the file that is going to be replaced,
     * it will run and then wait until it has writing access to given path (so that it can delete the file).
     * <p>
     * Class first check if updater is already deployed in windows temporary folder, deploys it when needed,
     * then it executes the updater.jar -localFilePath,
     * and then performs Platform.exit(); so that updater can remove the app and copy newer version in its place.
     * <p>
     * System.exit(0); is just a support if Platform.exit() would not execute.
     **/

    private static final String UPDATER_FILE_SUFFIX = "jar";
    private static final String UPDATER_FILE_NAME = "updater" + "." + UPDATER_FILE_SUFFIX;
    private static final String PATH_TO_UPDATER = "/updater/" + UPDATER_FILE_NAME;
    private final String temporaryDirectory = System.getProperty("java.io.tmpdir");
    private final File updaterFileInTemporaryFolder = new File(temporaryDirectory + UPDATER_FILE_NAME);

    public void update() {
        if (!updaterIsDeployed()) {
            deployUpdater();
        }

        runUpdater();

        Platform.exit();
        System.exit(0);
    }

    //TODO implement a checking mechanism so that return value would be used properly
    private boolean runUpdater() {
        try {
            File localFile = new File(UpdaterDeploy.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", updaterFileInTemporaryFolder.getPath().replace("\\", "/"),
                    localFile.getPath().replace("\\", "/"));
            pb.inheritIO();
            pb.start();
            return true;
        } catch (IOException | URISyntaxException e) {
            //TODO get rid of printing stacktrace after testing the app
            e.printStackTrace();
            return false;
        }
    }

    //TODO implement a checking mechanism so that return value would be used properly
    private boolean deployUpdater() {
        try {
            InputStream inputStream = getClass().getResource(
                    PATH_TO_UPDATER
            ).openStream();

            Files.copy(inputStream, updaterFileInTemporaryFolder.toPath());

            return true;
        } catch (IOException e) {
            //TODO get rid of printing stacktrace after testing the app
            e.printStackTrace();
            return false;
        }
    }

    private boolean updaterIsDeployed() {
        return updaterFileInTemporaryFolder.exists();
    }
}
