package com.ctw.workstation.mapper;

import com.ctw.workstation.data.entity.Team;
import com.ctw.workstation.dto.TeamDTO;

public class TeamMapper {

    public static TeamDTO toDTOWithTimestamps(Team team) {
        if (team == null) {
            return null;
        }

        TeamDTO dto = new TeamDTO(team.getProductName(), team.getName(), team.getDefaultLocation());
        dto.setCreatedAt(team.getCreatedAt());
        dto.setModifiedAt(team.getModifiedAt());
        dto.setId(team.getId());

        return dto;
    }

    public static TeamDTO toDTO(Team team) {

        TeamDTO dto = new TeamDTO(team.getProductName(), team.getName(), team.getDefaultLocation());
        dto.setId(team.getId());

        return dto;
    }
    public static Team toTeam(TeamDTO teamDTO) {

       Team team = new Team();
       team.setName(teamDTO.getName());
       team.setDefaultLocation(teamDTO.getDefaultLocation());
       team.setProductName(teamDTO.getProductName());
       return team;
    }
}
