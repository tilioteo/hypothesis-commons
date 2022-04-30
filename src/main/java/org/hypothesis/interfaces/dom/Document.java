package org.hypothesis.interfaces.dom;

import java.io.Serializable;

public interface Document extends Serializable {

    String NAMESPACE_SEPARATOR = ".";

    Element root();

    Element createRoot(String name);

    Element createRoot(Element element);

}
