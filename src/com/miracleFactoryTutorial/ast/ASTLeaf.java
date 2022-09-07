package com.miracleFactoryTutorial.ast;

import com.miracleFactoryTutorial.Token;

import java.util.ArrayList;
import java.util.Iterator;

public class ASTLeaf extends ASTree {
    public Token token;
    private static ArrayList<ASTree> empty = new ArrayList<ASTree>();
    public ASTLeaf(Token t){this.token=t;}
    public ASTree child(int i){throw new IndexOutOfBoundsException();}
    public int numChildren(){return 0;}
    public Iterator<ASTree> children() {return empty.iterator();}
    public String toString() {return token.getText();}
    public String location() {return "at line"+token.getLineNumber();}
    public Token token(){return token;}
}
