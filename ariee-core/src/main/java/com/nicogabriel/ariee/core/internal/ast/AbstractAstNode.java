package com.nicogabriel.ariee.core.internal.ast;

public abstract class AbstractAstNode implements AstNode {

    private final int position;

    protected AbstractAstNode(int position) {
        this.position = position;
    }

    @Override
    public int getPosition() {
        return position;
    }
}
