package dev.toliner.calcgw.core;

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

    public final String displayText;

    Token(String displayText) {
        this.displayText = displayText;
    }
}
