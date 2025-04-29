package expression;

public class LeadingOnes extends Unary {
    public LeadingOnes(PrecedenceAware expr) {
        super(expr);
    }

    @Override
    public String getOperationString() {
        return "l1";
    }

    @Override
    public int makeOperation(int x) {
        if (x == 0) return 0;
        for (int i = 31; i >= 0; --i) {
            int cur = 1 << i;
            if ((cur & x) == 0) return 31 - i;
        }
        return 32;
    }
}
