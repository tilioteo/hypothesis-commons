package org.hypothesis.utils;

import org.hypothesis.builders.ActionBuilder;
import org.hypothesis.exception.NonInstantiableClassException;
import org.hypothesis.interfaces.action.Action;
import org.hypothesis.interfaces.action.ActionContext;
import org.hypothesis.interfaces.builder.BuilderContext;
import org.hypothesis.interfaces.dom.Element;
import org.hypothesis.interfaces.handler.HandlerContext;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;

import static org.hypothesis.interfaces.dom.Constants.HANDLERS;
import static org.hypothesis.interfaces.handler.HandlerContext.handlerContext;
import static org.hypothesis.utils.DomUtils.filterByChildNameAndSubChildrenPredicate;
import static org.hypothesis.utils.DomUtils.getElementName;

public class HandlerUtils {

    private HandlerUtils() {
        throw new NonInstantiableClassException(this.getClass());
    }

    public static void iterateHandlers(@Nonnull Element element, @Nonnull BuilderContext builderContext, @Nonnull Consumer<HandlerContext> handlerContextConsumer) {
        final ActionContext actionContext = ActionContext.actionContext(builderContext.evaluator(), builderContext.registrar());
        getComponentHandlers(element).stream()
                .filter(DomUtils::hasElementName)
                .forEach(e -> {
                    final Action action = ActionBuilder.withContext(actionContext).createHandlerAction(e);
                    builderContext.registrar().registerAction(action);
                    handlerContextConsumer.accept(handlerContext(e, getElementName(e), action));
                });
    }

    private static List<Element> getComponentHandlers(@Nonnull Element element) {
        return filterByChildNameAndSubChildrenPredicate(element, HANDLERS, t -> true);
    }

}
