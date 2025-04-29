package expression;

public class Negate extends Unary {

    public Negate(PrecedenceAware expr) {
        super(expr);
    }

    @Override
    public String getOperationString() {
        return "-";
    }

    @Override
    public int makeOperation(int x) {
        return -x;
    }

}
