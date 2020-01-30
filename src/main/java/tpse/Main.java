package tpse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tpse.windows_layer_connector.JacobLoader;

import java.util.Objects;

public class Main extends Application {

    private static JacobLoader jacobLoader = new JacobLoader();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("Technical Planning Sripting Engine");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("ico/tpse.png"))));
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.print("Loading jacob-dll : ");
        System.out.println(jacobLoader.loadLibrary());

        launch(args);
    }
}

