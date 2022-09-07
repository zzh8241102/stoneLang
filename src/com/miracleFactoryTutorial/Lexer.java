package com.miracleFactoryTutorial;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.miracleFactoryTutorial.Token;

public class Lexer {
    public static String regexPat = "\\s*((//.*)|([0-9]+)|" +
            "(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")"
            + "|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";
    private Pattern pattern = Pattern.compile(regexPat);
    private ArrayList<Token> queue = new ArrayList<>();
    private boolean hasMore; // sever as readline's terminator
    private LineNumberReader reader;

    public Lexer(Reader r) {
        hasMore = true;
        reader = new LineNumberReader(r);
    }

    public Token read() throws ParseException{
        if(fillQueue(0)){
            return queue.remove(0);
        }
        else{
            return Token.EOF;
        }
    }

    public Token peek(int i) throws ParseException {
        if(fillQueue(i)){
            return queue.get(i);
        }
        else
            return Token.EOF;
    }

    public boolean fillQueue(int i) throws ParseException { // call readline => that's why
        while(i >= queue.size()){
            if(hasMore){
                readline();
            }
            else {return false;}

        }
        return true;
    }

    protected void readline() throws ParseException {
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ParseException(e);
        }
        if (line == null) {
            hasMore = false;
        }
        int lineNo = reader.getLineNumber();
        Matcher matcher = pattern.matcher(line);
        matcher.useTransparentBounds(true).useAnchoringBounds(false); // https://www.jianshu.com/p/aee8aae74951
        for (int currentPos = 0; currentPos < line.length(); ) {
            matcher.region(currentPos, line.length());
            if (matcher.lookingAt()) {
                addToken(lineNo, matcher);
                currentPos = matcher.end();
            } else
                throw new ParseException("bad token at line " + lineNo);
        }
        queue.add(new IdToken(lineNo, Token.EOL));
    }

    protected void addToken(int lineNo, Matcher matcher) {
        String m = matcher.group(1);
        if (m != null) {
            if (matcher.group(2) != null) {
                Token token;
                if (matcher.group(3) != null) {
                    token = new NumToken(lineNo, Integer.parseInt(m));
                } else if (matcher.group(4) != null) {
                    token = new StrToken(lineNo, toStringLiteral(m));
                } else {
                    token = new IdToken(lineNo, m);
                }
                this.queue.add(token);
            }
        }
    }

    protected String toStringLiteral(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length() - 1;
        for (int i = 1; i < len; i++) {
            char c = s.charAt(i);
            if (c == '\\' && i + 1 < len) {
                int c2 = s.charAt(i + 1);
                if (c2 == '"' || c2 == '\\')
                    c = s.charAt(++i);
                else if (c2 == 'n') {
                    ++i;
                    c = '\n';
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    protected static class NumToken extends Token {
        private int value;

        protected NumToken(int line, int value) {
            super(line);
            this.value = value;
        }

        public boolean isNumber() {
            return true;
        }

        public String getText() {
            return Integer.toString(value);
        }

        public int getNumber() {
            return value;
        }
    }

    protected static class StrToken extends Token {
        private String strLiteral;

        protected StrToken(int line, String strLiteral) {
            super(line);
            this.strLiteral = strLiteral;
        }

        public boolean isString() {
            return true;
        }

        public String getText() {
            return strLiteral;
        }
    }

    protected static class IdToken extends Token {
        private String identifier;

        protected IdToken(int line, String id) {
            super(line);
            this.identifier = id;
        }
    }

}

