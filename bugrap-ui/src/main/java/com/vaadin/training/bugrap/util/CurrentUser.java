package com.vaadin.training.bugrap.util;

import com.vaadin.server.VaadinSession;
import com.vaadin.training.bugrap.domain.entity.User;

public class CurrentUser {
    public static User get() {
        return VaadinSession.getCurrent().getAttribute(User.class);
    }
}
