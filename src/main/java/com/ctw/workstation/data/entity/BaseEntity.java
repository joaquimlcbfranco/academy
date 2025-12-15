package com.ctw.workstation.data.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Common data class that will be extended by other classes
 * It contains common data fields that are shared by other classes
 * and a builder pattern
 */

@MappedSuperclass
public class BaseEntity {
    // Protected fields to be accessed by subclasses
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected long id;

    // Protected constructor to prevent instantiation from outside
    // package or classes that are not extending this class
    protected BaseEntity() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
