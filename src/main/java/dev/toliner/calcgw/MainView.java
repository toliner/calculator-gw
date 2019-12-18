package dev.toliner.calcgw;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class MainView {
    public JFXButton plusMinus;
    public JFXButton zero;
    public JFXButton one;
    public JFXButton two;
    public JFXButton three;
    public JFXButton four;
    public JFXButton five;
    public JFXButton six;
    public JFXButton seven;
    public JFXButton eight;
    public JFXButton nine;
    public JFXButton plus;
    public JFXButton minus;
    public JFXButton times;
    public JFXButton dot;
    public JFXButton equal;
    public VBox baseVBox;
    public HBox buttonHBox0;
    public HBox buttonHBox1;
    public HBox buttonHBox2;
    public Label answerLabel;

    @FXML
    private void initialize() {
        var resizableList = List.of(
                zero, one, two, three, four, five, six, seven, eight, nine,
                plus, minus, dot, equal, times, plusMinus,
                buttonHBox0, buttonHBox1, buttonHBox2
        );
        for (var button : resizableList) {
            VBox.setVgrow(button, Priority.ALWAYS);
            HBox.setHgrow(button, Priority.ALWAYS);
        }
        VBox.setVgrow(answerLabel, Priority.NEVER);
    }
}
