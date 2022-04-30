package org.hypothesis.interfaces.component;

import com.vaadin.ui.Component;

import javax.annotation.Nonnull;

public interface ComponentProvider {
    Component get(@Nonnull String id);

    Component getTimer(@Nonnull String id);

    Component getWindow(@Nonnull String id);

    Component getField(@Nonnull String id);
}
