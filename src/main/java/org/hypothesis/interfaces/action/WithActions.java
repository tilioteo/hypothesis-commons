package org.hypothesis.interfaces.action;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Map;

public interface WithActions extends Serializable {

    @Nonnull
    Map<String, Action> getActions();

}