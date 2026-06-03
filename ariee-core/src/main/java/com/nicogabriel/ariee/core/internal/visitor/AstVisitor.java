package com.nicogabriel.ariee.core.internal.visitor;

import com.nicogabriel.ariee.core.internal.ast.NumberNode;
import com.nicogabriel.ariee.core.internal.ast.OperatorNode;

public interface AstVisitor<T> {

    T visitNumber(NumberNode node);

    T visitOperator(OperatorNode node);
}
