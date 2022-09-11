package com.miracleFactoryTutorial.ast;

import com.miracleFactoryTutorial.Token;

public class Name extends ASTLeaf {
        public Name(Token t) { super(t); }
        public String getName() { return token.getText();}
    }


