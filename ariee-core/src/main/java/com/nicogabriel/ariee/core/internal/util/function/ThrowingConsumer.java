package com.nicogabriel.ariee.core.internal.util.function;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Throwable> {

    void accept(T t) throws E;
}
