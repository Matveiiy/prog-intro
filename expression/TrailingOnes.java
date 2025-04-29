package expression;

public class TrailingOnes extends Unary {
    
    public TrailingOnes(PrecedenceAware expr) {
        super(expr);
    }

    @Override
    public String getOperationString() {
        return "t1";
    }

    @Override
    public int makeOperation(int x) {
        if (x == 0) return 0;
        for (int i = 0; i < 32; ++i) {
            int cur = 1 << i;
            if ((cur & x) == 0) return i;
        }
        return 32;
    }
}
