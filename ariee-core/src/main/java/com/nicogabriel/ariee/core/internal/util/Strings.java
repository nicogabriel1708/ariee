package com.nicogabriel.ariee.core.internal.util;

import org.jspecify.annotations.Nullable;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgumentNotNull;

public final class Strings {

    private Strings() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    public static boolean isNullOrEmpty(@Nullable String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isNullOrBlank(@Nullable String string) {
        return string == null || string.isBlank();
    }

    private static int getFirstNonWhitespaceIndex(String string) {
        checkArgumentNotNull(string, "The given string must not be null.");

        for (int i = 0; i < string.length(); i++) {
            if (!Character.isWhitespace(string.charAt(i))) {
                return i;
            }
        }

        return -1;
    }

    private static int getLastNonWhitespaceIndex(String string) {
        checkArgumentNotNull(string, "The given string must not be null.");

        for (int i = string.length() - 1; i >= 0; i--) {
            if (!Character.isWhitespace(string.charAt(i))) {
                return i;
            }
        }

        return -1;
    }

    public static String capitalize(String string) {
        checkArgumentNotNull(string, "The given string must not be null.");

        int firstNonWhitespaceIndex = getFirstNonWhitespaceIndex(string);

        return firstNonWhitespaceIndex == -1
                ? string
                : string.substring(0, firstNonWhitespaceIndex) +
                  Character.toUpperCase(string.charAt(firstNonWhitespaceIndex)) +
                  string.substring(firstNonWhitespaceIndex + 1);
    }

    public static String ensureEndsWithPeriod(String string) {
        checkArgumentNotNull(string, "The given string must not be null.");

        int lastNonWhitespaceIndex = getLastNonWhitespaceIndex(string);

        return lastNonWhitespaceIndex == -1 || string.charAt(lastNonWhitespaceIndex) == '.'
                ? string
                : string.substring(0, lastNonWhitespaceIndex + 1) + '.' + string.substring(lastNonWhitespaceIndex + 1);
    }

    public static String formatSentence(String string) {
        checkArgumentNotNull(string, "The given string must not be null.");

        return ensureEndsWithPeriod(capitalize(string.trim()));
    }
}
