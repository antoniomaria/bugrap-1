package com.vaadin.training.bugrap;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.training.bugrap.service.DummyDataService;
import com.vaadin.training.bugrap.view.reports.ReportsOverviewView;
import com.vaadin.ui.UI;

import javax.ejb.EJB;
import javax.inject.Inject;

/**
 * The Application's "main" class
 */
@Widgetset("com.vaadin.training.bugrap.BugRapWidgetSet")
@SuppressWarnings("serial")
@CDIUI
@Theme("bugrap")
public class BugRapUI extends UI {

    @EJB
    private DummyDataService dummyDataService;

    @Inject
    private CDIViewProvider cdiViewProvider;

    @Override
    protected void init(VaadinRequest request) {
        dummyDataService.initDB();

        Navigator navigator = new Navigator(this, this);

        navigator.addProvider(cdiViewProvider);

        navigator.navigateTo(ReportsOverviewView.NAME);
    }
}
