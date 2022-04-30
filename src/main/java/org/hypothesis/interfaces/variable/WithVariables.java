package org.hypothesis.interfaces.variable;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Map;

public interface WithVariables extends Serializable {

    @Nonnull
    Map<String, Variable<?>> getVariables();

}
