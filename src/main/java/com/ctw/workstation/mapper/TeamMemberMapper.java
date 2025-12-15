package com.ctw.workstation.mapper;

import com.ctw.workstation.data.entity.Team;
import com.ctw.workstation.data.entity.TeamMember;
import com.ctw.workstation.dto.TeamMemberDTO;

public class TeamMemberMapper {

    public static TeamMemberDTO toDTO(TeamMember teamMember) {
        TeamMemberDTO dto = new TeamMemberDTO(teamMember.getCtwId(), teamMember.getName());
        dto.setId(teamMember.getId());
        dto.setTeamId(teamMember.getTeam().getId());

        return dto;
    }
    public static TeamMemberDTO toDTOWithTimestamps(TeamMember teamMember) {
        TeamMemberDTO dto = toDTO(teamMember);
        dto.setCreatedAt(teamMember.getCreatedAt());
        dto.setModifiedAt(teamMember.getModifiedAt());

        return dto;
    }

    public static TeamMember toTeamMember(TeamMemberDTO dto) {
        TeamMember teamMember = new TeamMember();
        teamMember.setCtwId(dto.getCtwId());
        teamMember.setName(dto.getName());

        return teamMember;
    }
}
