package com.nicogabriel.ariee.core.internal.util;

import org.jspecify.annotations.Nullable;

import java.util.Objects;

public final class Preconditions {

    private Preconditions() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    public static <T> T checkArgumentNotNull(@Nullable T reference) {
        return checkNotNull(reference, new IllegalArgumentException());
    }

    public static <T> T checkArgumentNotNull(@Nullable T reference, String errorMessage) {
        return checkNotNull(reference, new IllegalArgumentException(errorMessage));
    }

    public static void checkArgument(boolean expression) {
        check(expression, new IllegalArgumentException());
    }

    public static void checkArgument(boolean expression, String errorMessage) {
        check(expression, new IllegalArgumentException(errorMessage));
    }

    public static void checkState(boolean expression) {
        check(expression, new IllegalStateException());
    }

    public static void checkState(boolean expression, String errorMessage) {
        check(expression, new IllegalStateException(errorMessage));
    }

    public static <T> T checkNotNull(@Nullable T reference) {
        return Objects.requireNonNull(reference);
    }

    public static <T> T checkNotNull(@Nullable T reference, String errorMessage) {
        return Objects.requireNonNull(reference, errorMessage);
    }

    public static <T> T checkNotNull(@Nullable T reference, RuntimeException runtimeException) {
        check(reference != null, runtimeException);

        return reference;
    }

    public static <T> T checkNotNullElse(@Nullable T reference, T defaultValue) {
        return Objects.requireNonNullElse(reference, defaultValue);
    }

    private static void check(boolean expression, RuntimeException runtimeException) {
        if (!expression) {
            throw checkNotNullElse(runtimeException, new RuntimeException());
        }
    }
}
