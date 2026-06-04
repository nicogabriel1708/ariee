package com.nicogabriel.ariee.core.exception;

public abstract class PipelineException extends ArieeException {

    private final int position;

    protected PipelineException(String message, int position) {
        super(message);
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
