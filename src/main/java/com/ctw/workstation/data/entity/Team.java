package com.ctw.workstation.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Team entity that extends CommonData.
 * Has the teams product as an additional field.
 */
@Entity
@Table(name = "t_team")
public class Team extends BaseEntity {

    @Column(name="product")
    private String productName;
    @Column(name="name")
    private String name;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "default_location")
    private String defaultLocation;
    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;
    @OneToMany(mappedBy = "team")
    private List<TeamMember> teamMembers;
    @OneToMany(mappedBy = "team")
    private List<Rack> racks;

    public Team() {}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id && Objects.equals(name, team.name) && Objects.equals(productName, team.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productName);
    }

    @Override
    public String toString() {
        return "Team{" +
                "productName='" + productName + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}


