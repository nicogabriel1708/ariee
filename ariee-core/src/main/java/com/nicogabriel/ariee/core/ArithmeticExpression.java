package com.nicogabriel.ariee.core;

import com.nicogabriel.ariee.core.ast.ASTNode;
import com.nicogabriel.ariee.core.token.Token;
import com.nicogabriel.ariee.core.visitor.ASTVisitor;
import com.nicogabriel.ariee.core.visitor.EvaluatorVisitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ArithmeticExpression {

    private static final double EPSILON = 1e-10;

    private final Map<Class<? extends ASTVisitor<?>>, Object> cache = new HashMap<>();
    private final String rawExpression;
    private final ASTNode rootNode;

    private ArithmeticExpression(String rawExpression, ASTNode rootNode) {
        this.rawExpression = rawExpression;
        this.rootNode = rootNode;
    }

    public static ArithmeticExpression fromString(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("The given expression cannot be null or empty.");
        }

        List<Token> tokens = null; //new Lexer().tokenize(expression);
        ASTNode root = null; // new Parser().parse(tokens);

        return new ArithmeticExpression(expression, root);
    }

    public double evaluate() {
        return (double) cache.computeIfAbsent(EvaluatorVisitor.class, _ -> rootNode.accept(new EvaluatorVisitor()));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ArithmeticExpression other = (ArithmeticExpression) obj;

        return Math.abs(evaluate() - other.evaluate()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(Math.round(evaluate() / EPSILON) * EPSILON);
    }
}
