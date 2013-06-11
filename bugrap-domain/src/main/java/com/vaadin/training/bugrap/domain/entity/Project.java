package com.vaadin.training.bugrap.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class Project extends AbstractEntity {

    private String name;

    @ManyToOne
    private User manager;

    @OneToMany
    private Set<User> participants;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "project")
    private List<ProjectVersion> projectVersions;

    public Project() {
        participants = new HashSet<User>();
        projectVersions = new ArrayList<ProjectVersion>();
    }

    public ProjectVersion addProjectVersion(String versionName) {
        ProjectVersion version = new ProjectVersion();
        version.setVersion(versionName);
        version.setProject(this);

        projectVersions.add(version);

        return version;
    }

    public List<ProjectVersion> getProjectVersions() {
        return Collections.unmodifiableList(projectVersions);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return name;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void addParticipant(User participant) {
        participants.add(participant);
    }

    public void removeParticipant(User participant) {
        participants.remove(participant);
    }
}
