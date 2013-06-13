package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.training.bugrap.domain.entity.Project;
import com.vaadin.training.bugrap.domain.entity.Report;
import com.vaadin.training.bugrap.view.reports.ReportsPresenter;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class ReportPopupWindow extends Window {

    private ReportsPresenter presenter;

    public void setPresenter(ReportsPresenter presenter) {
        this.presenter = presenter;

        reportEditLayout.setPresenter(presenter);
    }

    private final ReportEditLayout reportEditLayout;

    public ReportPopupWindow(String caption) {
        setWidth("90%");
        setHeight("90%");
        center();

        addStyleName(Reindeer.WINDOW_LIGHT);

        setCaption(caption);

        reportEditLayout = new ReportEditLayout();

        setContent(reportEditLayout);
    }

    public void showReport(Report report) {
        reportEditLayout.showReport(report);
        reportEditLayout.hideNewWindowButton();
    }

    public void showNewReport(Report report, Project project) {
        showReport(report);

        reportEditLayout.populateDataFromProject(project);
    }
}
