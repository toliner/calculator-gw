package dev.toliner.calcgw.core.operations;

import dev.toliner.calcgw.core.Expression;
import dev.toliner.calcgw.core.Operation;
import dev.toliner.calcgw.core.Value;
import dev.toliner.calcgw.core.values.FractionValue;
import dev.toliner.calcgw.core.values.IntegerValue;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public abstract class PlusOperation implements Operation {

    @NotNull
    @Contract("_, _ -> new")
    public static PlusOperation of(Expression first, Expression second) {
        return new Lazy(first, second);
    }

    private static final class Integer extends PlusOperation {
        private IntegerValue first, second;

        public Integer(IntegerValue first, IntegerValue second) {
            this.first = first;
            this.second = second;
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

    private static final class Fraction extends PlusOperation {
        private FractionValue first, second;

        public Fraction(FractionValue first, FractionValue second) {
            this.first = first;
            this.second = second;
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

    private static final class Lazy extends PlusOperation {
        private final Expression first, second;

        public Lazy(Expression first, Expression second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public @NotNull Value eval() {
            PlusOperation actual;
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
                    actual = new Integer((IntegerValue) firstResult, (IntegerValue) firstResult);
                } else {
                    actual = new Fraction(FractionValue.of(firstResult), FractionValue.of(secondResult));
                }
            }
            return actual.eval();
        }

        @Override
        public @NotNull String asString() {
            return first.asString() + "+" + second.asString();
        }
    }
}
