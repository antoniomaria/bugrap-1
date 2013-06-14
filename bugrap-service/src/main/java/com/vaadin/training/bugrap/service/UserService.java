package com.vaadin.training.bugrap.service;

import com.vaadin.training.bugrap.domain.entity.User;
import com.vaadin.training.bugrap.service.exceptions.LoginException;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public interface UserService {

    User authenticate(String userName, String password) throws LoginException;
}
