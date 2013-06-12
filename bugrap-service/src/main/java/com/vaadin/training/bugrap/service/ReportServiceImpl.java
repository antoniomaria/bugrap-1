package com.vaadin.training.bugrap.service;

import com.google.common.collect.Lists;
import com.vaadin.training.bugrap.domain.entity.Project;
import com.vaadin.training.bugrap.domain.entity.Report;
import com.vaadin.training.bugrap.domain.repository.ProjectRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ReportServiceImpl implements ReportService {

    @Inject
    private ProjectRepository repository;

    @Override
    public Project findProject() {
        return repository.findAll().get(0);
    }

    @Override
    public List<Report> getReports(ReportQuery reportQuery) {
        return Lists.newArrayList();
    }
}
