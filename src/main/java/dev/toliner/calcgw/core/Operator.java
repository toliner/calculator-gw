package dev.toliner.calcgw.core;

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
}
