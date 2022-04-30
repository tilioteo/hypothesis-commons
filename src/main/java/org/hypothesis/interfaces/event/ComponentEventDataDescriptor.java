package org.hypothesis.interfaces.event;

import java.time.LocalDateTime;

public interface ComponentEventDataDescriptor {

    void setProperty(String name, Object value);

    void setProperty(String name, Class<?> clazz, Object value);

    void setProperty(String name, Object value, String pattern);

    void setProperty(String name, Class<?> clazz, Object value, String pattern);

    void setClientTimestamp(LocalDateTime clientTimestamp);

    void setTimestamp(LocalDateTime timestamp);

}
