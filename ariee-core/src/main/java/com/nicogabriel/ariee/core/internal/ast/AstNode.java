package com.nicogabriel.ariee.core.internal.ast;

import com.nicogabriel.ariee.core.internal.visitor.AstVisitor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class AstNode implements Iterable<AstNode> {

    private final int position;

    protected AstNode(int position) {
        this.position = position;
    }

    public abstract <T> T accept(AstVisitor<T> visitor);

    public Stream<AstNode> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    public Stream<AstNode> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }

    @Override
    public Iterator<AstNode> iterator() {
        return new PreOrderIterator(this);
    }

    public int getPosition() {
        return position;
    }

    private static final class PreOrderIterator implements Iterator<AstNode> {

        private final Deque<AstNode> stack = new ArrayDeque<>();

        private PreOrderIterator(AstNode root) {
            if (root != null) {
                stack.push(root);
            }
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

            if (node instanceof OperatorNode operatorNode) {
                AstNode left = operatorNode.getLeft();
                AstNode right = operatorNode.getRight();

                // the right child is pushed before the left child, so that due to the stack's LIFO behavior,
                // the left child is popped before the right child, maintaining pre-order traversal

                if (right != null) {
                    stack.push(right);
                }

                if (left != null) {
                    stack.push(left);
                }
            }

            return node;
        }
    }
}
