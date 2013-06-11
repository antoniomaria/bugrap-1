package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.training.bugrap.domain.ProjectVersion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;

import java.util.List;

public class StatusReportLayout extends HorizontalLayout {

    private final NativeSelect projectVersionsSelect;

    public StatusReportLayout() {
        Label reportsForLabel = new Label("Reports for");
        addComponent(reportsForLabel);
        setComponentAlignment(reportsForLabel, Alignment.MIDDLE_CENTER);

        projectVersionsSelect = new NativeSelect();
        projectVersionsSelect.setNullSelectionAllowed(false);
        projectVersionsSelect.addItem("1.2.3-pre12");
        projectVersionsSelect.addItem("1.2.3-pre11");
        projectVersionsSelect.addItem("1.2.3-pre10");
        projectVersionsSelect.setValue("1.2.3-pre12");
        addComponent(projectVersionsSelect);

        setSpacing(true);
    }

    public void updateProjectVersions(List<ProjectVersion> versions) {
        projectVersionsSelect.removeAllItems();

        if(versions.size() > 1) {
            projectVersionsSelect.addItem("");
            projectVersionsSelect.setItemCaption("", "All versions");
            projectVersionsSelect.setValue("");
        }

        for (ProjectVersion version : versions) {
            projectVersionsSelect.addItem(version.getVersion());
        }

        if(versions.size() == 1) {
            projectVersionsSelect.setValue(versions.iterator().next().getVersion());
        }
    }
}
