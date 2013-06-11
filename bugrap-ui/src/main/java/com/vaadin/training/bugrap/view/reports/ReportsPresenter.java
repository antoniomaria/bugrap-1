package com.vaadin.training.bugrap.view.reports;

import com.vaadin.training.bugrap.domain.Project;
import com.vaadin.training.bugrap.service.ReportService;
import com.vaadin.training.bugrap.view.mvp.Presenter;
import com.vaadin.training.bugrap.view.mvp.View;

import javax.inject.Inject;

public class ReportsPresenter extends Presenter {

    @Inject
    private ReportService reportService;

    @Override
    public void viewEntered(String params) {
        Project project = reportService.findProject();
        getView().showProject(project);
    }

    @Override
    public ReportsOverviewView getView() {
        return (ReportsOverviewView) super.getView();
    }
}
