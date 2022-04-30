package org.hypothesis.interfaces.action;

import org.hypothesis.interfaces.variable.ExchangeVariable;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Map;

public interface Action extends Serializable {

    void execute();

    @Nonnull
    String getId();

    @Nonnull
    Map<Integer, ExchangeVariable> getOutputs();

    @Nonnull
    Map<Integer, ExchangeVariable> getScores();

}
