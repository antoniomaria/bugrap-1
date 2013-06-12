package com.vaadin.training.bugrap.domain.repository;

import com.vaadin.training.bugrap.domain.entity.Report;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ReportRepository extends AbstractRepository<Report> {
    @Override
    protected Class<Report> getEntityClass() {
        return Report.class;
    }
}
