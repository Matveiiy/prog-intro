package expression;

import java.util.Objects;

public abstract class BinaryExpression implements Expression, TripleExpression, PrecedenceAware {
    protected PrecedenceAware left;
    protected PrecedenceAware right;

    public BinaryExpression(PrecedenceAware left, PrecedenceAware right) {
        this.left = left;
        this.right = right;
    }

    protected boolean leftAssoc(PrecedenceAware e) {
        return false;
    }


    protected boolean rightAssoc(PrecedenceAware e) {
        return false;
    }

    protected abstract String getOperationString();

    private static StringBuilder wrap(PrecedenceAware e, StringBuilder res, int p, boolean assoc) {
        int p2 = e.precedence();
        if (p2 > p || (assoc && p2 == p)) {
            return res.append(e.toMiniString());
        }
        return res.append("(").append(e.toMiniString()).append(")");
    }

    public String toString() {
        return new StringBuilder()
                .append("(")
                .append(left.toString())
                .append(" ")
                .append(getOperationString())
                .append(" ")
                .append(right.toString())
                .append(")")
                .toString();
    }

    public String toMiniString() {
        int p = precedence();
        StringBuilder lhs = wrap(left, new StringBuilder(), p, leftAssoc(left))
                .append(' ')
                .append(getOperationString())
                .append(' ');
        return wrap(right, lhs, p, rightAssoc(right)).toString();
    }

    public abstract int makeOperation(int a, int b);

    @Override
    public int evaluate(int x) {
        return makeOperation(((Expression) left).evaluate(x), ((Expression) right).evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return makeOperation(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryExpression that = (BinaryExpression) o;
        return Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, this.getClass());
    }
}
