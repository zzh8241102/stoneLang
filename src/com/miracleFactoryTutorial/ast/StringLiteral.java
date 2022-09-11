package com.miracleFactoryTutorial.ast;

import com.miracleFactoryTutorial.Token;

public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token t) {
        super(t);
    }
    public String value() {
        return token().getText();
    }
}