package dev.toliner.calcgw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculatorApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(ClassLoader.getSystemResource("Main.fxml"));
        primaryStage.setTitle("Calculator");
        var scene = new Scene(root);
        scene.getStylesheets().add(ClassLoader.getSystemResource("main.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
