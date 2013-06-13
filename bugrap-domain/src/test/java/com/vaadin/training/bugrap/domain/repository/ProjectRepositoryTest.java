package com.vaadin.training.bugrap.domain.repository;

import com.vaadin.training.bugrap.domain.entity.Project;
import com.vaadin.training.bugrap.domain.entity.ProjectVersion;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ProjectRepositoryTest extends AbstractRepositoryTest {

    private ProjectRepository projectRepository;


    @Before
    public void setUp() {
        super.setUp();
        projectRepository = new ProjectRepository();
        projectRepository.em = em;
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
}
