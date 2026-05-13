package com.nicogabriel.ariee.core.token;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Bracket {

    OPEN("("), CLOSE(")");

    private static final Map<String, Bracket> SYMBOL_CACHE = new HashMap<>();

    static {
        for (Bracket bracket : values()) {
            SYMBOL_CACHE.put(bracket.symbol, bracket);
        }
    }

    private final String symbol;

    Bracket(String symbol) {
        this.symbol = symbol;
    }

    public static Optional<Bracket> fromString(String symbol) {
        return Optional.ofNullable(SYMBOL_CACHE.get(symbol));
    }

    @Override
    public String toString() {
        return symbol;
    }
}
