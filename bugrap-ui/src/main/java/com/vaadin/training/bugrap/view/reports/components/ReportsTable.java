package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.training.bugrap.domain.Report;
import com.vaadin.ui.Table;

import java.util.List;

public class ReportsTable extends Table {
    public ReportsTable() {
        setSizeFull();
    }

    public void showReports(List<Report> reports) {
        setContainerDataSource(new BeanItemContainer<Report>(Report.class, reports));
        setVisibleColumns(new Object[]{"priority", "type", "summary", "assigned", "timestamp"});
    }
}
