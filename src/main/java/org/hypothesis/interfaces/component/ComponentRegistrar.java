package org.hypothesis.interfaces.component;

import com.vaadin.ui.Component;

import javax.annotation.Nonnull;

public interface ComponentRegistrar {

    @Nonnull
    Component register(@Nonnull String id, @Nonnull Component component);
}
