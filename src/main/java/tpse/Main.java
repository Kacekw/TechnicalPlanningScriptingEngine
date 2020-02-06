package tpse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tpse.updater.new_version_checker.CheckForUpdates;
import tpse.windows_layer_connector.JacobLoader;
import tpse.windows_layer_connector.sap.Sessions;

import java.util.Objects;

public class Main extends Application {

    private static JacobLoader jacobLoader = new JacobLoader();

    private final String TITLE_BAR_TEXT = "Technical Planning Sripting Engine";
    private final String MAIN_FXML_PATH = "/fxml/main.fxml";
    private final String APP_ICON_PATH = "ico/tpse.png";


    public static void main(String[] args) {
        new Thread(() -> jacobLoader.loadLibrary()).start();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(MAIN_FXML_PATH));
        primaryStage.setTitle(TITLE_BAR_TEXT);
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(APP_ICON_PATH))));
        primaryStage.show();

        CheckForUpdates.checkForUpdate();

    }


}

