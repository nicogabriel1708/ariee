package com.nicogabriel.ariee.core.internal.token;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgumentNotNull;

public enum Bracket {

    OPEN("("), CLOSE(")");

    private static final Map<String, Bracket> SYMBOL_CACHE =
            Stream.of(values()).collect(Collectors.toUnmodifiableMap(bracket -> bracket.symbol, Function.identity()));

    private final String symbol;

    Bracket(String symbol) {
        this.symbol = symbol;
    }

    public static Optional<Bracket> fromString(String symbol) {
        checkArgumentNotNull(symbol, "The given symbol must not be null.");

        return Optional.ofNullable(SYMBOL_CACHE.get(symbol));
    }

    @Override
    public String toString() {
        return symbol;
    }
}
