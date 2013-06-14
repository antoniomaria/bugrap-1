package com.vaadin.training.bugrap.client.components.distbar;

import com.vaadin.shared.communication.ServerRpc;

public interface DistributionBarServerRpc extends ServerRpc {

    public void segmentClicked(Segment segment);
}
