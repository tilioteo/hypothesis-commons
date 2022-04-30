package org.hypothesis.interfaces.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;

import javax.annotation.Nonnull;

public interface SlideComponent extends WithComponent {

    @Nonnull
    static SlideComponent slideComponent(@Nonnull Component component, @Nonnull Alignment alignment) {
        return new SlideComponent() {
            @Nonnull
            @Override
            public Component getComponent() {
                return component;
            }

            @Nonnull
            @Override
            public Alignment getAlignment() {
                return alignment;
            }
        };
    }

    @Nonnull
    @Override
    Component getComponent();

    @Nonnull
    Alignment getAlignment();
}
