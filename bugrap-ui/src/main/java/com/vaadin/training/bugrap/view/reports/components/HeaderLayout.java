package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.training.bugrap.view.reports.ReportsPresenter;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.themes.Reindeer;

public class HeaderLayout extends HorizontalLayout {

    private ReportsPresenter presenter;

    public void setPresenter(ReportsPresenter presenter) {
        this.presenter = presenter;
    }

    private final Label projectNameLabel;
    private final Label userLabel;
    private final Button logoutButton;

    public HeaderLayout() {
        setWidth("100%");

        projectNameLabel = new Label();
        addComponent(projectNameLabel);
        setExpandRatio(projectNameLabel, 1.0f);

        userLabel = new Label();
        userLabel.setWidth("100px");
        addComponent(userLabel);

        logoutButton = new Button("Logout", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                presenter.logoutButtonClicked();
            }
        });
        addComponent(logoutButton);
    }

    public void updateProjectName(String projectName) {
        projectNameLabel.setValue(projectName);
    }

    public void updateUserName(String username) {
        userLabel.setValue(username);
    }
}
