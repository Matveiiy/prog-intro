package expression;

import java.math.BigInteger;

public class Subtract extends BigIntBinaryExpression {
    public Subtract(PrecedenceAware left, PrecedenceAware right) {
        super(left, right);
    }

    @Override
    protected String getOperationString() {
        return "-";
    }

    @Override
    public int makeOperation(int a, int b) {
        return a - b;
    }

    @Override
    public BigInteger makeOperation(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    protected boolean leftAssoc(PrecedenceAware e) {
        return true;
    }

    @Override
    public int precedence() {
        return 400;
    }
}
