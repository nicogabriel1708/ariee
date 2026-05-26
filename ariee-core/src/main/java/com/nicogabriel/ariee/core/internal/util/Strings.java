package com.nicogabriel.ariee.core.internal.util;

public final class Strings {

    private Strings() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isNullOrBlank(String string) {
        return string == null || string.isBlank();
    }

    public static String capitalize(String string) {
        if (isNullOrBlank(string)) {
            return string;
        }

        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    public static String ensureEndsWithPeriod(String string) {
        if (isNullOrBlank(string)) {
            return string;
        }

        return string.endsWith(".") ? string : string + ".";
    }

    public static String formatSentence(String string) {
        if (isNullOrBlank(string)) {
            return string;
        }

        return ensureEndsWithPeriod(capitalize(string.trim()));
    }
}
