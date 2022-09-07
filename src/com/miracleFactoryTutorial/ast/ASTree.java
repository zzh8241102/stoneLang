package com.miracleFactoryTutorial.ast;

import java.util.Iterator;

public abstract class ASTree implements Iterable<ASTree> {
//    public abstract ASTree child();
    public abstract int numChildren();
    public abstract String location();
    public abstract Iterator<ASTree> children();
    public Iterator<ASTree> iterator() {return children();}
}
