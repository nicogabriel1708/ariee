package com.nicogabriel.ariee.core.internal.token;

import java.util.Optional;

public final class TokenFactory {

    private TokenFactory() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Optional<Token> create(char literal, int position) {
        return create(String.valueOf(literal), position);
    }

    public static Optional<Token> create(String literal, int position) {
        Optional<Token> operatorToken =
                Operator.fromString(literal).map(operator -> new OperatorToken(operator, position));
        Optional<Token> bracketToken = Bracket.fromString(literal).map(bracket -> new BracketToken(bracket, position));

        return operatorToken.isPresent() ? operatorToken : bracketToken;
    }
}
