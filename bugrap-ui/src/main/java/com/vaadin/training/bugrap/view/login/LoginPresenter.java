package com.vaadin.training.bugrap.view.login;

import com.vaadin.training.bugrap.domain.entity.User;
import com.vaadin.training.bugrap.view.mvp.Presenter;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public class LoginPresenter extends Presenter {

    @Inject
    Event<LoginEvent> loginEvent;

    @Override
    public void viewEntered(String params) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void loginAttempt(String username, String password) {

        //if successful
        loginEvent.fire(new LoginEvent(new User()));

    }
}
