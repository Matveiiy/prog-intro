package expression.parser;

import expression.*;

import java.util.Objects;

public class ExpressionParser implements TripleParser {
    public ExpressionParser() {

    }

    int pos = 0;
    String curTok;
    String data;

    String getNextOne() {
        while (pos < data.length() && Character.isWhitespace(data.charAt(pos))) {
            pos++;
        }
        if (pos == data.length()) return "";
        return curTok = String.valueOf(data.charAt(pos++));
    }

    String getNext() {
        while (pos < data.length() && Character.isWhitespace(data.charAt(pos))) {
            pos++;
        }
        if (pos == data.length()) return curTok = "";
        if (Character.isDigit(data.charAt(pos)) || data.charAt(pos) == '-') {
            int start = pos++;
            while (pos < data.length() && Character.isDigit(data.charAt(pos))) pos++;
            return curTok = data.substring(start, pos);
        }
        return curTok = String.valueOf(data.charAt(pos++));
    }

    public PrecedenceAware parseFactor() {
        var lhs = parsePrimary();
        while (Objects.equals(curTok, "*") || Objects.equals(curTok, "/")) {
            char c = curTok.charAt(0);
            getNext();
            if (c == '*') lhs = new Multiply(lhs, parsePrimary());
            else lhs = new Divide(lhs, parsePrimary());
        }
        return lhs;
    }

    public PrecedenceAware parseTerm() {
        var lhs = parseFactor();
        while (Objects.equals(curTok, "-") || Objects.equals(curTok, "+")) {
            char c = curTok.charAt(0);
            getNext();
            if (c == '+') lhs = new Add(lhs, parseFactor());
            else lhs = new Subtract(lhs, parseFactor());
        }
        return lhs;
    }

    public PrecedenceAware parseAnd() {
        var lhs = parseTerm();
        while (Objects.equals(curTok, "&")) {
            getNext();
            lhs = new BitwiseAnd(lhs, parseTerm());
        }
        return lhs;
    }

    public PrecedenceAware parseXor() {
        var lhs = parseAnd();
        while (Objects.equals(curTok, "^")) {
            getNext();
            lhs = new BitwiseXor(lhs, parseAnd());
        }
        return lhs;
    }

    public PrecedenceAware parseOr() {
        var lhs = parseXor();
        while (Objects.equals(curTok, "|")) {
            getNext();
            lhs = new BitwiseOr(lhs, parseXor());
        }
        return lhs;
    }

    public PrecedenceAware parseExpression() {
        return parseOr();
    }

    @Override
    public TripleExpression parse(String expression) {
        data = expression;
        pos = 0;
        curTok = getNext();
        return parseExpression();
    }

    public PrecedenceAware parsePrimary() {
        if (Objects.equals(curTok, "(")) {
            getNext();
            var x = parseExpression();
            getNextOne();
            return x;
        }
        if (Objects.equals(curTok, "-")) {
            getNext();
            var x = parsePrimary();
            return new Negate(x);
        }
        if (Objects.equals(curTok, "l")) {
            getNextOne();
            getNext();
            var x = parsePrimary();
            return new LeadingOnes(x);
        }
        if (Objects.equals(curTok, "t")) {
            getNextOne();
            getNext();
            var x = parsePrimary();
            return new TrailingOnes(x);
        }
        String val = curTok;
        getNextOne();
        if (val.equals("x") || val.equals("y") || val.equals("z")) {
            return new Variable(val);
        }
        return new Const(Integer.parseInt(val));
    }
}
