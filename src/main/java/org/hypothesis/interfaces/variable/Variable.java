package org.hypothesis.interfaces.variable;

import javax.annotation.Nonnull;
import java.io.Serializable;

public interface Variable<T> extends Serializable {

    @Nonnull
    String getName();

    Class<?> getType();

    Object getValue();

    String getStringValue();

    void setRawValue(Object value);

}
