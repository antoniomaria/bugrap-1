package com.vaadin.training.bugrap.domain.repository;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public abstract class AbstractRepositoryTest {

    EntityManager em;

    @Before
    public void setUp() {
        em = Persistence.createEntityManagerFactory("test").createEntityManager();
        em.getTransaction().begin();
    }

    @After
    public void tearDown() {
        if (em != null) {
            em.getTransaction().rollback();
        }
    }
}
