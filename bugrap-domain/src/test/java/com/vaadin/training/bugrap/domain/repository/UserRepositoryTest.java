package com.vaadin.training.bugrap.domain.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.vaadin.training.bugrap.domain.entity.User;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class UserRepositoryTest extends AbstractRepositoryTest {

    private UserRepository repository;


    @Before
    public void setUp() {
        super.setUp();
        repository = new UserRepository();
        repository.em = em;
    }


    @Test
    public void testFindByUserName() throws Exception {
        String testuser = "testuser";
        User user = new User();
        user.setUsername(testuser);
        user = repository.save(user);

        User foundUser = repository.findByUserName(testuser);

        assertEquals(user, foundUser);
    }


    @Test
    public void testFailedUsernameFind() {
        String testuser = "testuser";
        User user = new User();
        user.setUsername(testuser);
        repository.save(user);

        User foundUser = repository.findByUserName("doesnotexist");

        assertNull(foundUser);
    }
}
