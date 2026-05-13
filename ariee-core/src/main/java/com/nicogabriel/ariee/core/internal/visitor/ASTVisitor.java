package com.nicogabriel.ariee.core.internal.visitor;

import com.nicogabriel.ariee.core.internal.ast.NumberNode;
import com.nicogabriel.ariee.core.internal.ast.OperatorNode;

public interface ASTVisitor<T> {

    T visitNumber(NumberNode node);

    T visitOperator(OperatorNode node);
}
