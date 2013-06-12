package com.vaadin.training.bugrap.service;

import com.vaadin.training.bugrap.domain.entity.Project;
import com.vaadin.training.bugrap.domain.entity.Report;
import com.vaadin.training.bugrap.domain.repository.ProjectRepository;
import com.vaadin.training.bugrap.domain.repository.ReportQuery;
import com.vaadin.training.bugrap.domain.repository.ReportRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ReportServiceImpl implements ReportService {

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    ReportRepository reportRepository;

    @Override
    public Project findProject() {
        return projectRepository.findAll().get(0);
    }

    @Override
    public List<Report> getReports(ReportQuery reportQuery) {
        return reportRepository.findReports(reportQuery);
    }
}
