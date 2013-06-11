package com.vaadin.training.bugrap;

import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.training.bugrap.view.reports.ReportsOverviewView;
import com.vaadin.training.bugrap.view.reports.ReportsOverviewViewImpl;
import com.vaadin.ui.UI;

import javax.inject.Inject;

/**
 * The Application's "main" class
 */
@Widgetset("com.vaadin.training.bugrap.BugRapWidgetSet")
@SuppressWarnings("serial")
@CDIUI
public class BugRapUI extends UI
{
    @Inject
    private CDIViewProvider cdiViewProvider;

    @Override
    protected void init(VaadinRequest request) {
        Navigator navigator = new Navigator(this, this);

        navigator.addProvider(cdiViewProvider);

        navigator.navigateTo(ReportsOverviewView.NAME);
    }
}
