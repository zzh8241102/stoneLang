package com.miracleFactoryTutorial.ast;

import java.util.Iterator;
import java.util.List;

public class ASTList extends ASTree {

    protected List<ASTree> children;

    public ASTList(List<ASTree> list) { this.children = list; }


    public ASTree child(int i) { return children.get(i); }

    public int numChildren() { return children.size(); }

    public Iterator<ASTree> children() { return children.iterator(); }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        String sep = "";
        for (ASTree t : children) {
            sb.append(sep);
            sep = ", ";
            sb.append(t.toString());
        }

        return sb.append(')').toString();
    }

    @Override
    public String location() {
        for (ASTree t : children) {
            String loc = t.location();
            if (loc != null) {
                return loc;
            }
        }
        return null;
    }

}