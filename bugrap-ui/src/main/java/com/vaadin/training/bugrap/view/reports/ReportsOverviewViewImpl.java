package com.vaadin.training.bugrap.view.reports;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.training.bugrap.domain.Project;
import com.vaadin.training.bugrap.domain.Report;
import com.vaadin.training.bugrap.view.reports.components.*;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@CDIView(ReportsOverviewView.NAME)
public class ReportsOverviewViewImpl extends VerticalLayout implements ReportsOverviewView {

    @Inject
    private ReportsPresenter reportsPresenter;

    private final HeaderLayout headerLayout;
    private final StatusReportLayout statusReportLayout;
    private final FiltersLayout filtersLayout;
    private final ReportsTable reportsTable;

    public ReportsOverviewViewImpl() {
        setSizeFull();

        headerLayout = new HeaderLayout();
        addComponent(headerLayout);
        addComponent(new ManageButtonsLayout());

        VerticalLayout statusAndFiltersLayout = new VerticalLayout();
        statusAndFiltersLayout.addComponent(new Label("<hr/>", ContentMode.HTML));

        statusReportLayout = new StatusReportLayout();
        statusAndFiltersLayout.addComponent(statusReportLayout);

        filtersLayout = new FiltersLayout();
        statusAndFiltersLayout.addComponent(filtersLayout);

        statusAndFiltersLayout.setSpacing(true);
        addComponent(statusAndFiltersLayout);

        reportsTable = new ReportsTable();
        addComponent(reportsTable);
        setExpandRatio(reportsTable, 1.0f);

        setSpacing(true);
    }

    @PostConstruct
    public void init() {
        reportsPresenter.setView(this);
        statusReportLayout.setPresenter(reportsPresenter);
        filtersLayout.setPresenter(reportsPresenter);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        reportsPresenter.viewEntered(event.getParameters());
    }

    @Override
    public void showProject(Project project) {
        headerLayout.updateProjectName(project.getName());
        statusReportLayout.updateProjectVersions(project.getProjectVersions());
    }

    @Override
    public void showReports(List<Report> reports) {
        reportsTable.showReports(reports);
    }
}
