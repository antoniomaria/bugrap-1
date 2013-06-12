package com.vaadin.training.bugrap.domain.entity;

import javax.persistence.*;

@MappedSuperclass
abstract public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private int consistencyVersion;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getConsistencyVersion() {
        return consistencyVersion;
    }

    public void setConsistencyVersion(int consistencyVersion) {
        this.consistencyVersion = consistencyVersion;
    }

    public boolean isPersistent() {
        return id != null;
    }
}
