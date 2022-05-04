package org.hypothesis.builders;

import com.tilioteo.common.event.MouseEvents.ClickEvent;
import com.tilioteo.common.event.TimekeepingComponentEvent;
import org.hypothesis.interfaces.event.ComponentEventDataDescriptor;

import java.util.function.BiConsumer;

import static org.hypothesis.utils.DateTimeUtils.toLocalDateTime;

public interface ComponentEventConstants {

    BiConsumer<? extends TimekeepingComponentEvent, ComponentEventDataDescriptor> EMPTY_COMPONENT_EVENT_DATA = (e, ce) -> {
    };

    BiConsumer<? extends TimekeepingComponentEvent, ComponentEventDataDescriptor> DEFAULT_COMPONENT_EVENT_DATA = (e, ce) -> {
        ce.setClientTimestamp(toLocalDateTime(e.getClientDatetime()));
        ce.setTimestamp(toLocalDateTime(e.getServerDatetime()));
    };

    BiConsumer<? extends ClickEvent, ComponentEventDataDescriptor> DEFAULT_MOUSE_CLICK_DATA =
            (BiConsumer<? extends ClickEvent, ComponentEventDataDescriptor>) DEFAULT_COMPONENT_EVENT_DATA.andThen((e, ce) -> {
                ce.setProperty("x", ((ClickEvent) e).getRelativeX());
                ce.setProperty("y", ((ClickEvent) e).getRelativeY());
            });
}
