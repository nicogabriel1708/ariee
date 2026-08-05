package com.nicogabriel.ariee.core.internal.parser;

import com.nicogabriel.ariee.core.internal.ast.AstNode;
import com.nicogabriel.ariee.core.internal.lexer.Lexer;

import static com.nicogabriel.ariee.core.internal.util.Preconditions.checkArgumentNotNull;

public final class ArieeParser implements Parser {

    private final Lexer lexer;

    public ArieeParser(Lexer lexer) {
        this.lexer = checkArgumentNotNull(lexer, "The given lexer must not be null.");
    }

    @Override
    public AstNode parse() {
        return null;
    }
}

/*
package com.nicogabriel.ariee.core.parser;

import com.nicogabriel.ariee.core.ast.ASTNode;
import com.nicogabriel.ariee.core.ast.NumberNode;
import com.nicogabriel.ariee.core.ast.OperatorNode;
import com.nicogabriel.ariee.core.token.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public final class Parser {

    private final Deque<Token> operatorTokenStack = new ArrayDeque<>();
    private final Deque<ASTNode> treeNodeStack = new ArrayDeque<>();

    public ASTNode parse(List<Token> tokens) {
        for (Token token : tokens) {
            switch (token.getType()) {
                case NUMBER -> handleNumber((NumberToken) token);
                case BRACKET -> handleBracket((Bracket) token);
                case OPERATOR -> handleOperator((Operator) token);
            }
        }

        while (!operatorTokenStack.isEmpty()) {
            buildAstBranch();
        }

        if (treeNodeStack.size() != 1) {
            throw new IllegalStateException("Invalid expression structure!");
        }

        return treeNodeStack.pop();
    }

    private void handleNumber(NumberToken numberToken) {
        treeNodeStack.push(new NumberNode(numberToken.value()));
    }

    private void handleBracket(Bracket currentBracket) {
        if (currentBracket.isOpen()) {
            operatorTokenStack.push(currentBracket);
            return;
        }

        // It's a close bracket
        while (!operatorTokenStack.isEmpty()) {
            Token topToken = operatorTokenStack.peek();

            if (topToken.getType() == TokenType.BRACKET) {
                Bracket openBracket = (Bracket) topToken;
                if (!currentBracket.matches(openBracket)) {
                    throw new IllegalArgumentException("Mismatched brackets!");
                }
                operatorTokenStack.pop(); // Discard the matching open bracket
                break;
            }
            buildAstBranch();
        }
    }

    private void handleOperator(Operator newOp) {
        while (!operatorTokenStack.isEmpty()) {
            Token topToken = operatorTokenStack.peek();

            if (topToken.getType() == TokenType.OPERATOR) {
                Operator topOp = (Operator) topToken;
                if (topOp.evaluatesBefore(newOp)) {
                    buildAstBranch();
                    continue;
                }
            }
            break;
        }
        operatorTokenStack.push(newOp);
    }

    private void buildAstBranch() {
        Operator op = (Operator) operatorTokenStack.pop();
        ASTNode right = treeNodeStack.pop();
        ASTNode left = treeNodeStack.pop();
        treeNodeStack.push(new OperatorNode(op, left, right));
    }
}
 */
