package com.vaadin.training.bugrap.view.login;

import com.vaadin.training.bugrap.domain.entity.User;
import com.vaadin.training.bugrap.service.UserService;
import com.vaadin.training.bugrap.service.exceptions.LoginException;
import com.vaadin.training.bugrap.view.mvp.Presenter;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public class LoginPresenter extends Presenter {

    @Inject
    Event<LoginEvent> loginEvent;

    @Inject
    UserService userService;

    @Override
    public void viewEntered(String params) {

    }

    public void loginAttempt(String username, String password) {
        try {
            User user = userService.authenticate(username, password);

            loginEvent.fire(new LoginEvent(user));
        } catch (LoginException e) {
            getView().showLoginError("Login failed");
        }
    }

    @Override
    public LoginView getView() {
        return (LoginView) super.getView();
    }
}
