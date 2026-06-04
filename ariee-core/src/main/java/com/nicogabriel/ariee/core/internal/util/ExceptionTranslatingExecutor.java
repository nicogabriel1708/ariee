package com.nicogabriel.ariee.core.internal.util;

import com.nicogabriel.ariee.core.exception.ArieeException;
import com.nicogabriel.ariee.core.exception.InternalException;
import com.nicogabriel.ariee.core.internal.util.function.ThrowingConsumer;
import com.nicogabriel.ariee.core.internal.util.function.ThrowingFunction;
import com.nicogabriel.ariee.core.internal.util.function.ThrowingRunnable;
import com.nicogabriel.ariee.core.internal.util.function.ThrowingSupplier;
import org.jspecify.annotations.Nullable;

public final class ExceptionTranslatingExecutor {

    private ExceptionTranslatingExecutor() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    private static void translateAndThrow(Throwable throwable, @Nullable String errorMessage) {
        try {
            throw throwable;
        } catch (ArieeException | Error e) {
            throw e;
        } catch (Throwable t) {
            throw new InternalException(errorMessage, t);
        }
    }

    public static <T, R, E extends Throwable> R execute(
            ThrowingFunction<T, R, E> function,
            T argument,
            @Nullable String errorMessage
    ) {
        try {
            return function.apply(argument);
        } catch (Throwable throwable) {
            translateAndThrow(throwable, errorMessage);
            throw new AssertionError("Unreachable: translateAndThrow() should always throw.");
        }
    }

    public static <T, R, E extends Throwable> R execute(ThrowingFunction<T, R, E> function, T argument) {
        return execute(function, argument, null);
    }

    public static <T, E extends Throwable> void execute(
            ThrowingConsumer<T, E> consumer,
            T argument,
            @Nullable String errorMessage
    ) {
        try {
            consumer.accept(argument);
        } catch (Throwable throwable) {
            translateAndThrow(throwable, errorMessage);
            throw new AssertionError("Unreachable: translateAndThrow() should always throw.");
        }
    }

    public static <T, E extends Throwable> void execute(ThrowingConsumer<T, E> consumer, T argument) {
        execute(consumer, argument, null);
    }

    public static <T, E extends Throwable> T execute(ThrowingSupplier<T, E> supplier, @Nullable String errorMessage) {
        try {
            return supplier.get();
        } catch (Throwable throwable) {
            translateAndThrow(throwable, errorMessage);
            throw new AssertionError("Unreachable: translateAndThrow() should always throw.");
        }
    }

    public static <T, E extends Throwable> T execute(ThrowingSupplier<T, E> supplier) {
        return execute(supplier, null);
    }

    public static <E extends Throwable> void execute(ThrowingRunnable<E> runnable, @Nullable String errorMessage) {
        try {
            runnable.run();
        } catch (Throwable throwable) {
            translateAndThrow(throwable, errorMessage);
            throw new AssertionError("Unreachable: translateAndThrow() should always throw.");
        }
    }

    public static <E extends Throwable> void execute(ThrowingRunnable<E> runnable) {
        execute(runnable, null);
    }
}
