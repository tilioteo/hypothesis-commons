package org.hypothesis.evaluation;

import com.tilioteo.expressions.Expression;
import com.tilioteo.expressions.UnaryExpression;
import org.hypothesis.interfaces.evaluable.Evaluable;
import org.hypothesis.interfaces.variable.Variable;

import javax.annotation.Nonnull;
import java.util.Map;

public class ExpressionImpl implements Evaluable {

    private final Expression internalExpression;

    public ExpressionImpl(Expression expression) {
        this.internalExpression = expression;
    }

    @Override
    public void evaluate() {
        getValue();
    }

    public Boolean getBoolean() {
        if (internalExpression != null) {
            return internalExpression.getBoolean();
        }

        return null;
    }

    public String getSimpleVariableName() {
        if (internalExpression != null && internalExpression instanceof UnaryExpression) {
            UnaryExpression expression = (UnaryExpression) internalExpression;
            if (expression.getRightSide() != null
                    && expression.getRightSide() instanceof Variable) {
                return ((Variable<?>) expression.getRightSide()).getName();
            }
        }

        return null;
    }

    public Object getValue() {
        if (internalExpression != null) {
            return internalExpression.getValue();
        }
        return null;
    }

    @Override
    public void setVariables(@Nonnull Map<String, Variable<?>> variables) {
        if (internalExpression != null) {
            for (String key : variables.keySet()) {
                Variable<?> variable = variables.get(key);
                internalExpression.setVariableValue(key, variable.getValue());
            }
        }
    }

    @Override
    public void updateVariables(@Nonnull Map<String, Variable<?>> variables) {
        if (internalExpression != null) {
            for (String key : variables.keySet()) {
                Variable<?> variable = variables.get(key);
                if (internalExpression.hasVariable(key)) {
                    Object value = internalExpression.getVariableValue(key);
                    variable.setRawValue(value);
                }
            }
        }
    }

    @Override
    public String toString() {
        return internalExpression != null ? internalExpression.toString() : "<null>";
    }

}
