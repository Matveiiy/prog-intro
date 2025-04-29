package expression;

import java.math.BigInteger;

public class Divide extends BigIntBinaryExpression {
    public Divide(PrecedenceAware left, PrecedenceAware right) {
        super(left, right);
    }

    @Override
    protected boolean leftAssoc(PrecedenceAware e) {
        return true;
    }

    @Override
    protected String getOperationString() {
        return "/";
    }

    @Override
    public int makeOperation(int a, int b) {
        return a / b;
    }

    @Override
    public BigInteger makeOperation(BigInteger a, BigInteger b) {
        return a.divide(b);
    }

    @Override
    public int precedence() {
        return 500;
    }
}
