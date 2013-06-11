package com.vaadin.training.bugrap.view.reports;

import com.vaadin.training.bugrap.domain.entity.Project;
import com.vaadin.training.bugrap.domain.entity.ProjectVersion;
import com.vaadin.training.bugrap.domain.entity.Report;
import com.vaadin.training.bugrap.domain.entity.ReportStatus;
import com.vaadin.training.bugrap.service.ReportQuery;
import com.vaadin.training.bugrap.service.ReportService;
import com.vaadin.training.bugrap.view.mvp.Presenter;

import javax.inject.Inject;
import java.util.List;

public class ReportsPresenter extends Presenter {

    private Project currentProject;
    private ProjectVersion currentVersion;

    @Inject
    private ReportService reportService;

    @Override
    public void viewEntered(String params) {
        Project project = reportService.findProject();

        currentProject = project;
        currentVersion = new ProjectVersion();
        currentVersion.setVersion("");
        currentVersion.setProject(project);

        getView().showProject(project);
        getView().showReports(reportService.getReports(null));
    }

    @Override
    public void projectVersionChanged(String version) {
        ReportQuery reportQuery = new ReportQuery();
        ProjectVersion projectVersion = new ProjectVersion();
        projectVersion.setProject(currentProject);
        projectVersion.setVersion(version);
        reportQuery.setVersion(projectVersion);

        currentVersion = projectVersion;

        List<Report> reports = reportService.getReports(reportQuery);

        getView().showReports(reports);
    }

    @Override
    public void reportsStatusFilterChanged(List<ReportStatus> statuses) {
        ReportQuery reportQuery = new ReportQuery();
        reportQuery.setStatuses(statuses);
        reportQuery.setVersion(currentVersion);

        List<Report> reports = reportService.getReports(reportQuery);

        getView().showReports(reports);
    }

    @Override
    public ReportsOverviewView getView() {
        return (ReportsOverviewView) super.getView();
    }
}
