package com.vaadin.training.bugrap.service;

import com.vaadin.training.bugrap.domain.entity.Project;
import com.vaadin.training.bugrap.domain.entity.Report;
import com.vaadin.training.bugrap.domain.repository.ProjectRepository;
import com.vaadin.training.bugrap.domain.repository.ReportQuery;
import com.vaadin.training.bugrap.domain.repository.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ReportServiceImpl implements ReportService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    ReportRepository reportRepository;

    @Override
    public Project findProject() {
        logger.debug("Finding projects");
        return projectRepository.findAll().get(0);
    }

    @Override
    public List<Report> getReports(ReportQuery reportQuery) {
        logger.debug("Getting reports");
        return reportRepository.findReports(reportQuery);
    }
}
