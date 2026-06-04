package com.nicogabriel.ariee.core.internal.util.function;

@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Throwable> {

    R apply(T t) throws E;
}
