package com.nicogabriel.ariee.core;

public record ArithmeticEquation(ArithmeticExpression left, ArithmeticExpression right) {

    public ArithmeticEquation {
        if (left == null || right == null) {
            throw new IllegalArgumentException("The given expressions must not be null.");
        }
    }

    public static ArithmeticEquation fromString(String equation) {
        if (equation == null || equation.trim().isEmpty()) {
            throw new IllegalArgumentException("The given equation must not be null or empty.");
        }

        String[] parts = equation.split("=");

        if (parts.length != 2) {
            throw new IllegalArgumentException("The given equation must contain exactly one '='.");
        }

        return new ArithmeticEquation(
                ArithmeticExpression.fromString(parts[0]),
                ArithmeticExpression.fromString(parts[1])
        );
    }

    public boolean isValid() {
        return left.equals(right);
    }
}
