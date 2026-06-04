package com.nicogabriel.ariee.core.internal.util.function;

@FunctionalInterface
public interface ThrowingSupplier<T, E extends Throwable> {

    T get() throws E;
}
