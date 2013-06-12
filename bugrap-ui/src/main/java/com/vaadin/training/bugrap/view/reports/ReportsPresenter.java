package com.vaadin.training.bugrap.view.reports;

import com.google.common.collect.Lists;
import com.vaadin.training.bugrap.domain.entity.*;
import com.vaadin.training.bugrap.domain.repository.ReportQuery;
import com.vaadin.training.bugrap.service.ReportService;
import com.vaadin.training.bugrap.view.mvp.Presenter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ReportsPresenter extends Presenter {

    private User currentUser;

    private ReportQuery query = new ReportQuery();

    @Inject
    private ReportService reportService;

    private Report currentReport;

    @Override
    public void viewEntered(String params) {
        Project project = reportService.findProject();

        currentUser = new User();

        getView().showProject(project);
        getView().showReports(reportService.getReports(null));
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
        //TODO: save currentReport

        getView().updateReports();
    }
}
