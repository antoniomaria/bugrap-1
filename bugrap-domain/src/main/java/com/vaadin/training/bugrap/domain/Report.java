package com.vaadin.training.bugrap.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Report extends AbstractEntity {

    @Enumerated
    private ReportType type;

    @Enumerated
    private ReportStatus status;

    @Enumerated
    private ReportResolution resolution;

    @Column(columnDefinition = "VARCHAR(5000)")
    private String summary;

    @Column(columnDefinition = "VARCHAR(5000)")
    private String description;

    @ManyToOne
    private ProjectVersion projectVersion;

    @Enumerated
    private ReportPriority priority;

    @ManyToOne
    private User assigned;

    @ManyToOne
    private User author;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReportPriority getPriority() {
        return priority;
    }

    public void setPriority(ReportPriority priority) {
        this.priority = priority;
    }

    public User getAssigned() {
        return assigned;
    }

    public void setAssigned(User assigned) {
        this.assigned = assigned;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public ReportResolution getResolution() {
        return resolution;
    }

    public void setResolution(ReportResolution resolution) {
        this.resolution = resolution;
    }

    public ProjectVersion getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(ProjectVersion occursIn) {
        this.projectVersion = occursIn;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @PrePersist
    void updateDates() {
        if (timestamp == null) {
            timestamp = new Date();
        }
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Transient
    public Object getRealStatus() {
        if (status == ReportStatus.OPEN) {
            return status;
        } else {
            return resolution;
        }
    }

    @Transient
    public void setRealStatus(Object o) {

        if (o instanceof ReportStatus) {
            status = ReportStatus.OPEN;
            resolution = null;
        } else {
            status = ReportStatus.CLOSED;
            resolution = (ReportResolution) o;
        }
    }

    @Transient
    public static ReportResolution getReportResolution(Object x) {
        if (x.equals("Needs more information")) {
            return ReportResolution.NEEDMOREINFO;
        }
        final String s = ((String) x).toUpperCase().replaceAll(" ", "")
                .replaceAll("'", "");
        return ReportResolution.valueOf(ReportResolution.class, s);
    }

    public boolean summaryOrDescriptionLike(String searchEntry) {
        if (this.summary != null && this.stringLike(this.summary, searchEntry)) {
            return true;
        }
        if (this.description != null
                && this.stringLike(this.description, searchEntry)) {
            return true;
        }
        return false;
    }

    public boolean stringLike(String origin, String searchEntry) {
        return origin.toLowerCase().contains(searchEntry.toLowerCase());
    }
}
