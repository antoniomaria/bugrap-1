package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.training.bugrap.view.mvp.Presenter;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class FiltersLayout extends HorizontalLayout {
    public FiltersLayout() {
        Label assigneesLabel = new Label("Assignees");
        addComponent(assigneesLabel);
        setComponentAlignment(assigneesLabel, Alignment.MIDDLE_CENTER);

        addComponent(new Button("Only me"));
        addComponent(new Button("Everyone"));

        Label statusLabel = new Label("Status");
        addComponent(statusLabel);
        setComponentAlignment(statusLabel, Alignment.MIDDLE_CENTER);

        addComponent(new Button("Open"));
        addComponent(new Button("All kinds"));
        addComponent(new Button("Custom"));

        setSpacing(true);
    }
}
