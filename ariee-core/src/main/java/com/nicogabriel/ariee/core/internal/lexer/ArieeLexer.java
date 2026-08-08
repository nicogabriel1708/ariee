package com.nicogabriel.ariee.core.internal.lexer;

import com.nicogabriel.ariee.core.internal.token.Token;
import com.nicogabriel.ariee.core.internal.util.Strings;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgument;

public final class ArieeLexer implements Lexer {

    private final String rawExpression;

    public ArieeLexer(String rawExpression) {
        checkArgument(!Strings.isNullOrBlank(rawExpression), "The given expression must not be null or blank.");

        this.rawExpression = rawExpression;
    }

    @Override
    public Token peek() {
        return null;
    }

    @Override
    public Token next() {
        return null;
    }
}

/*
package com.nicogabriel.ariee.core.lexer;

import com.nicogabriel.ariee.core.token.Bracket;
import com.nicogabriel.ariee.core.token.NumberToken;
import com.nicogabriel.ariee.core.token.Operator;
import com.nicogabriel.ariee.core.token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Lexer {

    public List<Token> tokenize(String rawExpression) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder currentValue = new StringBuilder();
        NumberToken numberToken = new NumberToken(Double.parseDouble(currentValue.toString()));
        for (char c : rawExpression.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                currentValue.append(c);
                continue;
            }

            if (!currentValue.isEmpty()) {
                tokens.add(numberToken);
                currentValue.setLength(0);
            }

            if (c == ' ') continue;

            String symbol = String.valueOf(c);

            Optional<Bracket> bracket = Bracket.fromString(symbol);
            Optional<Operator> operator = Operator.fromString(symbol);

            if (bracket.isPresent()) {
                tokens.add(bracket.get());
            } else if (operator.isPresent()) {
                tokens.add(operator.get());
            } else {
                throw new IllegalArgumentException("Unknown character: " + c);
            }
        }

        if (!currentValue.isEmpty()) {
            tokens.add(numberToken);
        }

        return tokens;
    }
}
 */
