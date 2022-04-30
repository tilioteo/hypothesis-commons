package org.hypothesis.interfaces.mask;

import com.vaadin.ui.Component;

public interface Maskable extends Component {

    default void mask() {
        mask("#808080");
    }

    default void mask(String color) {
        MaskManager.getInstance().setMaskFor(this, color);
    }

    default void unmask() {
        MaskManager.getInstance().removeMask(this);
    }

}
