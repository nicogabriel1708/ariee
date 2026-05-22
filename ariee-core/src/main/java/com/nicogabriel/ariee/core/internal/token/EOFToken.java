package com.nicogabriel.ariee.core.internal.token;

public record EOFToken(int position) implements Token {

    @Override
    public TokenType type() {
        return TokenType.EOF;
    }
}
