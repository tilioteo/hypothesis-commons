package org.hypothesis.interfaces.variable;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Map;

public interface ExchangeVariable extends Serializable {

    int getIndex();

    Object getValue();

    void setVariables(@Nonnull Map<String, Variable<?>> variables);
}
