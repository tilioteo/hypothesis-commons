package org.hypothesis.builders;

import org.hypothesis.evaluation.ExpressionImpl;
import org.hypothesis.interfaces.evaluable.Evaluable;
import org.hypothesis.interfaces.variable.Variable;
import org.hypothesis.interfaces.variable.WithVariables;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class IfImpl implements Evaluable {

    private final WithVariables variables;
    private final ExpressionImpl expression;
    private final List<Evaluable> trueBlock = new ArrayList<>();
    private final List<Evaluable> falseBlock = new ArrayList<>();

    public IfImpl(WithVariables variables, ExpressionImpl expression) {
        this.variables = variables;
        this.expression = expression;
    }

    public void addFalseEvaluable(Evaluable evaluable) {
        falseBlock.add(evaluable);
    }

    public void addTrueEvaluable(Evaluable evaluable) {
        trueBlock.add(evaluable);
    }

    public void evaluate() {
        if (expression != null && variables != null) {
            Boolean result = expression.getBoolean();
            expression.updateVariables(variables.getVariables());

            if (result != null) {
                List<Evaluable> evaluables = result ? trueBlock : falseBlock;

                for (Evaluable evaluable : evaluables) {
                    evaluable.setVariables(variables.getVariables());
                    evaluable.evaluate();
                    evaluable.updateVariables(variables.getVariables());
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
        // NOTE If statement cannot update variables after block evaluation
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("if (" + expression.toString() + ") {\n");
        for (Evaluable evaluable : trueBlock) {
            builder.append("\t").append(evaluable.toString()).append(";\n");
        }

        builder.append("}");
        if (!falseBlock.isEmpty()) {
            builder.append(" else {\n");
            for (Evaluable evaluable : falseBlock) {
                builder.append("\t").append(evaluable.toString()).append(";\n");
            }
            builder.append("}");
        }

        return builder.toString();
    }

}
