package dev.toliner.calcgw.core.operations;

import dev.toliner.calcgw.core.Expression;
import dev.toliner.calcgw.core.Operation;
import dev.toliner.calcgw.core.Value;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class MinusOperation implements Operation {

    private Expression first, second;

    public MinusOperation(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @NotNull
    @Contract("_, _ -> new")
    public static MinusOperation of(Expression first, Expression second) {
        return new MinusOperation(first, second);
    }

    @Override
    public @NotNull Value eval() {
        return null;
    }

    @Override
    public @NotNull String asString() {
        return null;
    }
}
