package com.nicogabriel.ariee.core.internal.ast;

import com.nicogabriel.ariee.core.internal.visitor.AstVisitor;

public final class NumberNode extends AstNode {

    private final double value;

    public NumberNode(double value, int position) {
        super(position);
        this.value = value;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitNumber(this);
    }

    public double getValue() {
        return value;
    }
}
