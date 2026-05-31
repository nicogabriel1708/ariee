package com.nicogabriel.ariee.core;

import com.nicogabriel.ariee.core.exception.ArieeInternalException;
import com.nicogabriel.ariee.core.internal.ast.ASTNode;
import com.nicogabriel.ariee.core.internal.visitor.ASTVisitor;
import com.nicogabriel.ariee.core.internal.visitor.EvaluatorVisitor;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgumentNotNull;
import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkNotNull;

public final class ArithmeticExpression {

    private static final double EPSILON = 1e-10;

    private final Map<Class<? extends ASTVisitor<?>>, Object> cache = new ConcurrentHashMap<>();
    private final ASTNode rootNode;

    ArithmeticExpression(ASTNode rootNode) {
        this.rootNode = checkArgumentNotNull(rootNode, "The given root node must not be null.");
    }

    public static ArithmeticExpression parse(CharSequence expression) {
        return ArithmeticInputParser.parseExpression(expression);
    }

    public static ArithmeticExpression parseFile(Path filePath) {
        return ArithmeticInputParser.parseExpressionFile(filePath);
    }

    public double evaluate() {
        return (double) checkNotNull(
                cache.computeIfAbsent(EvaluatorVisitor.class, _ -> rootNode.accept(new EvaluatorVisitor())),
                new ArieeInternalException("Evaluation of the expression unexpectedly resulted in null.")
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        ArithmeticExpression other = (ArithmeticExpression) object;

        return Math.abs(evaluate() - other.evaluate()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(Math.round(evaluate() / EPSILON) * EPSILON);
    }
}
