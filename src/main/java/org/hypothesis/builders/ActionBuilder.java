package org.hypothesis.builders;

import org.hypothesis.interfaces.action.Action;
import org.hypothesis.interfaces.action.ActionContext;
import org.hypothesis.interfaces.dom.Element;

import javax.annotation.Nonnull;
import java.util.Objects;

import static java.util.UUID.randomUUID;
import static org.hypothesis.builders.ActionDomConstants.ACTION;
import static org.hypothesis.builders.EvaluableDomUtils.getActionsElements;
import static org.hypothesis.builders.EvaluableDomUtils.getScoresElements;
import static org.hypothesis.builders.EvaluableBuilder.createValueExpression;
import static org.hypothesis.builders.ExpressionDomConstants.OUTPUT_VALUE;
import static org.hypothesis.utils.DomUtils.*;

public class ActionBuilder {

    private final ActionContext context;

    private ActionBuilder(@Nonnull ActionContext context) {
        this.context = context;
    }

    @Nonnull
    public static ActionBuilder withContext(@Nonnull ActionContext context) {
        return new ActionBuilder(context);
    }

    private static boolean isValidElement(@Nonnull Element element) {
        return hasId(element) && ACTION.equals(element.getName());
    }

    private static void createActionOutputValues(@Nonnull ActionImpl action, @Nonnull Element element) {
        filterChildrenByNameStarting(element, OUTPUT_VALUE).stream()
                .map(e -> createValueExpression(e, OUTPUT_VALUE))
                .filter(Objects::nonNull)
                .forEach(action::addOutputValue);
    }

    private static void createActionScores(@Nonnull ActionImpl action, @Nonnull Element element) {
        getScoresElements(element).stream()
                .map(EvaluableBuilder::createScoreExpression)
                .filter(Objects::nonNull)
                .forEach(action::addScoreValue);
    }

    public void createActions(@Nonnull Element element) {
        getActionsElements(element).stream()
                .filter(ActionBuilder::isValidElement)
                .forEach(this::createAction);
    }

    private void createAction(@Nonnull Element element) {
        context.registrar().registerAction(createInnerAction(element, getId(element)));
    }

    @Nonnull
    private Action createInnerAction(@Nonnull Element element, @Nonnull String id) {
        final ActionImpl action = new ActionImpl(context.evaluator(), id);
        element.children().stream()
                .map(e -> EvaluableBuilder.withEvaluator(context.evaluator()).createEvaluable(e))
                .filter(Objects::nonNull)
                .forEach(action::addEvaluable);

        createActionOutputValues(action, element);
        createActionScores(action, element);

        return action;
    }

    public Action createHandlerAction(@Nonnull Element element) {
        String id = randomUUID().toString();
        return createInnerAction(element, id);
    }


}
