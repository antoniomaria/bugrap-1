package com.vaadin.training.bugrap.service;

import com.google.common.collect.Lists;
import com.vaadin.training.bugrap.domain.entity.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ReportServiceImpl implements ReportService {


    private Project project;
    private final User author;
    private final User assigned;

    public ReportServiceImpl() {
        assigned = new User();
        assigned.setUsername("assigned");
        assigned.setName("Assigned User");
        assigned.setId(Long.valueOf(1));

        author = new User();
        author.setUsername("author");
        author.setName("Author User");
        author.setId(Long.valueOf(2));

    }

    @Override
    public Project findProject() {
        project = new Project();
        project.setName("Project name that is rather long blah blah blah blah");
        project.setId(Long.valueOf(1234));
        project.addProjectVersion("0.0.1");
        project.addProjectVersion("0.0.2");
        project.addProjectVersion("0.0.3");
        project.addProjectVersion("0.0.4");
        project.addParticipant(assigned);
        project.addParticipant(author);
        return project;
    }

    @Override
    public List<Report> getReports(ReportQuery reportQuery) {

        List<Report> reports = Lists.newLinkedList();
        Random random = new Random();
        for (int i = 1; i <= random.nextInt(50); i++) {
            Report report = new Report();
            report.setId(Long.valueOf(i));
            reports.add(report);
            report.setType(ReportType.BUG);
            report.setStatus(ReportStatus.OPEN);
            report.setSummary("This is bug #" + i);
            report.setDescription("This is a detailed description. It is very long. This is a detailed description. It is very long. This is a detailed description. It is very long. This is a detailed description. It is very long.");
            report.setProjectVersion(project.getProjectVersions().get(0));
            report.setAssigned(assigned);
            report.setAuthor(author);
            report.setTimestamp(new Date());
        }

        return reports;
    }
}
