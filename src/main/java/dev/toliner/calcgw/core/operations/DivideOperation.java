package dev.toliner.calcgw.core.operations;

import dev.toliner.calcgw.core.Expression;
import dev.toliner.calcgw.core.Operation;
import dev.toliner.calcgw.core.Operator;
import dev.toliner.calcgw.core.Value;
import org.jetbrains.annotations.NotNull;

public class DivideOperation implements Operation {

    private Expression first, second;

    public DivideOperation(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    public static DivideOperation of(Expression first, Expression second) {
        return new DivideOperation(first, second);
    }

    @Override
    public @NotNull Value eval() {
        return null;
    }

    @Override
    public @NotNull String asString() {
        return null;
    }

    @Override
    public @NotNull Operator getOperator() {
        return null;
    }
}
