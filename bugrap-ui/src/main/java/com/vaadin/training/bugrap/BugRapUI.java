package com.vaadin.training.bugrap;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.training.bugrap.domain.entity.User;
import com.vaadin.training.bugrap.service.DummyDataService;
import com.vaadin.training.bugrap.view.login.LoginEvent;
import com.vaadin.training.bugrap.view.login.LoginView;
import com.vaadin.training.bugrap.view.reports.ReportsOverviewView;
import com.vaadin.ui.UI;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
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

    @Inject
    Instance<LoginView> loginViewInstance;

    @Override
    protected void init(VaadinRequest request) {
        dummyDataService.initDB();

        User user = VaadinSession.getCurrent().getAttribute(User.class);

        if(user == null) {
            setContent(loginViewInstance.get());
        } else {
            initNavigation();
        }
    }

    private void initNavigation() {
        Navigator navigator = new Navigator(this, this);

        navigator.addProvider(cdiViewProvider);

        navigator.navigateTo(ReportsOverviewView.NAME);
    }

    protected void loginEventListener(@Observes LoginEvent loginEvent) {
        VaadinSession.getCurrent().setAttribute(User.class, loginEvent.getUser());

        initNavigation();
    }

}
