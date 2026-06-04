package com.nicogabriel.ariee.core.internal.util.function;

@FunctionalInterface
public interface ThrowingRunnable<E extends Throwable> {

    void run() throws E;
}
