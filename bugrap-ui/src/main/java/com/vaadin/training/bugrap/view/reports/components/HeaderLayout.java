package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Reindeer;

public class HeaderLayout extends HorizontalLayout {
    public HeaderLayout() {
        setWidth("100%");

        Label projectNameLabel = new Label("Project name that is rather long");
        addComponent(projectNameLabel);
    }
}
