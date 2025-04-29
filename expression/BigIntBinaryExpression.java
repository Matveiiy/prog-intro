package expression;

import java.math.BigInteger;

public abstract class BigIntBinaryExpression extends BinaryExpression implements BigIntegerExpression {

    public BigIntBinaryExpression(PrecedenceAware left, PrecedenceAware right) {
        super(left, right);
    }

    public abstract BigInteger makeOperation(BigInteger a, BigInteger b);

    @Override
    public BigInteger evaluate(BigInteger x) {
        return makeOperation(((BigIntegerExpression) left).evaluate(x), ((BigIntegerExpression) right).evaluate(x));
    }
}
