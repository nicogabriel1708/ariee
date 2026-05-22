package com.nicogabriel.ariee.core;

import java.nio.file.Path;

public record ArithmeticEquation(ArithmeticExpression left, ArithmeticExpression right) {

    public ArithmeticEquation {
        if (left == null || right == null) {
            throw new IllegalArgumentException("The given expressions must not be null.");
        }
    }

    public static ArithmeticEquation parse(CharSequence equation) {
        return ArithmeticInputParser.parseEquation(equation);
    }

    public static ArithmeticEquation parseFile(Path filePath) {
        return ArithmeticInputParser.parseEquationFile(filePath);
    }

    public boolean isValid() {
        return left.equals(right);
    }
}
