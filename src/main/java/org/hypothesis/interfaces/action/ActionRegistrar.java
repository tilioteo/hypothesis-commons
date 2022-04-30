package org.hypothesis.interfaces.action;

import javax.annotation.Nonnull;

public interface ActionRegistrar {

    @Nonnull
    Action registerAction(@Nonnull Action action);

}
