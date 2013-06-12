package com.vaadin.training.bugrap.domain.repository;

import com.vaadin.training.bugrap.domain.entity.Report;

import java.util.List;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ReportRepository extends AbstractRepository<Report> {
    @Override
    protected Class<Report> getEntityClass() {
        return Report.class;
    }

    public List<Report> findReports(ReportQuery reportQuery) {
        return findAll();
    }
}
