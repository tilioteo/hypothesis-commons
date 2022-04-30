package org.hypothesis.builders;

import org.apache.commons.lang3.StringUtils;
import org.hypothesis.evaluation.*;
import org.hypothesis.interfaces.dom.Element;
import org.hypothesis.interfaces.evaluable.Evaluable;
import org.hypothesis.interfaces.evaluable.Evaluator;
import org.hypothesis.utils.DomUtils;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.hypothesis.builders.EvaluableDomUtils.*;
import static org.hypothesis.builders.ExpressionBuilder.createExpression;
import static org.hypothesis.builders.ExpressionDomConstants.*;
import static org.hypothesis.utils.DomUtils.getValue;

public class EvaluableBuilder {

    private final Evaluator evaluator;

    private EvaluableBuilder(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public static EvaluableBuilder withEvaluator(@Nonnull Evaluator evaluator) {
        return new EvaluableBuilder(evaluator);
    }

    public static IndexedExpression createValueExpression(@Nonnull Element element, @Nonnull String prefix) {
        final String indexString = Optional.of(element)
                .map(Element::getName)
                .map(s -> s.replace(prefix, ""))
                .filter(StringUtils::isNotEmpty)
                .orElse("1");

        return createIndexedExpression(element, indexString);
    }

    public static IndexedExpression createScoreExpression(@Nonnull Element element) {
        String indexString = element.getAttribute(INDEX);

        return createIndexedExpression(element, indexString);
    }

    private static IndexedExpression createIndexedExpression(@Nonnull Element element, String indexString) {
        try {
            int index = Integer.parseInt(indexString);
            return Optional.ofNullable(getExpressionElement(element))
                    .map(ExpressionBuilder::createExpression)
                    .map(e -> new IndexedExpression(index, e))
                    .orElse(null);

        } catch (NumberFormatException e) {
            // log
            e.printStackTrace();
        }

        return null;
    }

    public Evaluable createEvaluable(@Nonnull Element element) {
        switch (element.getName()) {
            case EXPRESSION:
                return createExpression(element);
            case IF:
                return createIfStatement(element);
            case WHILE:
                return createWhileStatement(element);
            case SWITCH:
                return createSwitchStatement(element);
            case CALL:
                return createCall(element);
            default:
                return null;
        }
    }

    private Evaluable createIfStatement(@Nonnull Element element) {
        final Element trueElement = getTrueElement(element);
        final Element falseElement = getFalseElement(element);

        return Optional.ofNullable(getExpressionElement(element))
                .map(ExpressionBuilder::createExpression)
                .map(expression -> {
                    final IfImpl statement = new IfImpl(evaluator, expression);

                    for (int i = 0; i < 2; ++i) {
                        List<Element> elements = i == 0 ? trueElement != null ? trueElement.children() : emptyList()
                                : falseElement != null ? falseElement.children() : emptyList();

                        final int index = i;
                        elements.stream()
                                .map(this::createEvaluable)
                                .filter(Objects::nonNull)
                                .forEach(e -> {
                                    if (index == 0) {
                                        statement.addTrueEvaluable(e);
                                    } else {
                                        statement.addFalseEvaluable(e);
                                    }
                                });
                    }

                    return statement;
                })
                .orElse(null);
    }

    private Evaluable createWhileStatement(@Nonnull Element element) {
        final Element loopElement = getLoopElement(element);

        return Optional.ofNullable(getExpressionElement(element))
                .filter(e -> Objects.nonNull(loopElement))
                .map(ExpressionBuilder::createExpression)
                .map(expression -> {
                    final WhileImpl statement = new WhileImpl(evaluator, expression);

                    loopElement.children().stream()
                            .map(this::createEvaluable)
                            .filter(Objects::nonNull)
                            .forEach(statement::addEvaluable);

                    return statement;
                })
                .orElse(null);
    }

    private Evaluable createSwitchStatement(@Nonnull Element element) {
        final List<Element> caseElements = getCaseElements(element);

        return Optional.ofNullable(getExpressionElement(element))
                .map(ExpressionBuilder::createExpression)
                .map(expression -> {
                    final SwitchImpl statement = new SwitchImpl(evaluator, expression);

                    caseElements.stream()
                            .filter(DomUtils::hasValue)
                            .forEach(e -> {
                                final String value = getValue(e);
                                e.children().stream()
                                        .map(this::createEvaluable)
                                        .filter(Objects::nonNull)
                                        .forEach(evaluable -> statement.addCaseEvaluable(value, evaluable));
                            });

                    return statement;
                })
                .orElse(null);
    }

    private Evaluable createCall(@Nonnull Element element) {
        return Optional.ofNullable(getActionAttribute(element))
                .filter(StringUtils::isNotBlank)
                .map(action -> new CallImpl(evaluator, action))
                .orElse(null);
    }

}
