package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.training.bugrap.domain.ReportStatus;
import com.vaadin.training.bugrap.view.mvp.Presenter;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

import java.util.ArrayList;
import java.util.List;

public class FiltersLayout extends HorizontalLayout {

    private Presenter presenter;

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public FiltersLayout() {
        Label assigneesLabel = new Label("Assignees");
        addComponent(assigneesLabel);
        setComponentAlignment(assigneesLabel, Alignment.MIDDLE_CENTER);

        addComponent(new Button("Only me"));
        addComponent(new Button("Everyone"));

        Label statusLabel = new Label("Status");
        addComponent(statusLabel);
        setComponentAlignment(statusLabel, Alignment.MIDDLE_CENTER);

        addComponent(new Button("Open", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                List<ReportStatus> statuses = new ArrayList<ReportStatus>();
                statuses.add(ReportStatus.OPEN);
                presenter.reportsStatusFilterChanged(statuses);
            }
        }));
        addComponent(new Button("All kinds", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                presenter.reportsStatusFilterChanged(new ArrayList<ReportStatus>());
            }
        }));
        addComponent(new Button("Custom"));

        setSpacing(true);
    }
}
