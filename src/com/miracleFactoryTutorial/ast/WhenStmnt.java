package com.miracleFactoryTutorial.ast;

import java.util.List;

public class WhenStmnt extends ASTList {

    public WhenStmnt(List<ASTree> list) { super(list); }

    public ASTree condition() { return child(0); }

    public ASTree thenBlock() { return child(1); }

    public String toString() {
        return "(when " + condition() + " " + thenBlock() + ")";
    }

}