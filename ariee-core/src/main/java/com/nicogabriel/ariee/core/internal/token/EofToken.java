package com.nicogabriel.ariee.core.internal.token;

public record EofToken(int position) implements Token {

    @Override
    public TokenType type() {
        return TokenType.EOF;
    }

    @Override
    public String toString() {
        return "EOF";
    }
}
