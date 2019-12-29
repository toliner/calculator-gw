package dev.toliner.calcgw.ui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

public class MainView {
    public Label answerLabel;

    // Number Buttons
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

    // Operators
    public JFXButton plusMinus;
    public JFXButton plus;
    public JFXButton minus;
    public JFXButton times;
    public JFXButton equal;
    public JFXButton divide;
    public JFXButton bracketBegin;
    public JFXButton bracketEnd;
    public JFXButton clear;
    public JFXButton delete;

    // Panes
    public GridPane baseGrid;
    public VBox baseVBox;

    @FXML
    private void initialize() {
        var const1 = new ColumnConstraints();
        const1.setPercentWidth(25.0);
        baseGrid.getColumnConstraints().addAll(const1, const1, const1, const1);
    }

    public void onNumberClicked(@NotNull MouseEvent mouseEvent) {
        var button = (JFXButton) mouseEvent.getTarget();
        answerLabel.setText(answerLabel.getText() + button.getText());
    }
}
