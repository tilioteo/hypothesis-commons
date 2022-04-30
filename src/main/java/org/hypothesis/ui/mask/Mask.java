package org.hypothesis.ui.mask;

import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.AbstractComponent;
import org.hypothesis.ui.client.mask.MaskClientRpc;
import org.hypothesis.ui.client.mask.MaskState;

public class Mask extends AbstractExtension {

    private final MaskClientRpc clientRpc;

    protected Mask(AbstractComponent target) {
        super.extend(target);

        clientRpc = getRpcProxy(MaskClientRpc.class);
    }

    public static Mask addToComponent(AbstractComponent target) {
        return new Mask(target);
    }

    public void show() {
        clientRpc.show();
    }

    public void hide() {
        clientRpc.hide();
    }

    public String getColor() {
        return getState().color;
    }

    public void setColor(String color) {
        getState().color = color;
    }

    /*
     * public double getOpacity() { return getState().opacity; }
     *
     * public void setOpacity(double opacity) { getState().opacity = opacity; }
     */

    @Override
    protected MaskState getState() {
        return (MaskState) super.getState();
    }
}
