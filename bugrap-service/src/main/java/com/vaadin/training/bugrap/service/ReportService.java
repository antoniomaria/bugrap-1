package com.vaadin.training.bugrap.service;

import com.vaadin.training.bugrap.domain.entity.Report;
import com.vaadin.training.bugrap.domain.repository.ReportQuery;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
@Local
public interface ReportService {

    public List<Report> getReports(ReportQuery reportQuery);

    public Report save(Report report);
}
