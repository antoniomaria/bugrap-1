package com.vaadin.training.bugrap.view.reports;

import com.vaadin.training.bugrap.domain.entity.*;
import com.vaadin.training.bugrap.domain.repository.ReportQuery;
import com.vaadin.training.bugrap.service.ReportService;
import com.vaadin.training.bugrap.view.mvp.Presenter;

import javax.inject.Inject;
import java.util.List;

public class ReportsPresenter extends Presenter {

    private Project currentProject;
    private ProjectVersion currentVersion;
    private User currentUser;

    private List<ReportStatus> statuses;
    private List<ReportResolution> resolutions;

    @Inject
    private ReportService reportService;

    @Override
    public void viewEntered(String params) {
        Project project = reportService.findProject();

        currentProject = project;
        currentVersion = new ProjectVersion();
        currentVersion.setVersion("");
        currentVersion.setProject(project);

        currentUser = new User();

        getView().showProject(project);
        getView().showReports(reportService.getReports(null));
    }

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

    public void reportsStatusFilterChanged(List<ReportStatus> statuses) {
        ReportQuery reportQuery = new ReportQuery();
        reportQuery.setStatuses(statuses);
        reportQuery.setVersion(currentVersion);

        this.statuses = statuses;
        this.resolutions = null;

        List<Report> reports = reportService.getReports(reportQuery);

        getView().showReports(reports);
    }

    public void reportsCustomFilterChanged(List<ReportStatus> statuses, List<ReportResolution> resolutions) {
        ReportQuery reportQuery = new ReportQuery();
        reportQuery.setStatuses(statuses);
        reportQuery.setResolutions(resolutions);

        this.statuses = statuses;
        this.resolutions = resolutions;

        List<Report> reports = reportService.getReports(reportQuery);

        getView().showReports(reports);
    }

    @Override
    public ReportsOverviewView getView() {
        return (ReportsOverviewView) super.getView();
    }

    public void reportsCurrentUserFilterSelected() {
        ReportQuery reportQuery = new ReportQuery();
        reportQuery.setAssignee(currentUser);

        List<Report> reports = reportService.getReports(reportQuery);

        getView().showReports(reports);
    }

    public void reportsAllUsersFilterSelected() {
        ReportQuery reportQuery = new ReportQuery();

        List<Report> reports = reportService.getReports(reportQuery);

        getView().showReports(reports);
    }
}
