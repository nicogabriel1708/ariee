package com.nicogabriel.ariee.core.internal.lexer;

import com.nicogabriel.ariee.core.internal.token.Token;
import com.nicogabriel.ariee.core.internal.token.TokenType;

import java.util.Iterator;

public interface Lexer extends Iterator<Token> {

    Token peek();

    @Override
    default boolean hasNext() {
        return !peek().is(TokenType.EOF);
    }

    @Override
    Token next();
}
