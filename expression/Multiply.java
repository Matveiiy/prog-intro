package expression;

import java.math.BigInteger;

public class Multiply extends BigIntBinaryExpression {
    @Override
    protected String getOperationString() {
        return "*";
    }

    public Multiply(PrecedenceAware left, PrecedenceAware right) {
        super(left, right);
    }

    @Override
    protected boolean leftAssoc(PrecedenceAware e) {
        return true;
    }

    @Override
    public boolean rightAssoc(PrecedenceAware e) {
        return !(e instanceof Divide);
    }

    @Override
    public int makeOperation(int a, int b) {
        return a * b;
    }

    @Override
    public BigInteger makeOperation(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public int precedence() {
        return 500;
    }

}
