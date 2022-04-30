package org.hypothesis.interfaces.builder;

import org.hypothesis.interfaces.action.ActionRegistrar;
import org.hypothesis.interfaces.component.ComponentProvider;
import org.hypothesis.interfaces.component.ComponentRegistrar;
import org.hypothesis.interfaces.evaluable.Evaluator;
import org.hypothesis.interfaces.event.ComponentEventHandler;

import javax.annotation.Nonnull;

public interface BuilderContext {

    static BuilderContext builderContext(@Nonnull Evaluator evaluator,
                                         @Nonnull ActionRegistrar actionRegistrar,
                                         @Nonnull ComponentEventHandler componentEventHandler,
                                         @Nonnull ComponentProvider componentProvider,
                                         @Nonnull ComponentRegistrar componentRegistrar) {
        return new BuilderContext() {
            @Nonnull
            @Override
            public Evaluator evaluator() {
                return evaluator;
            }

            @Nonnull
            @Override
            public ActionRegistrar registrar() {
                return actionRegistrar;
            }

            @Nonnull
            @Override
            public ComponentEventHandler handler() {
                return componentEventHandler;
            }

            @Nonnull
            @Override
            public ComponentProvider components() {
                return componentProvider;
            }

            @Nonnull
            @Override
            public ComponentRegistrar componentRegistrar() {
                return componentRegistrar;
            }
        };
    }

    @Nonnull
    Evaluator evaluator();

    @Nonnull
    ActionRegistrar registrar();

    @Nonnull
    ComponentEventHandler handler();

    @Nonnull
    ComponentProvider components();

    @Nonnull
    ComponentRegistrar componentRegistrar();

}
