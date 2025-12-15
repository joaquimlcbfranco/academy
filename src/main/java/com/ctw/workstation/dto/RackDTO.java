package com.ctw.workstation.dto;

import java.time.LocalDateTime;
import com.ctw.workstation.data.enums.Status;

public class RackDTO {
    private Long id;
    private String serialNumber;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Status status;
    private Long teamId;

    public RackDTO(String serialNumber, String location, Status status) {
       this.serialNumber = serialNumber;
       this.location = location;
       this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getTeamId() {
        return teamId;
    }
}
