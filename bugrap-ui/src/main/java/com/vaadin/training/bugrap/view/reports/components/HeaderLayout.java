package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Reindeer;

public class HeaderLayout extends HorizontalLayout {

    private final Label projectNameLabel;

    public HeaderLayout() {
        setWidth("100%");

        projectNameLabel = new Label();
        addComponent(projectNameLabel);
    }

    public void updateProjectName(String projectName) {
        projectNameLabel.setValue(projectName);
    }
}
