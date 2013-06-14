package com.vaadin.training.bugrap.view.login;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import javax.inject.Inject;

public class LoginViewImpl extends CssLayout implements LoginView {

    @Inject
    private LoginPresenter presenter;
    private final TextField usernameField;
    private final PasswordField passwordField;

    public LoginViewImpl() {
        setWidth("250px");
        addStyleName("login");

        FormLayout formLayout = new FormLayout();

        usernameField = new TextField("Username");
        formLayout.addComponent(usernameField);

        passwordField = new PasswordField("Password");
        formLayout.addComponent(passwordField);

        Button loginButton = new Button("Login", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                presenter.loginAttempt(usernameField.getValue(), passwordField.getValue());
            }
        });
        formLayout.addComponent(loginButton);

        formLayout.setSpacing(true);
        formLayout.setMargin(true);

        addComponent(formLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        presenter.viewEntered(event.getParameters());
    }
}
