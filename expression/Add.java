package expression;

import java.math.BigInteger;

public class Add extends BigIntBinaryExpression {
    public Add(PrecedenceAware left, PrecedenceAware right) {
        super(left, right);
    }

    @Override
    public int precedence() {
        return 400;
    }

    @Override
    protected boolean leftAssoc(PrecedenceAware e) {
        return true;
    }

    @Override
    protected boolean rightAssoc(PrecedenceAware e) {
        return true;
    }

    @Override
    protected String getOperationString() {
        return "+";
    }

    @Override
    public int makeOperation(int left, int right) {
        return left + right;
    }

    @Override
    public BigInteger makeOperation(BigInteger left, BigInteger right) {
        return left.add(right);
    }
}
