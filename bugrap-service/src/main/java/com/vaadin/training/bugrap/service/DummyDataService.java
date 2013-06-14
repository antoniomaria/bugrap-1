package com.vaadin.training.bugrap.service;

import com.vaadin.training.bugrap.domain.entity.*;
import com.vaadin.training.bugrap.domain.repository.ProjectRepository;
import com.vaadin.training.bugrap.domain.repository.ReportRepository;
import com.vaadin.training.bugrap.domain.repository.UserRepository;
import de.svenjacobs.loremipsum.LoremIpsum;

import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.Date;
import java.util.Random;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
@Singleton
public class DummyDataService {

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ReportRepository reportRepository;

    private User author;
    private User assigned;
    private Project project;
    private User testUser;


    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void initDB() {
        if (projectRepository.findAll().isEmpty()) {
            createUsers();
            createProject();
            generateReports();
        }
    }


    private void createUsers() {
        assigned = new User();
        assigned.setUsername("assigned");
        assigned.setName("Assigned User");
        assigned.setPassword("assigned");

        author = new User();
        author.setUsername("author");
        author.setName("Author User");
        author.setPassword("author");

        testUser = new User();
        testUser.setUsername("test");
        testUser.setPassword("test");
        testUser.setName("Test User");

        assigned = userRepository.save(assigned);
        author = userRepository.save(author);
        testUser = userRepository.save(testUser);
    }


    private void createProject() {
        project = new Project();
        project.setName("Vaadin Bugrap");
        project.addProjectVersion("1.0-SNAPSHOT");
        project.addProjectVersion("1.0.0.beta1");
        project.addProjectVersion("1.0.0.beta2");
        project.addProjectVersion("1.0.0.rc");
        project.addProjectVersion("1.0.0");
        project.addParticipant(assigned);
        project.addParticipant(author);
        project.addParticipant(testUser);
        project.setManager(author);

        project = projectRepository.save(project);
    }


    public void generateReports() {
        LoremIpsum loremIpsum = new LoremIpsum();

        Random random = new Random(123);
        for (ProjectVersion version : project.getProjectVersions()) {

            for (int i = 1; i <= random.nextInt(20) + 1; i++) {
                Report report = new Report();
                report.setType(ReportType.values()[random.nextInt(ReportType.values().length)]);
                report.setStatus(ReportStatus.values()[random.nextInt(ReportStatus.values().length)]);
                if (report.getStatus().equals(ReportStatus.CLOSED)) {
                    report.setResolution(ReportResolution.values()[random.nextInt(ReportResolution.values().length)]);
                }
                report.setPriority(ReportPriority.values()[random.nextInt(ReportPriority.values().length)]);
                report.setSummary(loremIpsum.getWords(random.nextInt(15) + 2, random.nextInt(10)));
                report.setDescription(loremIpsum.getParagraphs(4));
                report.setProjectVersion(version);
                report.setAssigned(assigned);
                report.setAuthor(author);
                report.setTimestamp(new Date());
                reportRepository.save(report);
            }
        }
    }
}
