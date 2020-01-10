package dev.toliner.calcgw.core.operations;

import dev.toliner.calcgw.core.Expression;
import dev.toliner.calcgw.core.Operation;
import dev.toliner.calcgw.core.Operator;
import dev.toliner.calcgw.core.Value;
import dev.toliner.calcgw.core.values.FractionValue;
import dev.toliner.calcgw.core.values.IntegerValue;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public abstract class TimesOperation implements Operation {

    @NotNull
    @Contract("_, _ -> new")
    public static TimesOperation of(@NotNull Expression first, @NotNull Expression second) {
        return new Lazy(first, second);
    }

    @Override
    public @NotNull Operator getOperator() {
        return Operator.PLUS;
    }

    private static final class Integer extends TimesOperation {
        private IntegerValue first, second;

        public Integer(@NotNull IntegerValue first, @NotNull IntegerValue second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public @NotNull Value eval() {
            return new IntegerValue(first.value * second.value);
        }

        @Override
        public @NotNull String asString() {
            return first.asString() + "×" + second.asString();
        }
    }

    private static final class Fraction extends TimesOperation {
        private FractionValue first, second;

        public Fraction(@NotNull FractionValue first, @NotNull FractionValue second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public @NotNull Value eval() {
            return new FractionValue(
                    first.numerator * second.numerator ,
                    first.denominator * second.denominator
            ).optimized();
        }

        @Override
        public @NotNull String asString() {
            return first.asString() + "×" + second.asString();
        }
    }

    private static final class Lazy extends TimesOperation {
        private final Expression first, second;

        public Lazy(@NotNull Expression first, @NotNull Expression second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public @NotNull Value eval() {
            TimesOperation actual;
            if (first instanceof Value && second instanceof Value) {
                if (first instanceof IntegerValue && second instanceof IntegerValue) {
                    actual = new Integer((IntegerValue) first, (IntegerValue) second);
                } else {
                    actual = new Fraction(FractionValue.of((Value) first), FractionValue.of((Value) second));
                }
            } else {
                var firstResult = (Value) first.eval();
                var secondResult = (Value) second.eval();
                if (firstResult instanceof IntegerValue && secondResult instanceof IntegerValue) {
                    actual = new Integer((IntegerValue) firstResult, (IntegerValue) secondResult);
                } else {
                    actual = new Fraction(FractionValue.of(firstResult), FractionValue.of(secondResult));
                }
            }
            return actual.eval();
        }

        @Override
        public @NotNull String asString() {
            return first.asString() + "×" + second.asString();
        }
    }
}
