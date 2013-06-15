package com.vaadin.training.bugrap.service;

import com.vaadin.training.bugrap.domain.entity.Comment;
import com.vaadin.training.bugrap.domain.entity.Report;
import com.vaadin.training.bugrap.domain.repository.ReportQuery;
import com.vaadin.training.bugrap.domain.repository.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
@Stateless
public class ReportServiceImpl implements ReportService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    ReportRepository reportRepository;


    @Override
    public List<Report> getReports(ReportQuery reportQuery) {
        logger.debug("Getting reports");
        return reportRepository.findReports(reportQuery);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Report save(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public List<Comment> getComments(Report report) {
        return reportRepository.findComments(report);
    }
}
