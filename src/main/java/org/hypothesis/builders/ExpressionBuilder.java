package org.hypothesis.builders;

import org.hypothesis.evaluation.ExpressionImpl;
import org.hypothesis.exception.NonInstantiableClassException;
import org.hypothesis.interfaces.dom.Element;
import org.hypothesis.interfaces.expression.Exchange;

import javax.annotation.Nonnull;
import java.util.Objects;

import static com.tilioteo.expressions.ExpressionFactory.parseString;
import static org.hypothesis.builders.EvaluableDomUtils.*;
import static org.hypothesis.builders.EvaluableBuilder.createValueExpression;
import static org.hypothesis.builders.ExpressionDomConstants.*;
import static org.hypothesis.utils.DomUtils.getTrimmedText;

public class ExpressionBuilder {

    private ExpressionBuilder() {
        throw new NonInstantiableClassException(this.getClass());
    }

    public static ExpressionImpl createExpression(@Nonnull Element element) {
        if (EXPRESSION.equals(element.getName())) {
            return new ExpressionImpl(parseString(getTrimmedText(element)));
        }

        return null;
    }

    public static void createInputExpressions(@Nonnull Element element, @Nonnull Exchange exchange) {
        getInputValueElements(element).stream()
                .map(e -> createValueExpression(e, INPUT_VALUE))
                .filter(Objects::nonNull)
                .forEach(exchange::addInput);
    }

    public static void createOutputExpressions(@Nonnull Element element, @Nonnull Exchange exchange) {
        getOutputValueElements(element).stream()
                .map(e -> createValueExpression(e, OUTPUT_VALUE))
                .filter(Objects::nonNull)
                .forEach(exchange::addOutput);
    }

    public static void createScoreExpressions(@Nonnull Element element, @Nonnull Exchange exchange) {
        getScoresElements(element).stream()
                .map(EvaluableBuilder::createScoreExpression)
                .filter(Objects::nonNull)
                .forEach(exchange::addScore);
    }

}
