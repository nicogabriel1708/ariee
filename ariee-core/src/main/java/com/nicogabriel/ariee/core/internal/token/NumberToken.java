package com.nicogabriel.ariee.core.internal.token;

public record NumberToken(double value, int position) implements Token {

    @Override
    public TokenType type() {
        return TokenType.NUMBER;
    }
}
