package com.vaadin.training.bugrap.domain.repository;

import com.vaadin.training.bugrap.domain.entity.Project;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static org.junit.Assert.*;


/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ProjectRepositoryTest {

    private ProjectRepository projectRepository;

    @Before
    public void setUp(){
        projectRepository = new ProjectRepository();
        EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();
        projectRepository.em = em;
    }

    @Test
    public void testAddProject() {
        assertEquals(0, projectRepository.findAll().size());

        new Project();

    }


}
