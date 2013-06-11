package com.vaadin.training.bugrap.service;

import com.vaadin.training.bugrap.domain.entity.ProjectVersion;
import com.vaadin.training.bugrap.domain.entity.ReportResolution;
import com.vaadin.training.bugrap.domain.entity.ReportStatus;
import com.vaadin.training.bugrap.domain.entity.User;

import java.util.List;

public class ReportQuery {
    private User assignee;
    private ProjectVersion version;
    private List<ReportStatus> statuses;
    private List<ReportResolution> resolutions;


    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public ProjectVersion getVersion() {
        return version;
    }

    public void setVersion(ProjectVersion version) {
        this.version = version;
    }

    public List<ReportStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<ReportStatus> statuses) {
        this.statuses = statuses;
    }

    public List<ReportResolution> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<ReportResolution> resolutions) {
        this.resolutions = resolutions;
    }
}
