package expression;

import java.util.Objects;

public abstract class Unary implements PrecedenceAware {
    PrecedenceAware expr;

    public Unary(PrecedenceAware expr) {
        this.expr = expr;
    }

    public abstract String getOperationString();

    public abstract int makeOperation(int x);

    @Override
    public int precedence() {
        return 600;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return makeOperation(expr.evaluate(x, y, z));
    }

    @Override
    public String toMiniString() {
        if (this.expr.precedence() < 600) {
            return this.getOperationString() + "(" + this.expr.toMiniString() + ")";
        }
        return this.getOperationString() + " " + this.expr.toMiniString();
    }

    @Override
    public String toString() {
        return this.getOperationString() + "(" + this.expr.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unary unary = (Unary) o;
        return Objects.equals(expr, unary.expr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expr, this.getClass());
    }
}
