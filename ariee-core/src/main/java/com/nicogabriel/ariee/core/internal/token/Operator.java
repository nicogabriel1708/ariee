package com.nicogabriel.ariee.core.internal.token;

import java.util.Map;
import java.util.Optional;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Operator {

    ADD("+", (a, b) -> a + b, 1, true),
    SUBTRACT("-", (a, b) -> a - b, 1, true),
    MULTIPLY("*", (a, b) -> a * b, 2, true),
    DIVIDE("/", (a, b) -> a / b, 2, true),
    MODULO("%", (a, b) -> a % b, 2, true),
    EXPONENT("^", Math::pow, 3, false);

    private static final Map<String, Operator> SYMBOL_CACHE =
            Stream.of(values()).collect(Collectors.toUnmodifiableMap(operator -> operator.symbol, Function.identity()));

    private final String symbol;
    private final DoubleBinaryOperator operation;
    private final int precedence;
    private final boolean isLeftAssociative;

    Operator(String symbol, DoubleBinaryOperator operation, int precedence, boolean isLeftAssociative) {
        this.symbol = symbol;
        this.operation = operation;
        this.precedence = precedence;
        this.isLeftAssociative = isLeftAssociative;
    }

    public static Optional<Operator> fromString(String symbol) {
        return Optional.ofNullable(SYMBOL_CACHE.get(symbol));
    }

    public boolean evaluatesBefore(Operator other) {
        return precedence != other.precedence ? precedence > other.precedence : isLeftAssociative;
    }

    public double calculate(double left, double right) {
        return operation.applyAsDouble(left, right);
    }

    @Override
    public String toString() {
        return symbol;
    }
}
