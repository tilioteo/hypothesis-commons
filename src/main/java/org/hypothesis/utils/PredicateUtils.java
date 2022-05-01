package org.hypothesis.utils;

import org.hypothesis.exception.NonInstantiableClassException;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public class PredicateUtils {

    private PredicateUtils() {
        throw new NonInstantiableClassException(this.getClass());
    }

    public static <T> Predicate<T> alwaysTrue() {
        return t -> true;
    }

    public static <T> Predicate<T> alwaysFalse() {
        return t -> false;
    }

    public static <T> Predicate<T> not(@Nonnull Predicate<T> predicate) {
        return predicate.negate();
    }

}
