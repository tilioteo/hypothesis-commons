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
            Class<?> type = value.getClass();

            if (Integer.class.isAssignableFrom(type)) {
                return new VariableImpl<>(name, (Integer) value);
            } else if (Long.class.isAssignableFrom(type)) {
                return new VariableImpl<>(name, ((Long) value).doubleValue());
            } else if (Double.class.isAssignableFrom(type)) {
                return new VariableImpl<>(name, (Double) value);
            } else if (Boolean.class.isAssignableFrom(type)) {
                return new VariableImpl<>(name, (Boolean) value);
            } else if (type == String.class || String.class.isAssignableFrom(type)) {
                return new VariableImpl<>(name, (String) value);
            }
        }

        return new VariableImpl<>(name);
    }


}
