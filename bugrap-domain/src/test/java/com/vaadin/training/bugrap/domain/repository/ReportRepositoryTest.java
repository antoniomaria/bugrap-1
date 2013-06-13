package com.vaadin.training.bugrap.domain.repository;

import com.vaadin.training.bugrap.domain.entity.Report;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ReportRepositoryTest extends AbstractRepositoryTest {

    private ReportRepository reportRepository;

    @Before
    public void setUp() {
        super.setUp();
        reportRepository = new ReportRepository();
        reportRepository.em = em;
    }

    @Test
    public void testReportSearch() throws Exception {
        Report report = new Report();
        report.setSummary("summary1");
        report.setDescription("description1");

        reportRepository.save(report);

        report = new Report();
        report.setSummary("summary2");
        report.setDescription("description2");

        reportRepository.save(report);


        ReportQuery reportQuery = new ReportQuery();
        assertEquals("Reports were not persisted", 2, reportRepository.findAll().size());


        reportQuery.setSearchTerm("summary");
        assertEquals("Icorrect hitcount", 2, reportRepository.findReports(reportQuery).size());

        reportQuery.setSearchTerm("summary1");
        assertEquals("Icorrect hitcount", 1, reportRepository.findReports(reportQuery).size());
    }
}
