package com.nicogabriel.ariee.core.internal.token;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgumentNotNull;

public record OperatorToken(Operator operator, int position) implements Token {

    public OperatorToken {
        checkArgumentNotNull(operator, "The given operator must not be null.");
    }

    @Override
    public TokenType type() {
        return TokenType.OPERATOR;
    }

    @Override
    public String toString() {
        return operator.toString();
    }
}
