package com.vaadin.training.bugrap.view.login;

import com.vaadin.training.bugrap.view.mvp.View;

public interface LoginView extends View {
    public static final String NAME = "login";

    void showLoginError(String message);
}
