package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.event.ShortcutAction;
import com.vaadin.training.bugrap.domain.entity.Comment;
import com.vaadin.training.bugrap.domain.entity.Project;
import com.vaadin.training.bugrap.domain.entity.Report;
import com.vaadin.training.bugrap.view.reports.ReportsPresenter;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

import java.util.ArrayList;
import java.util.List;

public class ReportPopupWindow extends Window {

    private ReportsPresenter presenter;

    public void setPresenter(ReportsPresenter presenter) {
        this.presenter = presenter;

        reportEditLayout.setPresenter(presenter);
    }

    private final ReportEditLayout reportEditLayout;

    public ReportPopupWindow() {
        setWidth("90%");
        setHeight("90%");
        center();

        addStyleName(Reindeer.WINDOW_LIGHT);
        setModal(true);

        reportEditLayout = new ReportEditLayout();

        setContent(reportEditLayout);

        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);
    }

    public void showReport(Report report, List<Comment> comments) {
        if (report.isPersistent()) {
            setCaption("Edit a report");
        } else {
            setCaption("Create a report");
        }

        reportEditLayout.showReport(report, comments);
        reportEditLayout.hideNewWindowButton();
        reportEditLayout.enableEditableSummary();
    }

    public void showNewReport(Report report, Project project) {
        reportEditLayout.populateDataFromProject(project);

        showReport(report, new ArrayList<Comment>());
    }
}
