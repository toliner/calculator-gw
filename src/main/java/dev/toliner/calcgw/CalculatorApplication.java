package dev.toliner.calcgw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculatorApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // File Read
        Parent root = FXMLLoader.load(ClassLoader.getSystemResource("main.fxml"));
        primaryStage.setTitle("Calculator");
        var scene = new Scene(root);
        // File Read
        scene.getStylesheets().add(ClassLoader.getSystemResource("main.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
