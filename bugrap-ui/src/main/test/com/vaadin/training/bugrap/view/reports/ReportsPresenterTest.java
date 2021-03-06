package com.vaadin.training.bugrap.view.reports;

import com.vaadin.training.bugrap.domain.entity.Project;
import com.vaadin.training.bugrap.domain.entity.ProjectVersion;
import com.vaadin.training.bugrap.domain.entity.Report;
import com.vaadin.training.bugrap.domain.repository.ReportQuery;
import com.vaadin.training.bugrap.service.ProjectService;
import com.vaadin.training.bugrap.service.ReportService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReportsPresenterTest {

    @Mock
    private ReportsOverviewView view;

    @Mock
    private ReportService reportService;

    @Mock
    private ProjectService projectService;

    private ReportsPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new ReportsPresenter();

        presenter.setView(view);
        presenter.reportService = reportService;
        presenter.projectService = projectService;
        presenter.query = new ReportQuery();
    }

    @Test
    public void testViewEntered() {
        Project project = new Project();
        when(projectService.findProject()).thenReturn(project);
        ArrayList<Report> reports = new ArrayList<Report>();
        when(reportService.getReports(new ReportQuery())).thenReturn(reports);

        presenter.viewEntered("");

        verify(view).showProject(project);
        verify(view).showReports(reports);
    }

    @Test
    public void testProjectVersionChanged() {
        ArrayList<Report> reports = new ArrayList<Report>();
        when(reportService.getReports(presenter.query)).thenReturn(reports);

        ProjectVersion projectVersion = new ProjectVersion();
        String version = "new projectVersion";
        projectVersion.setVersion(version);
        presenter.projectVersionChanged(projectVersion);

        verify(view).showReports(reports);
        assertEquals(presenter.query.getVersion().getVersion(), version);
    }
}
