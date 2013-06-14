package com.vaadin.training.bugrap.service;

import com.vaadin.training.bugrap.domain.entity.User;
import com.vaadin.training.bugrap.domain.repository.UserRepository;
import com.vaadin.training.bugrap.service.exceptions.LoginException;

import javax.inject.Inject;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;


    @Override
    public User authenticate(String userName, String password) throws LoginException {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new LoginException();
        }

        if (!user.getPassword().equals(password)) {
            throw new LoginException();
        }

        return user;
    }
}
