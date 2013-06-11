package com.vaadin.training.bugrap.view.reports;

import com.vaadin.training.bugrap.domain.entity.Project;
import com.vaadin.training.bugrap.view.mvp.View;

public interface ReportsOverviewView extends View {
    public static final String NAME = "reports";

    void showProject(Project project);
}
