package org.hypothesis.utils;

import org.hypothesis.exception.NonInstantiableClassException;
import org.hypothesis.interfaces.dom.Element;

import javax.annotation.Nonnull;
import java.util.Optional;

import static org.hypothesis.builders.ExpressionDomConstants.EXPRESSION;
import static org.hypothesis.interfaces.dom.Constants.*;

public class DocumentUtils {

    private DocumentUtils() {
        throw new NonInstantiableClassException(this.getClass());
    }

    public static String getUid(@Nonnull Element element) {
        return element.getAttribute(UID);
    }

    public static Element getExpressionElement(@Nonnull Element element) {
        return element.selectElement(EXPRESSION);
    }

    public static String getType(@Nonnull Element element) {
        return element.getAttribute(TYPE);
    }

    public static String getValues(@Nonnull Element element) {
        return element.getAttribute(VALUES);
    }

    public static Element getReferenceSubElement(@Nonnull Element element) {
        return Optional.ofNullable(element.selectElement(REFERENCE)).map(e -> e.firstChild()).orElse(null);
    }

    public static Element getInstanceSubElement(@Nonnull Element element) {
        return Optional.ofNullable(element.selectElement(INSTANCE)).map(e -> e.firstChild()).orElse(null);
    }
}
