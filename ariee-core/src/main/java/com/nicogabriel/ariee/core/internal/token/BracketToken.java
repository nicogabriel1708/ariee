package com.nicogabriel.ariee.core.internal.token;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgumentNotNull;

public record BracketToken(Bracket bracket, int position) implements Token {

    public BracketToken {
        checkArgumentNotNull(bracket, "The given bracket must not be null.");
    }

    @Override
    public TokenType type() {
        return TokenType.BRACKET;
    }

    @Override
    public String toString() {
        return bracket.toString();
    }
}
