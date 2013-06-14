package com.vaadin.training.bugrap.client.components.distbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;
import com.vaadin.training.bugrap.components.distbar.DistributionBar;

@Connect(DistributionBar.class)
public class DistributionBarConnector extends AbstractComponentConnector {


    public DistributionBarConnector() {
        getWidget().setClosedListener(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                getRpcProxy(DistributionBarServerRpc.class).segmentClicked(Segment.CLOSED);
            }
        });

        getWidget().setInProgressListener(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                getRpcProxy(DistributionBarServerRpc.class).segmentClicked(Segment.IN_PROGRESS);
            }
        });

        getWidget().setUnassignedListener(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                getRpcProxy(DistributionBarServerRpc.class).segmentClicked(Segment.UNASSIGNED);
            }
        });
    }

    @Override
    public DistributionBarWidget getWidget() {
        return (DistributionBarWidget) super.getWidget();
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(DistributionBarWidget.class);
    }

    @Override
    public DistributionBarState getState() {
        return (DistributionBarState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        getWidget().setClosed(getState().closed);
        getWidget().setInProgress(getState().inProgress);
        getWidget().setUnassigned(getState().unassigned);
    }
}
