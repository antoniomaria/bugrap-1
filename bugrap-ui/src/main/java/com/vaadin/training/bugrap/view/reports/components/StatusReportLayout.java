package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.data.Property;
import com.vaadin.training.bugrap.domain.entity.ProjectVersion;
import com.vaadin.training.bugrap.view.reports.ReportsPresenter;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;

import java.util.List;

public class StatusReportLayout extends HorizontalLayout {

    private final NativeSelect projectVersionsSelect;
    private final Property.ValueChangeListener valueChangeListener;
    private ReportsPresenter presenter;
    private ProjectVersion allVersions;

    public void setPresenter(ReportsPresenter presenter) {
        this.presenter = presenter;
    }

    public StatusReportLayout() {
        Label reportsForLabel = new Label("Reports for");
        addComponent(reportsForLabel);
        setComponentAlignment(reportsForLabel, Alignment.MIDDLE_CENTER);

        projectVersionsSelect = new NativeSelect();
        projectVersionsSelect.setNullSelectionAllowed(false);
        projectVersionsSelect.setImmediate(true);
        valueChangeListener = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                ProjectVersion version = (ProjectVersion) event.getProperty().getValue();

                if (allVersions.equals(version)) {
                    version = null;
                }
                presenter.projectVersionChanged(version);
            }
        };

        addComponent(projectVersionsSelect);

        setSpacing(true);
    }

    public void updateProjectVersions(List<ProjectVersion> versions) {
        projectVersionsSelect.removeValueChangeListener(valueChangeListener);

        projectVersionsSelect.removeAllItems();

        if(versions.size() > 1) {
            allVersions = new ProjectVersion();
            projectVersionsSelect.addItem(allVersions);
            projectVersionsSelect.setItemCaption(allVersions, "All versions");
            projectVersionsSelect.setValue(allVersions);
        }

        for (ProjectVersion version : versions) {
            projectVersionsSelect.addItem(version);
            projectVersionsSelect.setItemCaption(version, version.getVersion());
        }

        if(versions.size() == 1) {
            projectVersionsSelect.setValue(versions.iterator().next());
        }

        projectVersionsSelect.addValueChangeListener(valueChangeListener);
    }
}
