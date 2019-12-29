package dev.toliner.calcgw.core;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Processor {

    private static final Processor instance = new Processor();

    private ObservableList<Token> tokenList = FXCollections.observableArrayList();

    public static Processor getInstance() {
        return instance;
    }

    private StringProperty labelProperty;

    private Processor() {
        tokenList.addListener((ListChangeListener<? super Token>) (change) -> {
            StringBuilder builder = new StringBuilder();
            for (Token token : change.getList()) {
                builder.append(token.displayText);
            }
            labelProperty.setValue(builder.toString());
        });
    }

    public void setLabelProperty(StringProperty property) {
        if (labelProperty == null) {
            labelProperty = property;
        } else {
            throw new IllegalStateException("Single assign");
        }
    }

    public void addToken(Token token) {
        tokenList.add(token);
    }

    public void removeLastToken() {
        tokenList.remove(tokenList.size() - 1);
    }

    public void clearTokenList() {
        tokenList.clear();
    }
}
