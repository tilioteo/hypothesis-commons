package org.hypothesis.interfaces.handler;

import org.hypothesis.interfaces.action.Action;
import org.hypothesis.interfaces.dom.Element;

import javax.annotation.Nonnull;

public interface HandlerContext {

    @Nonnull
    static HandlerContext handlerContext(@Nonnull Element element, @Nonnull String name, @Nonnull Action action) {
        return new HandlerContext() {
            @Nonnull
            @Override
            public Element getElement() {
                return element;
            }

            @Nonnull
            @Override
            public String getName() {
                return name;
            }

            @Nonnull
            @Override
            public Action getAction() {
                return action;
            }
        };
    }

    @Nonnull
    Element getElement();

    @Nonnull
    String getName();

    @Nonnull
    Action getAction();
}
