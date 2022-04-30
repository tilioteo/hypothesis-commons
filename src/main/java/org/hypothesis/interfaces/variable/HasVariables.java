package org.hypothesis.interfaces.variable;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Map;

public interface HasVariables extends Serializable {

    @Nonnull
    Map<String, Variable<?>> getVariables();

}
