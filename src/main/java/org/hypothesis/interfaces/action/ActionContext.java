package org.hypothesis.interfaces.action;

import org.hypothesis.interfaces.evaluable.Evaluator;

import javax.annotation.Nonnull;

public interface ActionContext {

    @Nonnull
    static ActionContext actionContext(@Nonnull Evaluator evaluator, @Nonnull ActionRegistrar actionRegistrar) {
        return new ActionContext() {
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
        };
    }

    @Nonnull
    Evaluator evaluator();

    @Nonnull
    ActionRegistrar registrar();
}
