package expression;

import java.math.BigInteger;

public class Const implements Expression, BigIntegerExpression, TripleExpression, PrecedenceAware {
    private final Number val;

    public Const(int val) {
        this.val = val;
    }

    public Const(BigInteger val) {
        this.val = val;
    }


    @Override
    public int evaluate(int x) {
        return val.intValue();
    }


    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const constant = (Const) o;
        return val.equals(constant.val);
    }

    @Override
    public int hashCode() {
        return val.hashCode();
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return (BigInteger) val;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return val.intValue();
    }

    @Override
    public int precedence() {
        return 600;
    }
}
