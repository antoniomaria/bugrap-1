package com.vaadin.training.bugrap.view.reports;

import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.training.bugrap.domain.entity.*;
import com.vaadin.training.bugrap.domain.repository.ReportQuery;
import com.vaadin.training.bugrap.service.ProjectService;
import com.vaadin.training.bugrap.service.ReportService;
import com.vaadin.training.bugrap.view.mvp.Presenter;
import com.vaadin.training.bugrap.view.reports.components.ReportPopupWindow;
import com.vaadin.ui.UI;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportsPresenter extends Presenter {

    private User currentUser;

    private ReportQuery query = new ReportQuery();

    @Inject
    ReportService reportService;

    @Inject
    ProjectService projectService;

    private Report currentReport;

    @Override
    public void viewEntered(String params) {
        Project project = projectService.findProject();

        currentUser = new User();

        getView().showProject(project);
        getView().showReports(reportService.getReports(query));
    }

    public void projectVersionChanged(ProjectVersion version) {
        query.setVersion(version);

        List<Report> reports = reportService.getReports(query);

        getView().showReports(reports);
    }

    public void reportsStatusFilterChanged(ReportStatus status) {
        query.setStatus(status);
        query.setResolutions(new ArrayList<ReportResolution>());

        List<Report> reports = reportService.getReports(query);

        getView().showReports(reports);
    }

    public void reportsCustomFilterChanged(ReportStatus status, List<ReportResolution> resolutions) {
        query.setStatus(status);
        query.setResolutions(resolutions);

        List<Report> reports = reportService.getReports(query);

        getView().showReports(reports);
    }

    @Override
    public ReportsOverviewView getView() {
        return (ReportsOverviewView) super.getView();
    }

    public void reportsCurrentUserFilterSelected() {
        query.setAssignee(currentUser);

        List<Report> reports = reportService.getReports(query);

        getView().showReports(reports);
    }

    public void reportsAllUsersFilterSelected() {
        query.setAssignee(null);

        List<Report> reports = reportService.getReports(query);

        getView().showReports(reports);
    }

    public void reportSelected(Report report) {
        currentReport = report;

        getView().showSelectedReport(report);
    }

    public void reportUpdated() {
        currentReport = reportService.save(currentReport);

        List<Report> reports = reportService.getReports(query);

        getView().showReports(reports);
        getView().selectReport(currentReport);
        getView().showSelectedReport(currentReport);
        getView().hidePopupWindow();
    }

    public void newWindowReportButtonClicked() {
        getView().showReportPopup(currentReport);
    }

    public void reportBugButtonClicked() {
        currentReport = new Report();

        currentReport.setTimestamp(new Date());

        Project project = projectService.findProject();

        getView().showNewReportPopup(currentReport, project);
    }
}
