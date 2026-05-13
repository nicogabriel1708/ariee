package com.nicogabriel.ariee.core.exception;

public abstract class ArieeException extends RuntimeException {

    private final int position;

    protected ArieeException(String message, int position) {
        super(message);
        this.position = position;
    }

    protected ArieeException(String message, Throwable cause, int position) {
        super(message, cause);
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
