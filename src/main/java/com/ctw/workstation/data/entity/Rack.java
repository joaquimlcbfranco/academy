package com.ctw.workstation.data.entity;

import com.ctw.workstation.data.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Rack is a data class that represents a rack in the system.
 * A rack is a physical object that can be used to store servers.
 * A rack has a serial number that uniquely identifies it.
 * A rack has a location that represents its physical location.
 */
@Entity
@Table(name = "t_rack")
public class Rack extends BaseEntity {
    // Serial number is a unique identifier for a rack, it is used to identify a rack
    @Column(name = "serial_number")
    private String serialNumber;
    // Location is the physical location of the rack
    @Column(name = "default_location")
    private String location;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    @OneToMany(mappedBy = "rack")
    private List<Booking> bookings;
    @OneToMany(mappedBy = "rack")
    private List<RackAsset> rackAssets;


    public Rack() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }


    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rack rack = (Rack) o;
        return Objects.equals(serialNumber, rack.serialNumber) && Objects.equals(location, rack.location) &&
                Objects.equals(createdAt, rack.createdAt) && Objects.equals(modifiedAt, rack.modifiedAt) &&
                Objects.equals(status, rack.status);
    }


    @Override
    public String toString() {
        return "Rack{" +
                "serialNumber=" + serialNumber +
                ", location='" + location + '\'' +
                ", id=" + id +
                '}';
    }

}
