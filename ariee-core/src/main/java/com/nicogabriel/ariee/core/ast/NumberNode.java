package com.nicogabriel.ariee.core.ast;

import com.nicogabriel.ariee.core.visitor.ASTVisitor;

public final class NumberNode extends ASTNode {

    private final double value;

    public NumberNode(double value, int position) {
        super(position);
        this.value = value;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitNumber(this);
    }

    public double getValue() {
        return value;
    }
}
