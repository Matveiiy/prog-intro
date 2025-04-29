package expression;

public interface PrecedenceAware extends TripleExpression {
    int precedence();
}
