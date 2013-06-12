package com.vaadin.training.bugrap.service;

import com.vaadin.training.bugrap.domain.entity.Report;
import com.vaadin.training.bugrap.domain.repository.ReportQuery;

import java.util.List;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public interface ReportService {

    public List<Report> getReports(ReportQuery reportQuery);
}
