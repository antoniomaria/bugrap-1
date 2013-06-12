package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.training.bugrap.domain.entity.*;
import com.vaadin.ui.*;

public class ReportEditLayout extends VerticalLayout {

    private FieldGroup fieldGroup;

    private final ComboBox priorityCombobox;
    private final ComboBox typeCombobox;
    private final ComboBox statusCombobox;
    private final ComboBox assignedCombobox;
    private final ComboBox versionCombobox;
    private final TextArea descriptionTextArea;
    private final Label reportSummaryLabel;

    public ReportEditLayout() {
        setSizeFull();

        reportSummaryLabel = new Label();
        addComponent(reportSummaryLabel);

        HorizontalLayout reportFormLayout = new HorizontalLayout();

        reportFormLayout.setSpacing(true);

        priorityCombobox = new ComboBox("Priority");
        reportFormLayout.addComponent(priorityCombobox);

        typeCombobox = new ComboBox("Type");
        for (ReportType reportType : ReportType.values()) {
            typeCombobox.addItem(reportType);
            typeCombobox.setItemCaption(reportType, reportType.toString());
        }
        reportFormLayout.addComponent(typeCombobox);

        statusCombobox = new ComboBox("Status");
        for (ReportStatus reportStatus : ReportStatus.values()) {
            statusCombobox.addItem(reportStatus);
            statusCombobox.setItemCaption(reportStatus, reportStatus.toString());
        }
        reportFormLayout.addComponent(statusCombobox);

        assignedCombobox = new ComboBox("Assigned to");
        reportFormLayout.addComponent(assignedCombobox);

        versionCombobox = new ComboBox("Version");
        reportFormLayout.addComponent(versionCombobox);

        Button updateButton = new Button("Update", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit();
                } catch (FieldGroup.CommitException e) {
                    Notification.show("Can't save the report", Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        reportFormLayout.addComponent(updateButton);
        reportFormLayout.setComponentAlignment(updateButton, Alignment.BOTTOM_CENTER);

        Button revertButton = new Button("Revert", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                fieldGroup.discard();
            }
        });
        reportFormLayout.addComponent(revertButton);
        reportFormLayout.setComponentAlignment(revertButton, Alignment.BOTTOM_CENTER);

        addComponent(reportFormLayout);

        descriptionTextArea = new TextArea();
        descriptionTextArea.setSizeFull();
        descriptionTextArea.setValue("Blablabla");
        addComponent(descriptionTextArea);
        setExpandRatio(descriptionTextArea, 1.0f);

        setSpacing(true);
    }

    public void showReport(Report report) {
        fieldGroup = new FieldGroup(new BeanItem<Report>(report));
        fieldGroup.bind(priorityCombobox, "priority");
        fieldGroup.bind(typeCombobox, "type");
        fieldGroup.bind(statusCombobox, "status");
        fieldGroup.bind(assignedCombobox, "assigned");
        fieldGroup.bind(versionCombobox, "projectVersion");

        Project project = report.getProjectVersion().getProject();
        for (ProjectVersion projectVersion : project.getProjectVersions()) {
            versionCombobox.addItem(projectVersion);
            versionCombobox.setItemCaption(projectVersion, projectVersion.getVersion());
        }

        //todo: fix to load all users
        assignedCombobox.addItem(report.getAssigned());
        assignedCombobox.setItemCaption(report.getAssigned(), report.getAssigned().getName());

        reportSummaryLabel.setValue(report.getSummary());
        priorityCombobox.setValue(report.getPriority());
        typeCombobox.setValue(report.getType());
        statusCombobox.setValue(report.getStatus());
        versionCombobox.setValue(report.getProjectVersion());
        assignedCombobox.setValue(report.getAssigned().getName());
        descriptionTextArea.setValue(report.getDescription());
    }
}
