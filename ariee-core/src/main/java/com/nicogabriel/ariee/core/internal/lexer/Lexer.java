package com.nicogabriel.ariee.core.internal.lexer;

import com.nicogabriel.ariee.core.internal.token.Token;
import com.nicogabriel.ariee.core.internal.token.TokenType;
import com.nicogabriel.ariee.core.internal.util.iterator.PeekingIterator;

public interface Lexer extends PeekingIterator<Token> {

    @Override
    default boolean hasNext() {
        return !peek().is(TokenType.EOF);
    }
}
