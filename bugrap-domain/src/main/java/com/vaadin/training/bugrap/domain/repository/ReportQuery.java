package com.vaadin.training.bugrap.domain.repository;

import com.google.common.collect.Lists;
import com.vaadin.training.bugrap.domain.entity.ProjectVersion;
import com.vaadin.training.bugrap.domain.entity.ReportResolution;
import com.vaadin.training.bugrap.domain.entity.ReportStatus;
import com.vaadin.training.bugrap.domain.entity.User;

import java.util.List;

public class ReportQuery {
    private User assignee;
    private ProjectVersion version;
    private ReportStatus status;
    private List<ReportResolution> resolutions = Lists.newLinkedList();
    private String searchTerm;


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

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public List<ReportResolution> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<ReportResolution> resolutions) {
        this.resolutions = resolutions;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getSearchTerm() {
        return searchTerm;
    }
}
