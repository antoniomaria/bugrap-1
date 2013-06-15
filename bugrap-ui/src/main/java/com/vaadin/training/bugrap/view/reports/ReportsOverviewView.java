package com.vaadin.training.bugrap.view.reports;

import com.vaadin.training.bugrap.domain.entity.Comment;
import com.vaadin.training.bugrap.domain.entity.Project;
import com.vaadin.training.bugrap.domain.entity.Report;
import com.vaadin.training.bugrap.view.mvp.View;

import java.util.List;

public interface ReportsOverviewView extends View {
    public static final String NAME = "reports";

    void showProject(Project project);

    void showReports(List<Report> reports);

    void showSelectedReport(Report report, List<Comment> comments);

    void selectReport(Report report);

    void showReportPopup(Report report, List<Comment> comments);

    void showNewReportPopup(Report currentReport, Project project);

    void hidePopupWindow();

    void updateUsername(String name);

    void updateComments(List<Comment> comments);
}
