package com.vaadin.training.bugrap.service;

import com.vaadin.training.bugrap.domain.entity.Project;

import javax.ejb.Local;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
@Local
public interface ProjectService {

    public Project findProject();
}
