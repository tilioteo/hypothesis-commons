package org.hypothesis.builders;

import org.hypothesis.evaluation.AbstractBaseAction;
import org.hypothesis.interfaces.evaluable.Evaluable;
import org.hypothesis.interfaces.variable.ExchangeVariable;
import org.hypothesis.interfaces.variable.Variable;
import org.hypothesis.interfaces.variable.WithVariables;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionImpl extends AbstractBaseAction {

    private final Map<Integer, ExchangeVariable> outputValues = new HashMap<>();
    private final Map<Integer, ExchangeVariable> scores = new HashMap<>();
    private final List<Evaluable> evaluables = new ArrayList<>();

    public ActionImpl(WithVariables variables, String id) {
        super(variables, id);
    }

    public void addEvaluable(@Nonnull Evaluable evaluable) {
        evaluables.add(evaluable);
    }

    @Override
    public void execute() {
        super.execute();

        for (Evaluable evaluable : evaluables) {
            final Map<String, Variable<?>> variables = getVariables();
            evaluable.setVariables(variables);
            evaluable.evaluate();
            evaluable.updateVariables(variables);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getId() + "() {\n");
        for (Evaluable evaluable : evaluables) {
            builder.append("\t").append(evaluable.toString()).append(";\n");
        }
        builder.append("}");

        return builder.toString();
    }

    @Nonnull
    @Override
    public Map<Integer, ExchangeVariable> getOutputs() {
        Map<String, Variable<?>> variables = getVariables();
        for (ExchangeVariable outputValue : outputValues.values()) {
            outputValue.setVariables(variables);
        }

        return outputValues;
    }

    @Nonnull
    @Override
    public Map<Integer, ExchangeVariable> getScores() {
        Map<String, Variable<?>> variables = getVariables();
        for (ExchangeVariable score : scores.values()) {
            score.setVariables(variables);
        }

        return scores;
    }

    public void addOutputValue(@Nonnull ExchangeVariable exchangeVariable) {
        getOutputs().put(exchangeVariable.getIndex(), exchangeVariable);
    }

    public void addScoreValue(@Nonnull ExchangeVariable exchangeVariable) {
        getScores().put(exchangeVariable.getIndex(), exchangeVariable);
    }
}
