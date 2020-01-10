package dev.toliner.calcgw.core;

import dev.toliner.calcgw.core.operations.DivideOperation;
import dev.toliner.calcgw.core.operations.MinusOperation;
import dev.toliner.calcgw.core.operations.PlusOperation;
import dev.toliner.calcgw.core.operations.TimesOperation;
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
        if (!tokenList.isEmpty()) {
            tokenList.remove(tokenList.size() - 1);
        } else {
            labelProperty.setValue("");
        }
    }

    public void clearTokenList() {
        tokenList.clear();
        labelProperty.setValue("");
    }

    public void eval() {
        try {
            var expression = analyze();
            var result = expression.eval();
            tokenList.clear();
            labelProperty.setValue(result.asString());
        } catch (Exception e) {
            labelProperty.setValue("ERROR: " + e.getMessage());
        }
    }


    private Expression analyze() {
        var rpn = convertTokenToRPN();

        Deque<Expression> stack = new LinkedList<>();
        for (Object o : rpn) {
            if (o instanceof Integer) {
                stack.addFirst(new IntegerValue((Integer) o));
            } else {
                Operator op = (Operator) o;
                Expression exp;
                switch (op) {
                    case PLUS: {
                        var second = stack.removeFirst();
                        var first = stack.removeFirst();
                        exp = PlusOperation.of(first, second);
                        break;
                    }
                    case MINUS: {
                        var second = stack.removeFirst();
                        var first = stack.removeFirst();
                        exp = MinusOperation.of(first, second);
                        break;
                    }
                    case TIMES: {
                        var second = stack.removeFirst();
                        var first = stack.removeFirst();
                        exp = TimesOperation.of(first, second);
                        break;
                    }
                    case DIVIDE: {
                        var second = stack.removeFirst();
                        var first = stack.removeFirst();
                        exp = DivideOperation.of(first, second);
                        break;
                    }
                    case POWER:
                    default:
                        throw new RuntimeException("TODO");
                }
                stack.addFirst(exp);
            }
        }
        return stack.getFirst();
    }

    /**
     * 「操車場アルゴリズム」
     * https://ja.m.wikipedia.org/wiki/%E6%93%8D%E8%BB%8A%E5%A0%B4%E3%82%A2%E3%83%AB%E3%82%B4%E3%83%AA%E3%82%BA%E3%83%A0
     *
     * @return トークンを後置記法(逆ポーランド記法, RPN)のQueueに変換した結果
     * @throws IllegalSyntaxException 構文に異常があった時
     */
    private Deque<Object> convertTokenToRPN() {
        // flash number stackを関数内定義の関数にしたい。ローカル変数管理なのでコピペする羽目になってる。オブジェクト化する？
        Deque<Integer> numberQueue = new LinkedList<>();
        Deque<Object> outputQueue = new LinkedList<>();
        Deque<Token> operatorStack = new LinkedList<>();
        for (Token token : tokenList) {
            if (Token.numberTokens.contains(token)) {
                numberQueue.addLast(Token.getValueOfToken(token));
            } else {
                switch (token) {
                    case PLUS:
                    case MINUS:
                    case TIMES:
                    case DIVIDE: {
                        // flash number stack
                        if (!numberQueue.isEmpty()) {
                            var n = numberQueue.removeFirst();
                            while (!numberQueue.isEmpty()) {
                                n = n * 10 + numberQueue.removeFirst();
                            }
                            outputQueue.addLast(n);
                        }
                        var op = Operator.fromToken(token);
                        // 本来ならoperatorStack.getFirst()がOperatorである事の確認をすべき。
                        while (!operatorStack.isEmpty() && operatorStack.getFirst() != Token.BRACKET_BEGIN && op.priority <= Operator.fromToken(operatorStack.getFirst()).priority) {
                            outputQueue.addLast(Operator.fromToken(operatorStack.removeFirst()));
                        }
                        operatorStack.addFirst(token);
                        break;
                    }
                    case POWER: {
                        // flash number stack
                        if (!numberQueue.isEmpty()) {
                            var n = numberQueue.removeFirst();
                            while (!numberQueue.isEmpty()) {
                                n = n * 10 + numberQueue.removeFirst();
                            }
                            outputQueue.addLast(n);
                        }
                        var op = Operator.fromToken(token);
                        while (!operatorStack.isEmpty() && op.priority < Operator.fromToken(operatorStack.getFirst()).priority) {
                            outputQueue.addLast(Operator.fromToken(operatorStack.removeFirst()));
                        }
                        operatorStack.addFirst(token);
                        break;
                    }
                    case BRACKET_BEGIN: {
                        operatorStack.addFirst(token);
                        break;
                    }
                    case BRACKET_END: {
                        // flash number stack
                        if (!numberQueue.isEmpty()) {
                            var n = numberQueue.removeFirst();
                            while (!numberQueue.isEmpty()) {
                                n = n * 10 + numberQueue.removeFirst();
                            }
                            outputQueue.addLast(n);
                        }
                        var op = operatorStack.removeFirst();
                        while (op != Token.BRACKET_BEGIN) {
                            outputQueue.addLast(Operator.fromToken(op));
                            // 左括弧が出るまではoperatorが積まれているはず
                            // そうでなければエラー
                            op = operatorStack.removeFirst();
                        }
                        break;
                    }
                }
            }
        }
        // flash number stack
        if (!numberQueue.isEmpty()) {
            var n = numberQueue.removeFirst();
            while (!numberQueue.isEmpty()) {
                n = n * 10 + numberQueue.removeFirst();
            }
            outputQueue.addLast(n);
        }
        // flash operator stack
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
