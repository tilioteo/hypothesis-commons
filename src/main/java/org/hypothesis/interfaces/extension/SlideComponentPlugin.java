package org.hypothesis.interfaces.extension;

import org.hypothesis.interfaces.builder.BuilderContext;
import org.hypothesis.interfaces.component.SlideComponent;
import org.hypothesis.interfaces.dom.Element;

import java.util.Map;
import java.util.Set;

public interface SlideComponentPlugin extends Plugin {

    String getNamespace();

    Set<String> getElements();

    Set<String> getEventTypes();

    Map<String, Set<ValidParentGroup>> getElementParentGroups();

    SlideComponent createComponentFromElement(Element element, BuilderContext context);

    enum ValidParentGroup {
        VIEWPORT, PANEL, CONTAINER
    }
}
