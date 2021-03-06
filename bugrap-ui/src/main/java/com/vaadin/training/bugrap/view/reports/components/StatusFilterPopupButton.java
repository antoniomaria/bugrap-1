package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.data.Property;
import com.vaadin.training.bugrap.domain.entity.ReportResolution;
import com.vaadin.training.bugrap.domain.entity.ReportStatus;
import com.vaadin.training.bugrap.view.reports.ReportsPresenter;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.hene.popupbutton.PopupButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StatusFilterPopupButton extends CustomComponent {
    private ReportsPresenter presenter;

    public void setPresenter(ReportsPresenter presenter) {
        this.presenter = presenter;
    }

    public StatusFilterPopupButton() {
        PopupButton popupButton = new PopupButton("Custom");

        VerticalLayout popupButtonLayout = new VerticalLayout();
        popupButtonLayout.setHeight("160px");

        final CheckBox openReportsCheckbox = new CheckBox("Open", true);
        openReportsCheckbox.setImmediate(true);
        popupButtonLayout.addComponent(openReportsCheckbox);
        popupButtonLayout.setExpandRatio(openReportsCheckbox, 1.0f);

        final OptionGroup optionGroup = new OptionGroup();
        optionGroup.setMultiSelect(true);
        for (ReportResolution reportResolution : ReportResolution.values()) {
            optionGroup.addItem(reportResolution);
            optionGroup.setItemCaption(reportResolution, reportResolution.toString());
        }
        optionGroup.setValue(Arrays.asList(ReportResolution.values()));
        optionGroup.setImmediate(true);
        popupButtonLayout.addComponent(optionGroup);

        popupButton.setContent(popupButtonLayout);

        setCompositionRoot(popupButton);

        Property.ValueChangeListener listener = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                ReportStatus status = openReportsCheckbox.getValue() ? ReportStatus.OPEN : ReportStatus.CLOSED;

                Collection<ReportResolution> resolutions = (Collection<ReportResolution>) optionGroup.getValue();

                presenter.reportsCustomFilterChanged(status, new ArrayList<ReportResolution>(resolutions));
            }
        };

        openReportsCheckbox.addValueChangeListener(listener);
        optionGroup.addValueChangeListener(listener);
    }
}
