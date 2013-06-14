package com.vaadin.training.bugrap.view.login;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

import javax.inject.Inject;

public class LoginViewImpl extends VerticalLayout implements LoginView {

    @Inject
    private LoginPresenter presenter;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        presenter.viewEntered(event.getParameters());
    }
}
