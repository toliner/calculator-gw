package dev.toliner.calcgw.core.operations;

import dev.toliner.calcgw.core.Expression;
import dev.toliner.calcgw.core.Operation;
import dev.toliner.calcgw.core.Value;
import org.jetbrains.annotations.NotNull;

public class TimesOperation implements Operation {

    private Expression first, second;

    public TimesOperation(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    public static TimesOperation of(Expression first, Expression second) {
        return new TimesOperation(first, second);
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
