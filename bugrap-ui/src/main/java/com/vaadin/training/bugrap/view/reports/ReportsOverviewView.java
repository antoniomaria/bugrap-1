package com.vaadin.training.bugrap.view.reports;

import com.vaadin.training.bugrap.domain.Project;
import com.vaadin.training.bugrap.domain.Report;
import com.vaadin.training.bugrap.view.mvp.View;

import java.util.List;

public interface ReportsOverviewView extends View {
    public static final String NAME = "reports";

    void showProject(Project project);

    void showReports(List<Report> reports);
}
