package com.nicogabriel.ariee.core.internal.token;

import java.util.Optional;

public final class TokenFactory {

    private TokenFactory() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Optional<Token> create(char c, int position) {
        return create(String.valueOf(c), position);
    }

    public static Optional<Token> create(String s, int position) {
        Optional<Token> operator = Operator.fromString(s).map(o -> new OperatorToken(o, position));
        Optional<Token> bracket = Bracket.fromString(s).map(b -> new BracketToken(b, position));

        return operator.isPresent() ? operator : bracket;
    }
}
