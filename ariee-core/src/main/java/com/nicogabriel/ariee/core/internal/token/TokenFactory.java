package com.nicogabriel.ariee.core.internal.token;

import java.util.Optional;

public final class TokenFactory {

    public static Optional<Token> fromChar(char c, int position) {
        return fromString(String.valueOf(c), position);
    }

    public static Optional<Token> fromString(String s, int position) {
        Optional<Token> operator = Operator.fromString(s).map(o -> new OperatorToken(o, position));
        Optional<Token> bracket = Bracket.fromString(s).map(b -> new BracketToken(b, position));
        return operator.isPresent() ? operator : bracket;
    }
}
