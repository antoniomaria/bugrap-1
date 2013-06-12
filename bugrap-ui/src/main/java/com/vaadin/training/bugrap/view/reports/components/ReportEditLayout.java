package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.training.bugrap.domain.entity.*;
import com.vaadin.training.bugrap.view.reports.ReportsPresenter;
import com.vaadin.ui.*;

public class ReportEditLayout extends VerticalLayout {

    private ReportsPresenter presenter;

    private FieldGroup fieldGroup;

    private final NativeSelect priorityCombobox;
    private final NativeSelect typeCombobox;
    private final NativeSelect statusCombobox;
    private final NativeSelect assignedCombobox;
    private final NativeSelect versionCombobox;
    private final TextArea descriptionTextArea;
    private final Label reportSummaryLabel;

    private final Button updateButton;

    public void setPresenter(ReportsPresenter presenter) {
        this.presenter = presenter;
    }

    public ReportEditLayout() {
        setSizeFull();

        reportSummaryLabel = new Label();
        addComponent(reportSummaryLabel);

        HorizontalLayout reportFormLayout = new HorizontalLayout();

        reportFormLayout.setSpacing(true);

        priorityCombobox = new NativeSelect("Priority");
        reportFormLayout.addComponent(priorityCombobox);
        priorityCombobox.setNullSelectionAllowed(false);

        typeCombobox = new NativeSelect("Type");
        typeCombobox.setNullSelectionAllowed(false);
        for (ReportType reportType : ReportType.values()) {
            typeCombobox.addItem(reportType);
            typeCombobox.setItemCaption(reportType, reportType.toString());
        }
        reportFormLayout.addComponent(typeCombobox);

        statusCombobox = new NativeSelect("Status");
        statusCombobox.setNullSelectionAllowed(false);
        for (ReportStatus reportStatus : ReportStatus.values()) {
            statusCombobox.addItem(reportStatus);
            statusCombobox.setItemCaption(reportStatus, reportStatus.toString());
        }
        reportFormLayout.addComponent(statusCombobox);

        assignedCombobox = new NativeSelect("Assigned to");
        assignedCombobox.setNullSelectionAllowed(false);
        reportFormLayout.addComponent(assignedCombobox);

        versionCombobox = new NativeSelect("Version");
        versionCombobox.setNullSelectionAllowed(false);
        reportFormLayout.addComponent(versionCombobox);

        updateButton = new Button("Update", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit();

                    presenter.reportUpdated();
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
        fieldGroup.bind(descriptionTextArea, "description");

        Project project = report.getProjectVersion().getProject();
        versionCombobox.removeAllItems();
        for (ProjectVersion projectVersion : project.getProjectVersions()) {
            versionCombobox.addItem(projectVersion);
            versionCombobox.setItemCaption(projectVersion, projectVersion.getVersion());
        }

        //todo: fix to load all users
        assignedCombobox.removeAllItems();
        for (User user : report.getProjectVersion().getProject().getParticipants()) {
            assignedCombobox.addItem(user);
            assignedCombobox.setItemCaption(user, user.getName());
        }

        reportSummaryLabel.setValue(report.getSummary());
        priorityCombobox.setValue(report.getPriority());
        typeCombobox.setValue(report.getType());
        statusCombobox.setValue(report.getStatus());
        versionCombobox.setValue(report.getProjectVersion());
        assignedCombobox.setValue(report.getAssigned());
        descriptionTextArea.setValue(report.getDescription());

        updateButton.focus();
    }
}
