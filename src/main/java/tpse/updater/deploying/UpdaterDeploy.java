package tpse.updater.deploying;

import javafx.application.Application;
import javafx.application.Platform;
import tpse.updater.new_version_checker.CheckForUpdates;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class UpdaterDeploy {

    public static final String UPDATER_FILE_SUFFIX = "jar";
    public static final String UPDATER_FILE_NAME = "updater" + "." + UPDATER_FILE_SUFFIX;
    public static final String PATH_TO_UPDATER = "/updater/" + UPDATER_FILE_NAME;
    private final String temporaryDirectory = System.getProperty("java.io.tmpdir");
    private final File updaterFileInTemporaryFolder = new File(temporaryDirectory + UPDATER_FILE_NAME);

    public void update() {
        System.out.println("Checking if file exists in " + updaterFileInTemporaryFolder.getPath());
        if (!updaterIsDeployed()) {
            System.out.println("Jar was not found, deploying...");
            deployUpdater();
        }

        runUpdater();

        Platform.exit();
        System.exit(0);
    }

    private boolean runUpdater() {
        try {
            File localFile = new File(UpdaterDeploy.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            System.out.println("Running " + "java " + "-jar " + updaterFileInTemporaryFolder.getPath().replace("\\", "/") + " " +
                    localFile.getPath().replace("\\", "/"));
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", updaterFileInTemporaryFolder.getPath().replace("\\", "/"),
                    localFile.getPath().replace("\\", "/"));
            pb.inheritIO();
            pb.start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (URISyntaxException use) {
            use.printStackTrace();
            return false;
        }
    }

    private boolean deployUpdater() {
        try {
            InputStream inputStream = getClass().getResource(
                    PATH_TO_UPDATER
            ).openStream();

            Files.copy(inputStream, updaterFileInTemporaryFolder.toPath());

            return true;
        } catch (IOException e) {
            System.out.println("Error in deployUpdater");
            e.printStackTrace();
            return false;
        }
    }

    private boolean updaterIsDeployed() {
        return updaterFileInTemporaryFolder.exists();
    }
}
