package org.hypothesis.utils;

import org.hypothesis.exception.NonInstantiableClassException;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamApiUtils {

    private StreamApiUtils() {
        throw new NonInstantiableClassException(this.getClass());
    }

    public static <A, B> Collector<A, ?, B> foldLeft(final B init, final BiFunction<? super B, ? super A, ? extends B> f) {
        return Collectors.collectingAndThen(
                Collectors.reducing(Function.<B>identity(), a -> b -> f.apply(b, a), Function::andThen),
                endOp -> endOp.apply(init)
        );
    }
}
