package org.hypothesis.evaluation;


import org.hypothesis.interfaces.variable.ExchangeVariable;
import org.hypothesis.interfaces.variable.Variable;

import javax.annotation.Nonnull;
import java.util.Map;

public class IndexedExpression implements ExchangeVariable {

    private final int index;
    private final ExpressionImpl expression;

    public IndexedExpression(int index, ExpressionImpl expression) {
        this.index = index;
        this.expression = expression;
    }

    @Override
    public int getIndex() {
        return index;
    }

    public ExpressionImpl getExpression() {
        return expression;
    }

    @Override
    public Object getValue() {
        if (expression != null) {
            return expression.getValue();
        }

        return null;
    }

    @Override
    public void setVariables(@Nonnull Map<String, Variable<?>> variables) {
        if (expression != null) {
            expression.setVariables(variables);
        }
    }

}
