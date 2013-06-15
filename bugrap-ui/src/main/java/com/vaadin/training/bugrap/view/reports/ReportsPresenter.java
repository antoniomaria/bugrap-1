package com.vaadin.training.bugrap.view.reports;

import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.training.bugrap.domain.entity.*;
import com.vaadin.training.bugrap.domain.repository.ReportQuery;
import com.vaadin.training.bugrap.service.CommentService;
import com.vaadin.training.bugrap.service.ProjectService;
import com.vaadin.training.bugrap.service.ReportService;
import com.vaadin.training.bugrap.util.CurrentUser;
import com.vaadin.training.bugrap.view.login.LogoutEvent;
import com.vaadin.training.bugrap.view.mvp.Presenter;
import com.vaadin.training.bugrap.view.reports.components.ReportPopupWindow;
import com.vaadin.ui.UI;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportsPresenter extends Presenter {

    private User currentUser;

    ReportQuery query = new ReportQuery();

    @Inject
    ReportService reportService;

    @Inject
    ProjectService projectService;

    @Inject
    CommentService commentService;

    @Inject
    Event<LogoutEvent> logoutEvent;

    private Report currentReport;

    @Override
    public void viewEntered(String params) {
        Project project = projectService.findProject();

        currentUser = CurrentUser.get();

        getView().showProject(project);
        getView().showReports(reportService.getReports(query));
        getView().updateUsername(currentUser.getName());
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

        List<Comment> comments = reportService.getComments(report);

        getView().showSelectedReport(report, comments);
    }

    public void reportUpdated() {
        currentReport = reportService.save(currentReport);

        List<Report> reports = reportService.getReports(query);

        List<Comment> comments = reportService.getComments(currentReport);

        getView().showReports(reports);
        getView().selectReport(currentReport);
        getView().showSelectedReport(currentReport, comments);
        getView().hidePopupWindow();
    }

    public void newWindowReportButtonClicked() {
        getView().showReportPopup(currentReport, new ArrayList<Comment>());
    }

    public void reportBugButtonClicked() {
        currentReport = new Report();

        Project project = projectService.findProject();

        currentReport.setTimestamp(new Date());
        currentReport.setPriority(ReportPriority.NORMAL);
        currentReport.setType(ReportType.BUG);
        currentReport.setAssigned(project.getParticipants().iterator().next());
        currentReport.setStatus(ReportStatus.OPEN);
        currentReport.setProjectVersion(project.getProjectVersions().get(project.getProjectVersions().size() - 1));

        getView().showNewReportPopup(currentReport, project);
    }

    public void searchReports(String value) {
        query.setSearchTerm(value);

        List<Report> reports = reportService.getReports(query);

        getView().showReports(reports);
    }

    public void logoutButtonClicked() {
        logoutEvent.fire(new LogoutEvent());
    }

    public void commentAdded(String message) {
        Comment comment = new Comment();

        comment.setTimestamp(new Date());
        comment.setAuthor(currentUser);
        comment.setComment(message);
        comment.setReport(currentReport);
        comment.setType(CommentType.COMMENT);

        comment = commentService.save(comment);

        List<Comment> comments = reportService.getComments(currentReport);

        getView().updateComments(comments);
    }
}
