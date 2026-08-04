package com.nicogabriel.ariee.core;

import com.nicogabriel.ariee.core.internal.util.ExceptionTranslatingExecutor;

import java.nio.file.Path;
import java.util.Objects;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgumentNotNull;

public record ArithmeticEquation(ArithmeticExpression left, ArithmeticExpression right) {

    public ArithmeticEquation {
        checkArgumentNotNull(left, "The given left expression must not be null.");
        checkArgumentNotNull(right, "The given right expression must not be null.");
    }

    public static ArithmeticEquation parse(CharSequence equation) {
        return ExceptionTranslatingExecutor.execute(ArithmeticInputParser::parseEquation, equation);
    }

    public static ArithmeticEquation parseFile(Path filePath) {
        return ExceptionTranslatingExecutor.execute(ArithmeticInputParser::parseEquationFile, filePath);
    }

    public boolean isValid() {
        return Objects.equals(left, right);
    }
}
