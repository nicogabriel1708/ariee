package com.nicogabriel.ariee.core.ast;

import com.nicogabriel.ariee.core.visitor.ASTVisitor;

public abstract class ASTNode {

    private final int position;

    protected ASTNode(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public abstract <T> T accept(ASTVisitor<T> visitor);
}
