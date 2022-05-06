package org.hypothesis.utils;

import org.hypothesis.exception.NonInstantiableClassException;
import org.hypothesis.interfaces.dom.Element;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.hypothesis.interfaces.dom.Constants.*;
import static org.hypothesis.utils.PredicateUtils.alwaysTrue;

public class DomUtils {

    private DomUtils() {
        throw new NonInstantiableClassException(this.getClass());
    }

    private static boolean isNotBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static Stream<Element> filterChildrenStream(@Nonnull Element element, @Nonnull Predicate<Element> predicate) {
        return element.children().stream()
                .filter(predicate);
    }

    public static Stream<Element> filterByChildNameAndSubChildrenPredicateStream(@Nonnull Element element,
                                                                                 @Nonnull String childName,
                                                                                 @Nonnull Predicate<Element> subChildrenPredicate) {
        return Optional.of(childName)
                .filter(DomUtils::isNotBlank)
                .map(element::selectElement)
                .map(child -> filterChildrenStream(child, subChildrenPredicate))
                .orElseGet(Stream::empty);
    }

    public static List<Element> filterChildren(@Nonnull Element element, @Nonnull Predicate<Element> predicate) {
        return filterChildrenStream(element, predicate)
                .collect(toList());
    }

    public static String getId(@Nonnull Element element) {
        return element.getAttribute(ID);
    }

    public static boolean hasId(@Nonnull Element element) {
        return isNotBlank(getId(element));
    }

    public static String getName(@Nonnull Element element) {
        return element.getAttribute(NAME);
    }

    public static String getElementName(@Nonnull Element element) {
        return element.getName();
    }

    public static boolean hasName(@Nonnull Element element) {
        return isNotBlank(getName(element));
    }

    public static boolean hasElementName(@Nonnull Element element) {
        return isNotBlank(element.getName());
    }

    public static String getValue(@Nonnull Element element) {
        return element.getAttribute(VALUE);
    }

    public static boolean hasValue(@Nonnull Element element) {
        return isNotBlank(getValue(element));
    }

    public static String getTrimmedText(@Nonnull Element element) {
        return Optional.ofNullable(element.getText())
                .map(String::trim)
                .orElse(null);
    }

    public static Map<String, String> getPropertyValueMap(@Nonnull Element element) {
        return filterByChildNameAndSubChildrenPredicateStream(element, PROPERTIES, alwaysTrue())
                .collect(toMap(Element::getName, el -> el.getAttribute(VALUE)));
    }

    public static List<Element> filterChildrenByName(@Nonnull Element element, @Nonnull String name) {
        return name.isEmpty() ? emptyList() : filterChildren(element, child -> name.equals(child.getName()));
    }

    public static List<Element> filterChildrenByNameStarting(@Nonnull Element element, @Nonnull String startName) {
        return startName.isEmpty() ? emptyList() : filterChildren(element, child -> child.getName().startsWith(startName));
    }

    public static Element findChildByNameAndAttributeValues(@Nonnull Element element, @Nonnull String name,
                                                            Map<String, String> attributes, boolean descendant) {
        for (Element child : element.children()) {
            if (name.equals(child.getName())) {
                if (attributes != null && !attributes.isEmpty()) {
                    boolean passed = true;
                    Map<String, String> selectedAttributes = child.attributes();
                    for (Map.Entry<String, String> entry : attributes.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        String selectedValue = selectedAttributes.get(key);
                        if (!selectedAttributes.containsKey(key) || (selectedValue == null && value != null)
                                || (!Objects.equals(selectedValue, value))) {
                            passed = false;
                            break;
                        }
                    }
                    if (passed) {
                        return child;
                    }
                } else {
                    return child;
                }
            } else if (descendant) {
                Element found = findChildByNameAndAttributeValues(child, name, attributes, true);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    public static List<Element> filterByChildNameAndSubChildrenPredicate(@Nonnull Element element, @Nonnull String childName, @Nonnull Predicate<Element> subChildrenPredicate) {
        return filterByChildNameAndSubChildrenPredicateStream(element, childName, subChildrenPredicate)
                .collect(toList());
    }

    public static List<Element> selectElements(@Nonnull Element element, String groupName, String subName) {
        return Optional.ofNullable(element.selectElement(groupName)).map(e -> e.selectElements(subName)).orElseGet(Collections::emptyList);
    }
}
