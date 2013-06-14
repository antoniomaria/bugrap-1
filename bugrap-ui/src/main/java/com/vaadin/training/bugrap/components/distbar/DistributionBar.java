package com.vaadin.training.bugrap.components.distbar;

import com.google.common.collect.Sets;
import com.vaadin.training.bugrap.client.components.distbar.DistributionBarServerRpc;
import com.vaadin.training.bugrap.client.components.distbar.DistributionBarState;
import com.vaadin.training.bugrap.client.components.distbar.Segment;
import com.vaadin.ui.AbstractComponent;

import java.util.Set;

public class DistributionBar extends AbstractComponent {

    private Set<SegmentClickListener> listeners = Sets.newHashSet();

    DistributionBarServerRpc rpc = new DistributionBarServerRpc() {
        @Override
        public void segmentClicked(Segment segment) {
            fireSegmentClick(segment);
        }
    };

    private void fireSegmentClick(Segment segment) {
        for (SegmentClickListener listener : listeners) {
            listener.segmentClicked(segment);
        }
    }

    public DistributionBar() {
        registerRpc(rpc);
    }

    @Override
    protected DistributionBarState getState() {
        return (DistributionBarState) super.getState();
    }

    public void setClosed(int closed) {
        getState().closed = closed;
    }

    public void setInProgress(int inProgress) {
        getState().inProgress = inProgress;
    }

    public void setUnassigned(int unassigned) {
        getState().unassigned = unassigned;
    }

    public void addSegmentClickListener(SegmentClickListener listener) {
        listeners.add(listener);
    }

    public void removeSegmentClickListener(SegmentClickListener listener) {
        listeners.remove(listener);
    }

    public interface SegmentClickListener {
        public void segmentClicked(Segment segment);
    }
}
