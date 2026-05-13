package com.nicogabriel.ariee.core.token;

public record OperatorToken(Operator operator, int position) implements Token {

    @Override
    public TokenType type() {
        return TokenType.OPERATOR;
    }
}
