package org.hypothesis.builders;

import com.tilioteo.expressions.ExpressionFactory;
import org.hypothesis.evaluation.ExpressionImpl;
import org.hypothesis.interfaces.evaluable.Evaluable;
import org.hypothesis.interfaces.variable.Variable;
import org.hypothesis.interfaces.variable.WithVariables;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SwitchImpl implements Evaluable {

    private final WithVariables variables;
    private final ExpressionImpl expression;

    private final Map<String, List<Evaluable>> caseMap = new HashMap<>();

    public SwitchImpl(WithVariables variables, ExpressionImpl expression) {
        this.variables = variables;
        this.expression = expression;
    }

    public void addCaseEvaluable(String caseValue, Evaluable evaluable) {
        List<Evaluable> evaluables = caseMap.computeIfAbsent(caseValue, k -> new ArrayList<>());

        evaluables.add(evaluable);
    }

    public void evaluate() {
        if (expression != null && variables != null) {
            Object result = expression.getValue();
            if (result != null) {
                com.tilioteo.expressions.Expression expression = ExpressionFactory.parseString("a==b");
                expression.setVariableValue("a", result);

                for (String caseValue : caseMap.keySet()) {
                    com.tilioteo.expressions.Expression caseExpression = ExpressionFactory.parseString(caseValue);
                    expression.setVariableValue("b", caseExpression.getValue());
                    Boolean value = expression.getBoolean();

                    if (value != null && value) {
                        List<Evaluable> evaluables = caseMap.get(caseValue);
                        if (evaluables != null) {
                            for (Evaluable evaluable : evaluables) {
                                evaluable.setVariables(variables.getVariables());
                                evaluable.evaluate();
                                evaluable.updateVariables(variables.getVariables());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setVariables(@Nonnull Map<String, Variable<?>> variables) {
        if (expression != null) {
            expression.setVariables(variables);
        }
    }

    @Override
    public void updateVariables(@Nonnull Map<String, Variable<?>> variables) {
        if (expression != null) {
            expression.updateVariables(variables);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("switch (" + expression.toString() + ") {\n");
        for (Object value : caseMap.keySet()) {
            builder.append("\tcase ").append(value.toString()).append(" : {\n");
            List<Evaluable> evaluables = caseMap.get(value);
            for (Evaluable evaluable : evaluables) {
                builder.append("\t\t").append(evaluable.toString()).append(";\n");
            }
            builder.append("\t}\n");
        }
        builder.append("}");

        return builder.toString();
    }

}
