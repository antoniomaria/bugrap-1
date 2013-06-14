package com.vaadin.training.bugrap.service;

import javax.inject.Inject;

import com.vaadin.training.bugrap.domain.entity.User;
import com.vaadin.training.bugrap.domain.repository.UserRepository;
import com.vaadin.training.bugrap.service.exceptions.InvalidPasswordException;
import com.vaadin.training.bugrap.service.exceptions.NoSuchUserException;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;


    @Override
    public User authenticate(String userName, String password) throws NoSuchUserException, InvalidPasswordException {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new NoSuchUserException();
        }

        if (!user.getPassword().equals(password)) {
            throw new InvalidPasswordException();
        }

        return user;
    }
}
