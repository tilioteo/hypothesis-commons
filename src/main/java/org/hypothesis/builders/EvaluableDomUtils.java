package org.hypothesis.builders;

import org.hypothesis.interfaces.dom.Element;
import org.hypothesis.utils.DocumentUtils;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hypothesis.builders.ActionDomConstants.ACTION;
import static org.hypothesis.builders.ActionDomConstants.ACTIONS;
import static org.hypothesis.builders.ExpressionDomConstants.*;
import static org.hypothesis.utils.DomUtils.filterChildrenByNameStarting;

class EvaluableDomUtils {

    public static List<Element> getInputValueElements(@Nonnull Element element) {
        return filterChildrenByNameStarting(element, INPUT_VALUE);
    }

    public static List<Element> getOutputValueElements(@Nonnull Element element) {
        return filterChildrenByNameStarting(element, OUTPUT_VALUE);
    }

    public static List<Element> getScoresElements(@Nonnull Element root) {
        return Optional.ofNullable(root.selectElement(SCORES)).map(e -> e.selectElements(SCORE)).orElseGet(Collections::emptyList);
    }

    public static List<Element> getActionsElements(@Nonnull Element element) {
        return Optional.ofNullable(element.selectElement(ACTIONS)).map(e -> e.selectElements(ACTION)).orElseGet(Collections::emptyList);
    }

    public static Element getExpressionElement(@Nonnull Element element) {
        return DocumentUtils.getExpressionElement(element);
    }

    public static Element getTrueElement(@Nonnull Element element) {
        return element.selectElement(TRUE);
    }

    public static Element getFalseElement(@Nonnull Element element) {
        return element.selectElement(FALSE);
    }

    public static Element getLoopElement(@Nonnull Element element) {
        return element.selectElement(LOOP);
    }

    public static List<Element> getCaseElements(@Nonnull Element element) {
        return element.selectElements(CASE);
    }

    public static String getActionAttribute(@Nonnull Element element) {
        return element.getAttribute(ACTION);
    }


}
