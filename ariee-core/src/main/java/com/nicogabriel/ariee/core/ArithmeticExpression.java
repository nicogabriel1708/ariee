package com.nicogabriel.ariee.core;

import com.nicogabriel.ariee.core.exception.InternalException;
import com.nicogabriel.ariee.core.internal.ast.AstNode;
import com.nicogabriel.ariee.core.internal.util.ExceptionTranslatingExecutor;
import com.nicogabriel.ariee.core.internal.util.TypedKey;
import com.nicogabriel.ariee.core.internal.visitor.AstVisitor;
import com.nicogabriel.ariee.core.internal.visitor.EvaluatorVisitor;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgumentNotNull;
import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkNotNullOrElseThrow;

public final class ArithmeticExpression {

    private static final TypedKey<Double> EVALUATOR_KEY = new TypedKey<>("EVALUATOR", Double.class);
    private static final double DEFAULT_EPSILON = 1e-9;

    private final Map<TypedKey<?>, Object> cache = new ConcurrentHashMap<>();
    private final AstNode rootNode;
    private final String rawExpression;

    ArithmeticExpression(AstNode rootNode, String rawExpression) {
        this.rootNode = checkArgumentNotNull(rootNode, "The given root node must not be null.");
        this.rawExpression = checkArgumentNotNull(rawExpression, "The given raw expression must not be null.");
    }

    public static ArithmeticExpression parse(CharSequence expression) {
        return ExceptionTranslatingExecutor.execute(() -> ArithmeticInputParser.parseExpression(expression));
    }

    public static ArithmeticExpression parseFile(Path path) {
        return ExceptionTranslatingExecutor.execute(() -> ArithmeticInputParser.parseExpressionFile(path));
    }

    public double evaluate() {
        return resolve(EVALUATOR_KEY, new EvaluatorVisitor());
    }

    private <T> T resolve(TypedKey<T> key, AstVisitor<T> visitor) {
        return ExceptionTranslatingExecutor.execute(() -> {
            Object result = cache.get(key);

            if (result == null) {
                result = rootNode.accept(visitor);

                checkNotNullOrElseThrow(
                        result,
                        () -> new InternalException("AST traversal for " + key + " unexpectedly resulted in null.")
                );

                Object racedResult = cache.putIfAbsent(key, result);

                if (racedResult != null) {
                    // Because of a race condition, a concurrent thread has already computed and cached the result.
                    // To guarantee strict object identity, the local value is discarded and the existing cache entry
                    // is returned instead.
                    result = racedResult;
                }
            }

            return key.cast(result);
        });
    }

    public boolean isApproximatelyEqualTo(ArithmeticExpression other) {
        return isApproximatelyEqualTo(other, DEFAULT_EPSILON);
    }

    public boolean isApproximatelyEqualTo(ArithmeticExpression other, double epsilon) {
        return Math.abs(evaluate() - other.evaluate()) < epsilon;
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

        return Double.compare(evaluate(), other.evaluate()) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(evaluate());
    }

    @Override
    public String toString() {
        return rawExpression;
    }
}
