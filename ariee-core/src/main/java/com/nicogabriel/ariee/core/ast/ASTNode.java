package com.nicogabriel.ariee.core.ast;

import com.nicogabriel.ariee.core.visitor.ASTVisitor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class ASTNode implements Iterable<ASTNode> {

    private final int position;

    protected ASTNode(int position) {
        this.position = position;
    }

    public abstract <T> T accept(ASTVisitor<T> visitor);

    public Stream<ASTNode> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    public Stream<ASTNode> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }

    @Override
    public Iterator<ASTNode> iterator() {
        return new PreOrderIterator(this);
    }

    public int getPosition() {
        return position;
    }

    private static final class PreOrderIterator implements Iterator<ASTNode> {

        private final Deque<ASTNode> stack = new ArrayDeque<>();

        private PreOrderIterator(ASTNode root) {
            if (root != null) {
                stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ASTNode next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            ASTNode node = stack.pop();

            if (node instanceof OperatorNode operatorNode) {
                ASTNode left = operatorNode.getLeft();
                ASTNode right = operatorNode.getRight();

                // to maintain pre-order traversal, the right child is pushed before the left child
                // due to the stack's LIFO behavior, the left child is popped before the right child

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
