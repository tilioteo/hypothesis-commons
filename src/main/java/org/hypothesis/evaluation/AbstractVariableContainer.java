package org.hypothesis.evaluation;


import org.hypothesis.interfaces.variable.Variable;
import org.hypothesis.interfaces.variable.WithVariables;

import javax.annotation.Nonnull;
import java.util.Map;

import static java.util.Collections.emptyMap;

public abstract class AbstractVariableContainer implements WithVariables {

    private WithVariables withVariables;

    protected AbstractVariableContainer(WithVariables withVariables) {
        this.withVariables = withVariables;
    }

    @Nonnull
    @Override
    public Map<String, Variable<?>> getVariables() {
        return withVariables != null ? withVariables.getVariables() : emptyMap();
    }

    protected void setHasVariables(WithVariables withVariables) {
        this.withVariables = withVariables;
    }
}
