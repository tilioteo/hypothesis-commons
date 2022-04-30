package org.hypothesis.builders;

import org.hypothesis.interfaces.action.Action;
import org.hypothesis.interfaces.action.WithActions;
import org.hypothesis.interfaces.evaluable.Evaluable;
import org.hypothesis.interfaces.variable.Variable;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;

class CallImpl implements Evaluable {

    private final WithActions withActions;
    private final String actionId;

    public CallImpl(WithActions withActions, String actionId) {
        this.withActions = withActions;
        this.actionId = actionId;
    }

    @Override
    public void evaluate() {
        Optional.ofNullable(withActions)
                .map(WithActions::getActions)
                .map(actions -> actions.get(actionId))
                .ifPresent(Action::execute);
    }

    @Override
    public void setVariables(@Nonnull Map<String, Variable<?>> variables) {
        // nop
    }

    @Override
    public void updateVariables(@Nonnull Map<String, Variable<?>> variables) {
        // nop
    }

    @Override
    public String toString() {
        return "->" + actionId + "()";
    }

}
