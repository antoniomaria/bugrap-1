package com.vaadin.training.bugrap.service;

import com.vaadin.training.bugrap.domain.entity.Project;
import com.vaadin.training.bugrap.domain.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ProjectServiceImpl implements ProjectService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private ProjectRepository projectRepository;

    @Override
    public Project findProject() {
        logger.debug("Finding projects");
        return projectRepository.findAll().get(0);
    }

}
