package com.nicogabriel.ariee.core.exception;

import com.nicogabriel.ariee.core.internal.util.Resources;
import com.nicogabriel.ariee.core.internal.util.Strings;
import org.jspecify.annotations.Nullable;

public final class InternalException extends ArieeException {

    private static final String PREFIX = "[ARIEE-INTERNAL-BUG]";
    private static final String SUFFIX = "Please report this issue to the ARIEE maintainers:";
    private static final String TEMPLATE = PREFIX + " %s\n  => " + SUFFIX + " %s.";
    private static final String FALLBACK_MESSAGE = "An unexpected internal error occurred.";
    private static final String ISSUE_URL = Resources.getProperty("issue-url");

    public InternalException() {
        super(formatMessage(null));
    }

    public InternalException(@Nullable String message) {
        super(formatMessage(message));
    }

    public InternalException(@Nullable String message, @Nullable Throwable cause) {
        super(formatMessage(message), cause);
    }

    public InternalException(@Nullable Throwable cause) {
        super(formatMessage(null), cause);
    }

    private static String formatMessage(@Nullable String message) {
        return TEMPLATE.formatted(
                Strings.isNullOrBlank(message) ? FALLBACK_MESSAGE : Strings.formatSentence(message),
                ISSUE_URL
        );
    }
}
