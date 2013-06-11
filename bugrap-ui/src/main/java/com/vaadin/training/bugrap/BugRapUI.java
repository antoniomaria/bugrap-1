package com.vaadin.training.bugrap;

import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.training.bugrap.view.reports.ReportsOverviewView;
import com.vaadin.ui.UI;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@CDIUI
public class BugRapUI extends UI
{

    @Override
    protected void init(VaadinRequest request) {
        setContent(new ReportsOverviewView());
    }
}
