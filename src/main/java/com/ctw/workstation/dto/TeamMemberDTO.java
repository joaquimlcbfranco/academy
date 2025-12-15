package com.ctw.workstation.dto;

import java.time.LocalDateTime;

public class TeamMemberDTO {

   private Long id;
   private String ctwId;
   private String name;
   private LocalDateTime createdAt;
   private LocalDateTime modifiedAt;
   private Long teamId;

   public TeamMemberDTO(String ctwId, String name) {
       this.ctwId = ctwId;
       this.name = name;
   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCtwId() {
        return ctwId;
    }

    public void setCtwId(String ctwId) {
        this.ctwId = ctwId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }


}
