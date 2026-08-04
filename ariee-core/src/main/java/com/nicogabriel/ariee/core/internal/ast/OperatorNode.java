package com.nicogabriel.ariee.core.internal.ast;

import com.nicogabriel.ariee.core.internal.token.Operator;
import com.nicogabriel.ariee.core.internal.visitor.AstVisitor;

import java.util.List;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgumentNotNull;

public final class OperatorNode extends AbstractAstNode {

    private final Operator operator;
    private final AstNode left;
    private final AstNode right;

    public OperatorNode(Operator operator, AstNode left, AstNode right, int position) {
        super(position);
        this.operator = checkArgumentNotNull(operator, "The given operator must not be null.");
        this.left = checkArgumentNotNull(left, "The given left child node must not be null.");
        this.right = checkArgumentNotNull(right, "The given right child node must not be null.");
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

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        checkArgumentNotNull(visitor, "The given AST visitor must not be null.");

        return visitor.visitOperator(this);
    }

    @Override
    public List<AstNode> getChildren() {
        return List.of(left, right);
    }
}
