package com.vaadin.training.bugrap.domain.repository;

import com.vaadin.training.bugrap.domain.entity.Project;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ProjectRepository extends AbstractRepository<Project> {


    @Override
    protected Class<Project> getEntityClass() {
        return Project.class;
    }
}
