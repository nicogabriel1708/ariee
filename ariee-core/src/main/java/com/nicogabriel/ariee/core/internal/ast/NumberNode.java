package com.nicogabriel.ariee.core.internal.ast;

import com.nicogabriel.ariee.core.ArithmeticExpression;
import com.nicogabriel.ariee.core.internal.visitor.AstVisitor;

import java.util.List;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgumentNotNull;

public final class NumberNode extends AbstractAstNode {

    private final double value;

    public NumberNode(double value, int position) {
        super(position);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        checkArgumentNotNull(visitor, "The given AST visitor must not be null.");

        return visitor.visitNumber(this);
    }

    @Override
    public List<AstNode> getChildren() {
        return List.of();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        NumberNode other = (NumberNode) object;

        return Double.compare(value, other.value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
