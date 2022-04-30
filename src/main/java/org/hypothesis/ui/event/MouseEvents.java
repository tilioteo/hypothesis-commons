package org.hypothesis.ui.event;

import com.vaadin.event.ConnectorEventListener;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.Component;
import com.vaadin.util.ReflectTools;

import java.lang.reflect.Method;

public interface MouseEvents {
    interface DoubleClickListener extends ConnectorEventListener {
        Method doubleClickMethod = ReflectTools.findMethod(DoubleClickListener.class, "doubleClick", DoubleClickEvent.class);

        void doubleClick(DoubleClickEvent doubleClickEvent);
    }

    interface ClickListener extends ConnectorEventListener {
        Method clickMethod = ReflectTools.findMethod(ClickListener.class, "click", ClickEvent.class);

        void click(ClickEvent clickEvent);
    }

    class DoubleClickEvent extends TimekeepingComponentEvent {
        public DoubleClickEvent(long timestamp, Component source) {
            super(timestamp, source);
        }
    }

    class ClickEvent extends TimekeepingComponentEvent {
        private final MouseEventDetails details;

        public ClickEvent(long timestamp, Component source, MouseEventDetails mouseEventDetails) {
            super(timestamp, source);
            this.details = mouseEventDetails;
        }

        public MouseEventDetails.MouseButton getButton() {
            return this.details.getButton();
        }

        public int getClientX() {
            return this.details.getClientX();
        }

        public int getClientY() {
            return this.details.getClientY();
        }

        public int getRelativeX() {
            return this.details.getRelativeX();
        }

        public int getRelativeY() {
            return this.details.getRelativeY();
        }

        public boolean isDoubleClick() {
            return this.details.isDoubleClick();
        }

        public boolean isAltKey() {
            return this.details.isAltKey();
        }

        public boolean isCtrlKey() {
            return this.details.isCtrlKey();
        }

        public boolean isMetaKey() {
            return this.details.isMetaKey();
        }

        public boolean isShiftKey() {
            return this.details.isShiftKey();
        }

        public String getButtonName() {
            return this.details.getButtonName();
        }
    }
}
