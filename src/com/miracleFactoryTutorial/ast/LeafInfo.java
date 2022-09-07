package com.miracleFactoryTutorial.ast;

import com.miracleFactoryTutorial.Token;

public class LeafInfo extends ASTLeaf {
        public LeafInfo(Token t) { super(t); }
        public String getName() { return token.getText();}
    }


