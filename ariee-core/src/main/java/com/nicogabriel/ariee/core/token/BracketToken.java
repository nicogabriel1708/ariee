package com.nicogabriel.ariee.core.token;

public record BracketToken(Bracket bracket, int position) implements Token {

    @Override
    public TokenType type() {
        return TokenType.BRACKET;
    }
}
