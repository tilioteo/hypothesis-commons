package org.hypothesis.evaluation;

import org.hypothesis.interfaces.variable.ExchangeVariable;
import org.hypothesis.interfaces.variable.Variable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.hypothesis.evaluation.VariableFactory.createVariable;

public class Nick implements Serializable {

    private final Long slideId;
    private ExpressionImpl expression;

    public Nick(Long slideId) {
        this.slideId = slideId;
    }

//    public ExpressionImpl getExpression() {
//        return expression;
//    }

    public void setExpression(ExpressionImpl expression) {
        this.expression = expression;
    }

    public Long getSlideId() {
        return slideId;
    }

    public boolean pass(Map<Integer, ExchangeVariable> inputs) {
        if (inputs != null && expression != null) {
            Map<String, Variable<?>> variables = new HashMap<>();

            for (Integer index : inputs.keySet()) {
                ExchangeVariable exchangeVariable = inputs.get(index);
                Variable<?> variable = createVariable("output" + index,
                        exchangeVariable.getValue());
                variables.put(variable.getName(), variable);
            }

            expression.setVariables(variables);
            return expression.getBoolean();
        }

        return false;
    }
}
