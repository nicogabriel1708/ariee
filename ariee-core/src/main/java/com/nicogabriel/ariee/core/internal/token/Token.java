package com.nicogabriel.ariee.core.internal.token;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgumentNotNull;

public interface Token {

    TokenType type();

    int position();

    default boolean is(TokenType expectedType) {
        checkArgumentNotNull(expectedType, "The given expected token type must not be null.");

        return type() == expectedType;
    }

    default boolean isAny(TokenType... expectedTypes) {
        checkArgumentNotNull(expectedTypes, "The given array of expected token types must not be null.");

        for (TokenType expectedType : expectedTypes) {
            checkArgumentNotNull(expectedType, "The given expected token type must not be null.");

            if (is(expectedType)) {
                return true;
            }
        }

        return false;
    }
}
