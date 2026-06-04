package com.nicogabriel.ariee.core.exception;

import org.jspecify.annotations.Nullable;

public abstract class ArieeException extends RuntimeException {

    protected ArieeException(String message) {
        super(message);
    }

    protected ArieeException(String message, @Nullable Throwable cause) {
        super(message, cause);
    }
}
