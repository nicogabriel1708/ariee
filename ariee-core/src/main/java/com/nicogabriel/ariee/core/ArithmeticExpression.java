package com.nicogabriel.ariee.core;

import com.nicogabriel.ariee.core.exception.InternalException;
import com.nicogabriel.ariee.core.internal.ast.AstNode;
import com.nicogabriel.ariee.core.internal.util.ExceptionTranslatingExecutor;
import com.nicogabriel.ariee.core.internal.visitor.AstVisitor;
import com.nicogabriel.ariee.core.internal.visitor.EvaluatorVisitor;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgumentNotNull;
import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkNotNullOrElseThrow;

public final class ArithmeticExpression {

    private static final double EPSILON = 1e-10;

    private final Map<Class<? extends AstVisitor<?>>, Object> cache = new ConcurrentHashMap<>();
    private final AstNode rootNode;

    ArithmeticExpression(AstNode rootNode) {
        this.rootNode = checkArgumentNotNull(rootNode, "The given root node must not be null.");
    }

    public static ArithmeticExpression parse(CharSequence expression) {
        return ExceptionTranslatingExecutor.execute(() -> ArithmeticInputParser.parseExpression(expression));
    }

    public static ArithmeticExpression parseFile(Path filePath) {
        return ExceptionTranslatingExecutor.execute(() -> ArithmeticInputParser.parseExpressionFile(filePath));
    }

    public double evaluate() {
        return ExceptionTranslatingExecutor.execute(() -> {
            Object result = cache.computeIfAbsent(EvaluatorVisitor.class, _ -> rootNode.accept(new EvaluatorVisitor()));
            checkNotNullOrElseThrow(
                    result,
                    () -> new InternalException("Evaluation of the expression unexpectedly " + "resulted in null.")
            );
            return (double) result;
        });
    }

    private <T> T resolve(AstVisitor<T> visitor) {
        return ExceptionTranslatingExecutor.execute(() -> {
            Object result = cache.computeIfAbsent(
                    (Class<? extends AstVisitor<?>>) visitor.getClass(),
                    _ -> rootNode.accept(visitor)
            );
            checkNotNullOrElseThrow(
                    result,
                    () -> new InternalException(visitor.getClass().getSimpleName() + " unexpectedly resulted in null.")
            );
            return (T) result;
        });
    }

    /*private <R, V extends AstVisitor<R>> R applyVisitor(Class<V> visitorClass, Supplier<V> visitorSupplier) {
        return ExceptionTranslatingExecutor.execute(() -> {
            // Compute and cache the result if it hasn't been evaluated yet
            Object result = cache.computeIfAbsent(
                    visitorClass, _ -> {
                        V visitor = visitorSupplier.get();
                        return rootNode.accept(visitor);
                    }
            );

            // Ensure we don't pass a null into auto-unboxing, failing fast if we do
            return (R) checkNotNull(
                    result,
                    new InternalException(visitorClass.getSimpleName() + " unexpectedly resulted in null.")
            );
        });
    }*/

    // 1. remove functions
    // 2. implement executeVisitor func using typedkey for the hashmap, so that we can avoid the cast and the unchecked
    // warning

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
