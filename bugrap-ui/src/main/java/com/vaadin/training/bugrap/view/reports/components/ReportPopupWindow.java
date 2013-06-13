package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.training.bugrap.domain.entity.Report;
import com.vaadin.training.bugrap.view.reports.ReportsPresenter;
import com.vaadin.ui.Window;

public class ReportPopupWindow extends Window {

    private ReportsPresenter presenter;

    public void setPresenter(ReportsPresenter presenter) {
        this.presenter = presenter;

        reportEditLayout.setPresenter(presenter);
    }

    private final ReportEditLayout reportEditLayout;

    public ReportPopupWindow() {
        setSizeFull();

        reportEditLayout = new ReportEditLayout();

        setContent(reportEditLayout);
    }

    public void showReport(Report report) {
        reportEditLayout.showReport(report);
        reportEditLayout.hideNewWindowButton();
    }
}
