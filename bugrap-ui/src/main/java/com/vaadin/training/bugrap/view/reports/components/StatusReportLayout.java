package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;

public class StatusReportLayout extends HorizontalLayout {
    public StatusReportLayout() {
        Label reportsForLabel = new Label("Reports for");
        addComponent(reportsForLabel);
        setComponentAlignment(reportsForLabel, Alignment.MIDDLE_CENTER);

        NativeSelect projectVersionsSelect = new NativeSelect();
        projectVersionsSelect.addItem("1.2.3-pre12");
        projectVersionsSelect.addItem("1.2.3-pre11");
        projectVersionsSelect.addItem("1.2.3-pre10");
        projectVersionsSelect.setValue("1.2.3-pre12");
        addComponent(projectVersionsSelect);

        setSpacing(true);
    }
}
