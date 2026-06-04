package com.nicogabriel.ariee.core.exception;

public final class TokenizationException extends PipelineException {

    private final char unexpectedChar;

    public TokenizationException(char unexpectedChar, int position) {
        super("Unexpected character '%c' at position %d.".formatted(unexpectedChar, position), position);
        this.unexpectedChar = unexpectedChar;
    }

    public char getUnexpectedChar() {
        return unexpectedChar;
    }
}
