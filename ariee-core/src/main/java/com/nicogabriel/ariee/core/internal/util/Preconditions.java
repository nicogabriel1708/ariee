package com.nicogabriel.ariee.core.internal.util;

import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

public final class Preconditions {

    private Preconditions() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    public static <T> T checkArgumentNotNull(@Nullable T reference) {
        if (reference == null) {
            throw new IllegalArgumentException();
        }

        return reference;
    }

    public static <T> T checkArgumentNotNull(@Nullable T reference, @Nullable String errorMessage) {
        if (reference == null) {
            throw new IllegalArgumentException(errorMessage);
        }

        return reference;
    }

    public static <T> T checkArgumentNotNull(@Nullable T reference, @Nullable Supplier<String> errorMessageSupplier) {
        if (reference == null) {
            throw new IllegalArgumentException(errorMessageSupplier == null ? null : errorMessageSupplier.get());
        }

        return reference;
    }

    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean expression, @Nullable String errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkArgument(boolean expression, @Nullable Supplier<String> errorMessageSupplier) {
        if (!expression) {
            throw new IllegalArgumentException(errorMessageSupplier == null ? null : errorMessageSupplier.get());
        }
    }

    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean expression, @Nullable String errorMessage) {
        if (!expression) {
            throw new IllegalStateException(errorMessage);
        }
    }

    public static void checkState(boolean expression, @Nullable Supplier<String> errorMessageSupplier) {
        if (!expression) {
            throw new IllegalStateException(errorMessageSupplier == null ? null : errorMessageSupplier.get());
        }
    }

    public static <T> T checkNotNull(@Nullable T reference) {
        return Objects.requireNonNull(reference);
    }

    public static <T> T checkNotNull(@Nullable T reference, @Nullable String errorMessage) {
        return Objects.requireNonNull(reference, errorMessage);
    }

    public static <T> T checkNotNull(@Nullable T reference, @Nullable Supplier<String> errorMessageSupplier) {
        return Objects.requireNonNull(reference, errorMessageSupplier);
    }

    public static <T> T checkNotNullOrElse(@Nullable T reference, T defaultValue) {
        return Objects.requireNonNullElse(reference, defaultValue);
    }

    public static <T> T checkNotNullOrElseGet(@Nullable T reference, Supplier<T> defaultValueSupplier) {
        return Objects.requireNonNullElseGet(reference, defaultValueSupplier);
    }

    public static <T> T checkNotNullOrElseThrow(
            @Nullable T reference,
            @Nullable Supplier<? extends RuntimeException> runtimeExceptionSupplier
    ) {
        if (reference == null) {
            throw runtimeExceptionSupplier == null ? new RuntimeException() : runtimeExceptionSupplier.get();
        }

        return reference;
    }
}
