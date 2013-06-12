package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.training.bugrap.domain.entity.Report;
import com.vaadin.training.bugrap.view.reports.ReportsPresenter;
import com.vaadin.ui.Table;

import java.util.List;

public class ReportsTable extends Table {

    private ReportsPresenter presenter;

    public void setPresenter(ReportsPresenter presenter) {
        this.presenter = presenter;
    }

    public ReportsTable() {
        setSelectable(true);
        setImmediate(true);
        setSizeFull();

        addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                presenter.reportSelected((Report)event.getProperty().getValue());
            }
        });
    }

    public void showReports(List<Report> reports) {
        setContainerDataSource(new BeanItemContainer<Report>(Report.class, reports));
        setVisibleColumns(new Object[]{"priority", "type", "summary", "assigned", "timestamp", "projectVersion"});
    }
}
