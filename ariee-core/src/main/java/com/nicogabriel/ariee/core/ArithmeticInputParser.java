package com.nicogabriel.ariee.core;

import com.nicogabriel.ariee.core.exception.ResourceException;
import com.nicogabriel.ariee.core.internal.ast.AstNode;
import com.nicogabriel.ariee.core.internal.lexer.ArieeLexer;
import com.nicogabriel.ariee.core.internal.lexer.Lexer;
import com.nicogabriel.ariee.core.internal.parser.ArieeParser;
import com.nicogabriel.ariee.core.internal.parser.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgument;
import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgumentNotNull;

final class ArithmeticInputParser {

    private ArithmeticInputParser() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    static ArithmeticExpression parseExpression(CharSequence expression) {
        Lexer lexer = new ArieeLexer(requireNonBlankString(expression));
        Parser parser = new ArieeParser(lexer);
        AstNode root = parser.parse();

        return new ArithmeticExpression(root);
    }

    static ArithmeticExpression parseExpressionFile(Path path) {
        return parseExpression(readFile(path));
    }

    static ArithmeticEquation parseEquation(CharSequence equation) {
        String[] parts = requireNonBlankString(equation).split("=");

        checkArgument(parts.length == 2, "The given equation must contain exactly one '='.");

        return new ArithmeticEquation(parseExpression(parts[0]), parseExpression(parts[1]));
    }

    static ArithmeticEquation parseEquationFile(Path path) {
        return parseEquation(readFile(path));
    }

    private static String requireNonBlankString(CharSequence charSequence) {
        checkArgumentNotNull(charSequence, "The given char sequence must not be null.");

        String string = charSequence.toString();

        checkArgument(!string.isBlank(), "The given char sequence must not be blank.");

        return string;
    }

    private static String readFile(Path path) {
        checkArgumentNotNull(path, "The given file path must not be null.");

        try {
            return Files.readString(path);
        } catch (IOException ioException) {
            throw new ResourceException("Failed to read file: '%s'.".formatted(path), ioException);
        }
    }
}
