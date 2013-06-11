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
//        Project project = reportService.findProject();
        Project project = createDummyProject();
        getView().showProject(project);
    }

    private Project createDummyProject() {
        Project project = new Project();
        project.setName("Self-created project");
        project.addProjectVersion("1.2.3-pre1");
        project.addProjectVersion("1.2.3-pre2");
        project.addProjectVersion("1.2.3-pre3");
        return project;
    }

    @Override
    public ReportsOverviewView getView() {
        return (ReportsOverviewView) super.getView();
    }
}
