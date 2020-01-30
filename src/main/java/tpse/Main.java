package tpse;

import com.jacob.activeX.ActiveXComponent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tpse.sap.SapConnection;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("Technical Planning Sripting Engine");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("ico/tpse.png"))));
        primaryStage.show();
    }

    public static void main(String[] args) {

        //TODO remove that part when finished with configuring sap connections details
        System.out.println("Connecting to SAPGui");
        SapConnection sapConnection = new SapConnection();
        ActiveXComponent sapGui = sapConnection.getSapGui();
        String connectionDetails = String.format("Connected to Sap version %s.%s", sapGui.getProperty("MajorVersion"), sapGui.getProperty("MinorVersion"));
        System.out.println(connectionDetails);
        sapConnection.disconnect();
        System.out.println("Disconnected from SAP");

        launch(args);
    }
}
