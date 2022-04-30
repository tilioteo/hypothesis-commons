package org.hypothesis.interfaces.action;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Map;

public interface HasActions extends Serializable {

    @Nonnull
    Map<String, Action> getActions();

}