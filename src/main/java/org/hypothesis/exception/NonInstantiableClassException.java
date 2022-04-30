package org.hypothesis.exception;

import javax.annotation.Nonnull;

public class NonInstantiableClassException extends IllegalStateException {

    public NonInstantiableClassException(@Nonnull Class<?> aClass) {
        super("Class " + aClass.getName() + " cannot be instantiated.");
    }
}
