package com.vaadin.training.bugrap.service;

import com.vaadin.training.bugrap.domain.Project;
import com.vaadin.training.bugrap.domain.Report;

import java.util.List;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public interface ReportService {

    public Project findProject();

    public List<Report> getReports(ReportQuery reportQuery);
}
