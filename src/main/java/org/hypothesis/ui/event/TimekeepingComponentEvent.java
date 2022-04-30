package org.hypothesis.ui.event;

import com.vaadin.ui.Component;

import java.util.Date;

public abstract class TimekeepingComponentEvent extends Component.Event {
    private final long clientTimestamp;
    private final Date serverTimestamp = new Date();

    protected TimekeepingComponentEvent(long timestamp, Component source) {
        super(source);
        this.clientTimestamp = timestamp;
    }

    public long getTimestamp() {
        return this.clientTimestamp;
    }

    public Date getClientDatetime() {
        return new Date(this.clientTimestamp);
    }

    public long getServerTimestamp() {
        return this.serverTimestamp.getTime();
    }

    public Date getServerDatetime() {
        return this.serverTimestamp;
    }
}
