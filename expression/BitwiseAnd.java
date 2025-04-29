package expression;

public class BitwiseAnd extends BinaryExpression {

    public BitwiseAnd(PrecedenceAware left, PrecedenceAware right) {
        super(left, right);
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
        return "&";
    }

    @Override
    public int makeOperation(int a, int b) {
        return a & b;
    }

    @Override
    public int precedence() {
        return 300;
    }
}
