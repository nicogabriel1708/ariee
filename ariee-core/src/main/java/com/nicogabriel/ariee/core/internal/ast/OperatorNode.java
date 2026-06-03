package com.nicogabriel.ariee.core.internal.ast;

import com.nicogabriel.ariee.core.internal.token.Operator;
import com.nicogabriel.ariee.core.internal.visitor.AstVisitor;

public final class OperatorNode extends AstNode {

    private final Operator operator;
    private final AstNode left;
    private final AstNode right;

    public OperatorNode(Operator operator, AstNode left, AstNode right, int position) {
        super(position);
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitOperator(this);
    }

    public Operator getOperator() {
        return operator;
    }

    public AstNode getLeft() {
        return left;
    }

    public AstNode getRight() {
        return right;
    }
}
