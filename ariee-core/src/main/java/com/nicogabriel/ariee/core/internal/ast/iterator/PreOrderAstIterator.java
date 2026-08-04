package com.nicogabriel.ariee.core.internal.ast.iterator;

import com.nicogabriel.ariee.core.internal.ast.AstNode;

import java.util.*;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgumentNotNull;
import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkNotNull;

public final class PreOrderAstIterator implements Iterator<AstNode> {

    private final Deque<AstNode> stack = new ArrayDeque<>();

    public PreOrderAstIterator(AstNode root) {
        stack.push(checkArgumentNotNull(root, "The given root node must not be null."));
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public AstNode next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        AstNode node = stack.pop();
        List<AstNode> children = checkNotNull(
                node.getChildren(),
                () -> "The " + node.getClass().getSimpleName() + ".getChildren() method must not return null. "
                      + "Instead, if a node in the AST is a leaf node, i.e. has no children, "
                      + "it should return an empty list."
        );

        // the children are pushed in reverse order to maintain pre-order traversal (due to the stack's LIFO behavior)
        for (AstNode child : children.reversed()) {
            stack.push(child);
        }

        return node;
    }
}
