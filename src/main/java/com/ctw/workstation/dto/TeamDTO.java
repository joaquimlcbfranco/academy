package com.ctw.workstation.dto;

import java.time.LocalDateTime;

public class TeamDTO {

    private Long id;
    private String productName;
    private String name;
    private String defaultLocation;
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

    public TeamDTO(String productName, String name, String defaultLocation) {
        this.productName = productName;
        this.name = name;
        this.defaultLocation = defaultLocation;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
