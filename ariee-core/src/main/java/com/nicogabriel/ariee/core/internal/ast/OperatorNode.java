package com.nicogabriel.ariee.core.internal.ast;

import com.nicogabriel.ariee.core.internal.token.Operator;
import com.nicogabriel.ariee.core.internal.visitor.ASTVisitor;

public final class OperatorNode extends ASTNode {

    private final Operator operator;
    private final ASTNode left;
    private final ASTNode right;

    public OperatorNode(Operator operator, ASTNode left, ASTNode right, int position) {
        super(position);
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitOperator(this);
    }

    public Operator getOperator() {
        return operator;
    }

    public ASTNode getLeft() {
        return left;
    }

    public ASTNode getRight() {
        return right;
    }
}
