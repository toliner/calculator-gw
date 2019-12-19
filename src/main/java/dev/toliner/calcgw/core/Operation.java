package dev.toliner.calcgw.core;

import org.jetbrains.annotations.NotNull;

public interface Operation extends Expression {
    @Override
    @NotNull
    Value eval();
}
