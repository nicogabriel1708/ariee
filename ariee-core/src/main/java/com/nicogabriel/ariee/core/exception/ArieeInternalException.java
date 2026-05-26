package com.nicogabriel.ariee.core.exception;

import com.nicogabriel.ariee.core.internal.util.Strings;

public final class ArieeInternalException extends RuntimeException {

    private static final String PREFIX = "[ARIEE-INTERNAL-BUG]";
    private static final String SUFFIX = "Please report this issue to the ARIEE maintainers.";
    private static final String TEMPLATE = PREFIX + " %s " + SUFFIX;
    private static final String FALLBACK_MESSAGE = "An unexpected internal error occurred.";

    public ArieeInternalException(String message) {
        super(formatMessage(message));
    }

    public ArieeInternalException(String message, Throwable cause) {
        super(formatMessage(message), cause);
    }

    private static String formatMessage(String message) {
        return TEMPLATE.formatted(Strings.isNullOrBlank(message) ? FALLBACK_MESSAGE : Strings.formatSentence(message));
    }
}
