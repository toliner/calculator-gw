package dev.toliner.calcgw.core;

import java.util.Set;

public enum Token {
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVIDE("/"),
    BRACKET_BEGIN("("),
    BRACKET_END(")"),
    POWER("^"),

    ;

    public static Set<Token> numberTokens = Set.of(ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE);
    public static Set<Token> operatorTokens = Set.of(PLUS, MINUS, TIMES, DIVIDE, POWER);

    public static int getValueOfToken(Token token) {
        switch (token) {
            case ZERO:
                return 0;
            case ONE:
                return 1;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            default:
                throw new IllegalArgumentException("Token[" + token + "] is not number token.");
        }
    }

    public final String displayText;

    Token(String displayText) {
        this.displayText = displayText;
    }
}
