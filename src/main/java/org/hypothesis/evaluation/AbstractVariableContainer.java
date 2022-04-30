package org.hypothesis.evaluation;


import org.hypothesis.interfaces.variable.HasVariables;
import org.hypothesis.interfaces.variable.Variable;

import javax.annotation.Nonnull;
import java.util.Map;

import static java.util.Collections.emptyMap;

public abstract class AbstractVariableContainer implements HasVariables {

    private HasVariables hasVariables;

    protected AbstractVariableContainer(HasVariables hasVariables) {
        this.hasVariables = hasVariables;
    }

    @Nonnull
    @Override
    public Map<String, Variable<?>> getVariables() {
        return hasVariables != null ? hasVariables.getVariables() : emptyMap();
    }

    protected void setHasVariables(HasVariables hasVariables) {
        this.hasVariables = hasVariables;
    }
}
