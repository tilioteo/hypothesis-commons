package org.hypothesis.evaluation;

import org.hypothesis.exception.NonInstantiableClassException;
import org.hypothesis.interfaces.variable.Variable;

public class VariableFactory {

    private VariableFactory() {
        throw new NonInstantiableClassException(this.getClass());
    }

    public static Variable<?> createVariable(String name) {
        return createVariable(name, null);
    }

    public static Variable<?> createVariable(String name, Object value) {
        if (value != null) {
            return new VariableImpl<>(name, value);
        }

        return new VariableImpl<>(name);
    }


}
