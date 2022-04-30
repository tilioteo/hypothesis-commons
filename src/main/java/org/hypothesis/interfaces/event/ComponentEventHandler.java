package org.hypothesis.interfaces.event;

import javax.annotation.Nonnull;

public interface ComponentEventHandler {

    void handle(@Nonnull ComponentEvent event);

}
