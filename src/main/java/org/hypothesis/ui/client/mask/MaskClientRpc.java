package org.hypothesis.ui.client.mask;

import com.vaadin.shared.communication.ClientRpc;

public interface MaskClientRpc extends ClientRpc {

    void show();

    void hide();

}
