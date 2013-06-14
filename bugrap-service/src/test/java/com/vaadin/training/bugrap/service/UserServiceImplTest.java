package com.vaadin.training.bugrap.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.vaadin.training.bugrap.domain.entity.User;
import com.vaadin.training.bugrap.domain.repository.UserRepository;
import com.vaadin.training.bugrap.service.exceptions.InvalidPasswordException;
import com.vaadin.training.bugrap.service.exceptions.NoSuchUserException;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserRepository userRepository;
    private String username;
    private String password;


    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl();
        userRepository = mock(UserRepository.class);
        userService.userRepository = userRepository;
    }


    @Test
    public void testAuthenticateSuccess() throws Exception {
        User user = getUser();

        when(userRepository.findByUserName(username)).thenReturn(user);

        assertEquals(user, userService.authenticate(username, password));
    }


    @Test(expected = InvalidPasswordException.class)
    public void testIncorrectPassword() throws Exception {
        User user = getUser();
        user.setPassword("wrong");

        when(userRepository.findByUserName(username)).thenReturn(user);

        userService.authenticate(username, password);

    }


    @Test(expected = NoSuchUserException.class)
    public void testNonExistantUser() throws Exception {
        when(userRepository.findByUserName(username)).thenReturn(null);

        userService.authenticate(username, password);
    }


    private User getUser() {
        User user = new User();
        username = "username";
        password = "password";
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

}
