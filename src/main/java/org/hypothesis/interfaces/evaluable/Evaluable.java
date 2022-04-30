package org.hypothesis.interfaces.evaluable;

import org.hypothesis.interfaces.variable.Variable;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Map;

public interface Evaluable extends Serializable {

    void evaluate();

    void setVariables(@Nonnull Map<String, Variable<?>> variables);

    void updateVariables(@Nonnull Map<String, Variable<?>> variables);
}
