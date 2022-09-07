package com.miracleFactoryTutorial.ast;

import com.miracleFactoryTutorial.Token;

public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token t){super(t);}
    private int value(){return token.getNumber();}
}
