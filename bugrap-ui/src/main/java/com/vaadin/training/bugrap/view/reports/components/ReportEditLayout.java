package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.training.bugrap.domain.entity.Report;
import com.vaadin.ui.*;

public class ReportEditLayout extends VerticalLayout {

    private final FieldGroup fieldGroup;

    public ReportEditLayout() {
        setWidth("100%");
        setHeight("600px");

        Label reportSummaryLabel = new Label("MenuBar bottleneck usability problem");
        addComponent(reportSummaryLabel);

        HorizontalLayout reportFormLayout = new HorizontalLayout();

        reportFormLayout.setSpacing(true);

        ComboBox priorityCombobox = new ComboBox("Priority");
        reportFormLayout.addComponent(priorityCombobox);

        ComboBox typeCombobox = new ComboBox("Type");
        reportFormLayout.addComponent(typeCombobox);

        ComboBox statusCombobox = new ComboBox("Status");
        reportFormLayout.addComponent(statusCombobox);

        ComboBox assignedCombobox = new ComboBox("Assigned to");
        reportFormLayout.addComponent(assignedCombobox);

        ComboBox versionCombobox = new ComboBox("Version");
        reportFormLayout.addComponent(versionCombobox);

        fieldGroup = new FieldGroup(new BeanItem<Report>(new Report()));
        fieldGroup.bind(priorityCombobox, "priority");
        fieldGroup.bind(typeCombobox, "type");
        fieldGroup.bind(statusCombobox, "status");
        fieldGroup.bind(assignedCombobox, "assigned");
//        fieldGroup.bind(versionCombobox, "version");

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

        TextArea descriptionTextArea = new TextArea();
        descriptionTextArea.setSizeFull();
        descriptionTextArea.setValue("Blablabla");
        addComponent(descriptionTextArea);
        setExpandRatio(descriptionTextArea, 1.0f);

        setSpacing(true);
    }
}
