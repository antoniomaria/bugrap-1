package com.vaadin.training.bugrap.service;

import com.vaadin.training.bugrap.domain.entity.Project;
import com.vaadin.training.bugrap.domain.entity.Report;

import java.util.List;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public interface ReportService {

    public Project findProject();

    public List<Report> getReports(ReportQuery reportQuery);
}
