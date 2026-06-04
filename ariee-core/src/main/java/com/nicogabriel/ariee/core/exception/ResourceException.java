package com.nicogabriel.ariee.core.exception;

import com.nicogabriel.ariee.core.internal.util.Strings;
import org.jspecify.annotations.Nullable;

public final class ResourceException extends ArieeException {

    public ResourceException(String message) {
        super(Strings.formatSentence(message));
    }

    public ResourceException(String message, @Nullable Throwable cause) {
        super(Strings.formatSentence(message), cause);
    }
}
