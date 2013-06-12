package com.vaadin.training.bugrap.domain.repository;

import com.vaadin.training.bugrap.domain.entity.Project;
import com.vaadin.training.bugrap.domain.entity.ProjectVersion;
import org.junit.After;
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
    private EntityManager em;

    @Before
    public void setUp() {
        projectRepository = new ProjectRepository();
        em = Persistence.createEntityManagerFactory("test").createEntityManager();
        projectRepository.em = em;
        em.getTransaction().begin();
    }

    @Test
    public void testAddProject() {
        assertEquals(0, projectRepository.findAll().size());

        Project project = new Project();
        projectRepository.save(project);

        assertTrue(project.isPersistent());

        assertEquals(1, projectRepository.findAll().size());

    }

    @Test
    public void testFindProject() {
        Project project = new Project();
        Project saved = projectRepository.save(project);

        Project found = projectRepository.find(saved.getId());

        assertEquals(saved, found);
    }

    @Test
    public void testProjectVersionCascade() {
        Project project = new Project();
        ProjectVersion version = project.addProjectVersion("1.0");

        projectRepository.save(project);

        assertNotNull(version.getId());
    }

    @After
    public void tearDown() {
        if (em != null) {
            em.getTransaction().rollback();
        }
    }


}
