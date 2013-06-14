package com.vaadin.training.bugrap.view.reports;

import com.vaadin.training.bugrap.domain.entity.Project;
import com.vaadin.training.bugrap.domain.entity.Report;
import com.vaadin.training.bugrap.domain.repository.ReportQuery;
import com.vaadin.training.bugrap.service.ProjectService;
import com.vaadin.training.bugrap.service.ReportService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ReportsPresenterViewIntegrationTest {

    private ReportService reportService;
    private ProjectService projectService;
    private ReportsPresenter reportsPresenter;

    @Before
    public void setUp() throws Exception {
        reportsPresenter = new ReportsPresenter();
        ReportsOverviewViewImpl view = new ReportsOverviewViewImpl();
        view.reportsPresenter = reportsPresenter;
        reportsPresenter.setView(view);

        reportService = mock(ReportService.class);
        projectService = mock(ProjectService.class);

        reportsPresenter.projectService = projectService;
        reportsPresenter.reportService = reportService;
    }

    @Test
    public void verifyReportsOnlyFetchedOnce() throws Exception {
        Project project = new Project();
        project.addProjectVersion("one");
        project.addProjectVersion("two");
        when(projectService.findProject()).thenReturn(project);
        when(reportService.getReports(any(ReportQuery.class))).thenReturn(new ArrayList<Report>());
        verifyNoMoreInteractions(reportService);

        reportsPresenter.viewEntered("");
    }
}
