package com.nicogabriel.ariee.core.visitor;

import com.nicogabriel.ariee.core.ast.NumberNode;
import com.nicogabriel.ariee.core.ast.OperatorNode;

public interface ASTVisitor<T> {

    T visitNumber(NumberNode node);

    T visitOperator(OperatorNode node);
}
