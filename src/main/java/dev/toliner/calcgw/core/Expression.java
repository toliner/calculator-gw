package dev.toliner.calcgw.core;

import org.jetbrains.annotations.NotNull;

public interface Expression {
    @NotNull
    Expression eval();

    @NotNull
    String asString();
}
