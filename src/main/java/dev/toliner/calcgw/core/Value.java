package dev.toliner.calcgw.core;

import org.jetbrains.annotations.NotNull;

public interface Value extends Expression {
    @Override
    @NotNull
    default Value eval() {
        return this;
    }
}
