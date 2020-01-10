package dev.toliner.calcgw.core.values;

import dev.toliner.calcgw.core.Value;
import org.jetbrains.annotations.NotNull;

/**
 * 分数
 */
public class FractionValue implements Value {

    /**
     * 分母
     */
    public final int denominator;
    /**
     * 分子
     */
    public final int numerator;

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

    private static int gcd(int a, int b) {
        if (a < b) return gcd(b, a);
        if (b == 0) return b;
        return gcd(b, b % a);
    }

    public Value optimized() {
        int gcd = gcd(denominator, numerator);
        int up = denominator / gcd, down = numerator / gcd;
        if (down == 1) {
            return new IntegerValue(up);
        } else {
            return new FractionValue(up, down);
        }
    }

    @Override
    public @NotNull Value eval() {
        return optimized();
    }
}
