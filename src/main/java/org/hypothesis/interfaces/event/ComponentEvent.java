package org.hypothesis.interfaces.event;

import com.vaadin.ui.Component;
import org.hypothesis.interfaces.action.Action;

import java.util.EventObject;
import java.util.function.BiConsumer;

public interface ComponentEvent<E extends EventObject> extends ProcessEvent {

    Component getComponent();

    EventObject getEvent();

    String getTypeName();

    String getEventName();

    Action getAction();

    BiConsumer<E, ComponentEventDataDescriptor> getDescriptor();

}
