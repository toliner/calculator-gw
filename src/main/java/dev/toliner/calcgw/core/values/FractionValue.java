package dev.toliner.calcgw.core.values;

import dev.toliner.calcgw.core.Value;
import org.jetbrains.annotations.NotNull;

// 分数
public class FractionValue implements Value {

    public final int denominator, numerator;

    public FractionValue(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static FractionValue of(Value value) {
        if (value instanceof FractionValue) return (FractionValue) value;
        return new FractionValue(((IntegerValue) value).value, 1);
    }

    @Override
    public @NotNull String asString() {
        return numerator + "/" + denominator;
    }
}
