package com.vaadin.training.bugrap.view.login;

import com.vaadin.training.bugrap.domain.entity.User;

public class LoginEvent {

    private User user;

    public User getUser() {
        return user;
    }

    public LoginEvent(User user) {
        this.user = user;
    }
}
