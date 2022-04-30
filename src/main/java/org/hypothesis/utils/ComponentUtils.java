package org.hypothesis.utils;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import org.hypothesis.exception.NonInstantiableClassException;
import org.hypothesis.interfaces.dom.Element;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import static org.hypothesis.interfaces.dom.Constants.*;
import static org.hypothesis.utils.DomUtils.getId;
import static org.hypothesis.utils.StringParserUtils.*;

public class ComponentUtils {

    private ComponentUtils() {
        throw new NonInstantiableClassException(this.getClass());
    }

    public static void setCommonProperties(@Nonnull Component component, @Nonnull Element element, @Nonnull Map<String, String> properties, @Nonnull Consumer<Alignment> alignmentConsumer) {
        // store component id
        if (component instanceof AbstractComponent) {
            setAbstractComponentProperties((AbstractComponent) component, element, properties);
        }

        component.setWidth(parseDimension(properties.get(WIDTH)));
        component.setHeight(parseDimension(properties.get(HEIGHT)));
        component.setVisible(parseBoolean(properties.get(VISIBLE), true));
        component.setEnabled(parseBoolean(properties.get(ENABLED), true));

        setWrappedAlignment(properties, alignmentConsumer);
    }

    private static void setAbstractComponentProperties(@Nonnull AbstractComponent component, @Nonnull Element element, @Nonnull Map<String, String> properties) {
        component.setData(getId(element));
        Optional.ofNullable(properties.get(CAPTION)).ifPresent(component::setCaption);
        Optional.ofNullable(properties.get(STYLE)).ifPresent(component::addStyleName);

    }

    private static void setWrappedAlignment(Map<String, String> properties, Consumer<Alignment> alignmentConsumer) {
        if (alignmentConsumer != null) {
            alignmentConsumer.accept(parseAlignment(properties.get(ALIGNMENT)));
        }
    }

}
