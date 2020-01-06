package dev.toliner.calcgw.core;

import dev.toliner.calcgw.core.values.IntegerValue;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.Deque;
import java.util.LinkedList;

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

    // TODO: バリデーション
    public void addToken(Token token) {
        tokenList.add(token);
    }

    public void removeLastToken() {
        tokenList.remove(tokenList.size() - 1);
    }

    public void clearTokenList() {
        tokenList.clear();
    }

    public void eval() {

        try {
            var expression = analyze();
        } catch (Exception e) {

        }
    }


    private Expression analyze() {
        var rpn = convertTokenToRPN();


        throw new RuntimeException();
    }

    /**
     * 「操車場アルゴリズム」
     * https://ja.m.wikipedia.org/wiki/%E6%93%8D%E8%BB%8A%E5%A0%B4%E3%82%A2%E3%83%AB%E3%82%B4%E3%83%AA%E3%82%BA%E3%83%A0
     *
     * @return トークンを後置記法(逆ポーランド記法, RPN)のQueueに変換した結果
     * @throws IllegalSyntaxException 構文に異常があった時
     */
    private Deque<Object> convertTokenToRPN() {
        Deque<Integer> numberStack = new LinkedList<>();
        Deque<Object> outputQueue = new LinkedList<>();
        Deque<Token> operatorStack = new LinkedList<>();
        for (Token token : tokenList) {
            if (Token.numberTokens.contains(token)) {
                numberStack.addFirst(Token.getValueOfToken(token));
            } else {
                var n = numberStack.removeFirst();
                while (!numberStack.isEmpty()) {
                    n = n * 10 + numberStack.removeFirst();
                }
                outputQueue.addLast(new IntegerValue(n));
                switch (token) {
                    case PLUS:
                    case MINUS:
                    case TIMES:
                    case DIVIDE: {
                        var op = Operator.fromToken(token);
                        while (!operatorStack.isEmpty() && op.priority <= Operator.fromToken(operatorStack.getFirst()).priority) {
                            outputQueue.addLast(Operator.fromToken(operatorStack.removeFirst()));
                        }
                        operatorStack.addFirst(token);
                        break;
                    }
                    case POWER: {
                        var op = Operator.fromToken(token);
                        while (!operatorStack.isEmpty() && op.priority < Operator.fromToken(operatorStack.getFirst()).priority) {
                            outputQueue.addLast(Operator.fromToken(operatorStack.removeFirst()));
                        }
                        operatorStack.addFirst(token);
                        break;
                    }
                    case BRACKET_BEGIN: {
                        operatorStack.addFirst(token);
                    }
                    case BRACKET_END: {
                        var op = operatorStack.removeFirst();
                        while (op == Token.BRACKET_BEGIN) {
                            outputQueue.addLast(Operator.fromToken(op));
                            // 左括弧が出るまではoperatorが積まれているはず
                            // そうでなければエラー
                            op = operatorStack.removeFirst();
                        }
                    }
                }
            }
        }
        while (!operatorStack.isEmpty()) {
            var op = operatorStack.removeFirst();
            if (op == Token.BRACKET_BEGIN || op == Token.BRACKET_END) {
                throw new IllegalArgumentException("Syntax Error");
            } else {
                outputQueue.addLast(Operator.fromToken(op));
            }
        }
        return outputQueue;
    }

    public static class IllegalSyntaxException extends RuntimeException {
        public IllegalSyntaxException() {
            super();
        }
    }
}
