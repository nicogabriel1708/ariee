package com.nicogabriel.ariee.core.exception;

public final class ParseException extends ArieeException {

    private final String expected;
    private final String encountered;

    public ParseException(String expected, String encountered, int position) {
        super("Expected %s but encountered '%s' at position %d".formatted(expected, encountered, position), position);
        this.expected = expected;
        this.encountered = encountered;
    }

    public String getExpected() {
        return expected;
    }

    public String getEncountered() {
        return encountered;
    }
}
