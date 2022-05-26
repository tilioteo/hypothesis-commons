package org.hypothesis.builders;

import com.vaadin.ui.Component;
import org.hypothesis.interfaces.action.Action;
import org.hypothesis.interfaces.event.ComponentEvent;
import org.hypothesis.interfaces.event.ComponentEventDataDescriptor;

import javax.annotation.Nonnull;
import java.util.EventObject;
import java.util.function.BiConsumer;

public class ComponentEventBuilder {

    private final Component component;

    private final String typeName;

    private final Action action;

    private BiConsumer<? extends EventObject, ComponentEventDataDescriptor> descriptor;

    public ComponentEventBuilder(@Nonnull Component component, @Nonnull String typeName, @Nonnull Action action) {
        this.component = component;
        this.typeName = typeName;
        this.action = action;
        this.descriptor = (e, ce) -> {
        };
    }

    public ComponentEventBuilder(@Nonnull Component component, @Nonnull String typeName, @Nonnull Action action, @Nonnull BiConsumer<? extends EventObject, ComponentEventDataDescriptor> descriptor) {
        this.component = component;
        this.typeName = typeName;
        this.action = action;
        this.descriptor = descriptor;
    }

    public ComponentEventBuilder(@Nonnull Component component, @Nonnull String typeName, @Nonnull BiConsumer<? extends EventObject, ComponentEventDataDescriptor> descriptor) {
        this.component = component;
        this.typeName = typeName;
        this.action = null;
        this.descriptor = descriptor;
    }

    public ComponentEvent buildWith(@Nonnull EventObject eventObject, @Nonnull String eventName) {
        return new ComponentEventImpl(this, eventObject, eventName);
    }

    public ComponentEvent buildWith(@Nonnull EventObject eventObject, @Nonnull String eventName, @Nonnull BiConsumer<? extends EventObject, ComponentEventDataDescriptor> descriptor) {
        return new ComponentEventImpl(this, eventObject, eventName, descriptor);
    }

    static class ComponentEventImpl implements ComponentEvent {

        private final Component component;

        private final EventObject event;

        private final String typeName;

        private final String eventName;

        private final Action action;

        private final BiConsumer<? extends EventObject, ComponentEventDataDescriptor> descriptor;

        private ComponentEventImpl(ComponentEventBuilder builder, EventObject event, String eventName) {
            this.component = builder.component;
            this.event = event;
            this.typeName = builder.typeName;
            this.eventName = eventName;
            this.action = builder.action;
            this.descriptor = builder.descriptor;
        }

        public ComponentEventImpl(ComponentEventBuilder builder, EventObject event, String eventName, BiConsumer<? extends EventObject, ComponentEventDataDescriptor> descriptor) {
            this.component = builder.component;
            this.event = event;
            this.typeName = builder.typeName;
            this.eventName = eventName;
            this.action = builder.action;
            this.descriptor = descriptor;
        }

        @Override
        public Component getComponent() {
            return component;
        }

        @Override
        public EventObject getEvent() {
            return event;
        }

        @Override
        public String getTypeName() {
            return typeName;
        }

        @Override
        public String getEventName() {
            return eventName;
        }

        @Override
        public Action getAction() {
            return action;
        }

        @Override
        public BiConsumer<? extends EventObject, ComponentEventDataDescriptor> getDescriptor() {
            return descriptor;
        }
    }
}
