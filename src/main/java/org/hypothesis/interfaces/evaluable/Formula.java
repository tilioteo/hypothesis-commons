package org.hypothesis.interfaces.evaluable;

import java.io.Serializable;

public interface Formula extends Serializable {

    boolean evaluate(Object input);

}
