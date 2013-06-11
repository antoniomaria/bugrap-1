package com.vaadin.training.bugrap.view.reports;

import com.vaadin.training.bugrap.domain.Project;
import com.vaadin.training.bugrap.domain.ProjectVersion;
import com.vaadin.training.bugrap.domain.Report;
import com.vaadin.training.bugrap.service.ReportQuery;
import com.vaadin.training.bugrap.service.ReportService;
import com.vaadin.training.bugrap.view.mvp.Presenter;
import com.vaadin.training.bugrap.view.mvp.View;

import javax.inject.Inject;
import java.util.List;

public class ReportsPresenter extends Presenter {

    @Inject
    private ReportService reportService;

    @Override
    public void viewEntered(String params) {
        Project project = reportService.findProject();
        getView().showProject(project);
        getView().showReports(reportService.getReports(null));
    }

    @Override
    public void projectVersionChanged(String version) {
        Project project = reportService.findProject();

        ReportQuery reportQuery = new ReportQuery();
        ProjectVersion projectVersion = new ProjectVersion();
        projectVersion.setProject(project);
        projectVersion.setVersion(version);
        reportQuery.setVersion(projectVersion);

        List<Report> reports = reportService.getReports(reportQuery);

        getView().showReports(reports);
    }

    @Override
    public ReportsOverviewView getView() {
        return (ReportsOverviewView) super.getView();
    }
}
