package dev.toliner.calcgw.ui;

import com.jfoenix.controls.JFXButton;
import dev.toliner.calcgw.core.Processor;
import dev.toliner.calcgw.core.Token;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

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
    public JFXButton power;
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

    private Map<JFXButton, Token> buttonTokenMap;

    @FXML
    private void initialize() {
        var const1 = new ColumnConstraints();
        const1.setPercentWidth(25.0);
        baseGrid.getColumnConstraints().addAll(const1, const1, const1, const1);
        buttonTokenMap = new HashMap<>();
        buttonTokenMap.putAll(Map.of(
                zero, Token.ZERO,
                one, Token.ONE,
                two, Token.TWO,
                three, Token.THREE,
                four, Token.FOUR,
                five, Token.FIVE,
                six, Token.SIX,
                seven, Token.SEVEN,
                eight, Token.EIGHT,
                nine, Token.NINE
        ));
        buttonTokenMap.putAll(Map.of(
                plus, Token.PLUS,
                minus, Token.MINUS,
                times, Token.TIMES,
                divide, Token.DIVIDE,
                bracketBegin, Token.BRACKET_BEGIN,
                bracketEnd, Token.BRACKET_END,
                power, Token.POWER
        ));
        StringProperty property = new SimpleStringProperty();
        answerLabel.textProperty().bind(property);
        // property.bind(answerLabel.textProperty());
        Processor.getInstance().setLabelProperty(property);
    }

    @FXML
    private void onButtonClicked(@NotNull MouseEvent mouseEvent) {
        var button = (JFXButton) mouseEvent.getTarget();
        var token = buttonTokenMap.get(button);
        Processor.getInstance().addToken(token);
    }

    @FXML
    private void onDeleteClicked(@NotNull MouseEvent mouseEvent) {
        Processor.getInstance().removeLastToken();
    }

    @FXML
    private void onClearClicked(@NotNull MouseEvent mouseEvent) {
        Processor.getInstance().clearTokenList();
    }

    @FXML
    private void onEqualClicked(@NotNull MouseEvent mouseEvent) {

    }
}
