package com.vaadin.training.bugrap.view.login;

import com.vaadin.training.bugrap.domain.entity.User;
import com.vaadin.training.bugrap.service.UserService;
import com.vaadin.training.bugrap.service.exceptions.InvalidPasswordException;
import com.vaadin.training.bugrap.service.exceptions.NoSuchUserException;
import com.vaadin.training.bugrap.view.mvp.Presenter;
import com.vaadin.training.bugrap.view.mvp.View;
import com.vaadin.ui.Notification;

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
        } catch (NoSuchUserException e) {
            getView().showLoginError("No such user: " + username);
        } catch (InvalidPasswordException e) {
            getView().showLoginError("Invalid password");
        }
    }

    @Override
    public LoginView getView() {
        return (LoginView) super.getView();
    }
}
