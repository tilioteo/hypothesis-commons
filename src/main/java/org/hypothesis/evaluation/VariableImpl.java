package org.hypothesis.evaluation;

import org.hypothesis.interfaces.variable.Variable;

import javax.annotation.Nonnull;
import java.util.Locale;

class VariableImpl<T> implements Variable<T> {

    private final String name;
    private T value;

    public VariableImpl(String name) {
        this.name = name;
    }

    public VariableImpl(String name, T value) {
        this(name);
        this.value = value;
    }

    @Nonnull
    @Override
    public String getName() {
        return name;
    }

    @Override
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @SuppressWarnings("unchecked")
    public void setRawValue(Object value) {
        try {
            this.value = (T) value;
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /*
     * public void setName(String name) { this.name = name; }
     */

    @Override
    public Class<?> getType() {
        if (value != null) {
            Class<?> type = value.getClass();

            if (type == byte.class || type == int.class || type == short.class
                    || Integer.class.isAssignableFrom(type)) {
                return Integer.class;
            } else if (type == long.class || type == double.class || type == float.class
                    || Double.class.isAssignableFrom(type) || Long.class.isAssignableFrom(type)) {
                return Double.class;
            } else if (type == boolean.class || Boolean.class.isAssignableFrom(type)) {
                return Boolean.class;
            } else if (type == String.class || String.class.isAssignableFrom(type)) {
                return String.class;
            } else {
                return Object.class;
            }
        } else {
            return Object.class;
        }
    }

    @Override
    public String getStringValue() {
        if (value != null) {
            Class<?> type = getType();
            if (type.equals(Integer.class)) {
                return value.toString();
            } else if (type.equals(Double.class)) {
                return String.format(Locale.ROOT, "%g", value);
            } else if (type.equals(Boolean.class)) {
                return value.toString();
            } else if (type.equals(String.class)) {
                return (String) value;
            } else {
                // object variables are not supported yet
                return "";
            }
        } else {
            return "";
        }
    }
}
