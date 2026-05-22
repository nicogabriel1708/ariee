package com.nicogabriel.ariee.core;

import com.nicogabriel.ariee.core.internal.ast.ASTNode;
import com.nicogabriel.ariee.core.internal.visitor.ASTVisitor;
import com.nicogabriel.ariee.core.internal.visitor.EvaluatorVisitor;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public final class ArithmeticExpression {

    private static final double EPSILON = 1e-10;

    private final Map<Class<? extends ASTVisitor<?>>, Object> cache = new HashMap<>();
    private final ASTNode rootNode;

    ArithmeticExpression(ASTNode rootNode) {
        this.rootNode = rootNode;
    }

    public static ArithmeticExpression parse(CharSequence expression) {
        return ArithmeticInputParser.parseExpression(expression);
    }

    public static ArithmeticExpression parseFile(Path filePath) {
        return ArithmeticInputParser.parseExpressionFile(filePath);
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
