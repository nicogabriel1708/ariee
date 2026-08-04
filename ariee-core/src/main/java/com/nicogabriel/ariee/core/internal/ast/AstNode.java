package com.nicogabriel.ariee.core.internal.ast;

import com.nicogabriel.ariee.core.internal.ast.iterator.PreOrderAstIterator;
import com.nicogabriel.ariee.core.internal.visitor.AstVisitor;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface AstNode extends Iterable<AstNode> {

    <T> T accept(AstVisitor<T> visitor);

    int getPosition();

    List<AstNode> getChildren();

    default Stream<AstNode> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    default Stream<AstNode> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }

    @Override
    default Iterator<AstNode> iterator() {
        return new PreOrderAstIterator(this);
    }
}
