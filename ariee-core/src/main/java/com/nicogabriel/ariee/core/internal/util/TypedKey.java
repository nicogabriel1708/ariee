package com.nicogabriel.ariee.core.internal.util;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgumentNotNull;

public final class TypedKey<T> {

    private final String name;
    private final Class<T> type;

    public TypedKey(String name, Class<T> type) {
        this.name = checkArgumentNotNull(name, "The given name must not be null.");
        this.type = checkArgumentNotNull(type, "The given type must not be null.");
    }

    public T cast(Object value) {
        return type.cast(value);
    }

    @Override
    public String toString() {
        return "%s (%s) @%x".formatted(name, type.getSimpleName(), System.identityHashCode(this));
    }
}
