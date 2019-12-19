package dev.toliner.calcgw.core.values;

import dev.toliner.calcgw.core.Value;
import org.jetbrains.annotations.NotNull;

public class IntegerValue implements Value {

    public final int value;

    public IntegerValue(int value) {
        this.value = value;
    }

    @Override
    public @NotNull String asString() {
        // ToDo
        return null;
    }
}
