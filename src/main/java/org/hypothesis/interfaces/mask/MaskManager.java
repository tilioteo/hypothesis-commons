package org.hypothesis.interfaces.mask;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import org.hypothesis.ui.mask.Mask;

import java.util.HashMap;
import java.util.Map;

class MaskManager {

    private static MaskManager instance;

    private final Map<Component, Mask> componentMaskMap = new HashMap<>();

    private MaskManager() {
    }

    public static MaskManager getInstance() {
        if (instance == null) {
            instance = new MaskManager();
        }
        return instance;
    }

    public void setMaskFor(Maskable maskable, String color) {
        if (maskable instanceof AbstractComponent) {
            Mask mask = componentMaskMap.get(maskable);
            if (mask == null) {
                mask = Mask.addToComponent((AbstractComponent) maskable);
                componentMaskMap.put(maskable, mask);
            }
            mask.setColor(color);
            mask.show();
        }
    }

    public void removeMask(Maskable maskable) {
        final Mask mask = componentMaskMap.remove(maskable);
        if (mask != null) {
            mask.hide();
        }
    }
}
