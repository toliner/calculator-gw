package dev.toliner.calcgw.core;

import org.jetbrains.annotations.NotNull;

public enum Operator {
    PLUS(10, Associativity.LEFT),
    MINUS(10, Associativity.LEFT),
    TIMES(20, Associativity.LEFT),
    DIVIDE(20, Associativity.LEFT),
    POWER(30, Associativity.RIGHT),
    ;

    public int priority;
    public Associativity associativity;

    Operator(int priority, Associativity associativity) {
        this.priority = priority;
        this.associativity = associativity;
    }

    /**
     * @param token 元のトークン
     * @return 該当するOperator
     * @throws IllegalArgumentException tokenがオペレーターでない時
     */
    public static @NotNull Operator fromToken(@NotNull Token token) {
        Operator ret;
        switch (token) {
            case PLUS:
                ret = PLUS;
                break;
            case MINUS:
                ret = MINUS;
                break;
            case TIMES:
                ret = TIMES;
                break;
            case DIVIDE:
                ret = DIVIDE;
                break;
            case POWER:
                ret = POWER;
                break;
            default:
                throw new IllegalArgumentException("Token[" + token + "] is not operator");
        }

        return ret;
    }
}
