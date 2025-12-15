package com.ctw.workstation.service;

import com.ctw.workstation.data.entity.Team;
import com.ctw.workstation.data.entity.TeamMember;
import com.ctw.workstation.data.repository.TeamMemberRepository;
import com.ctw.workstation.data.repository.TeamRepository;
import com.ctw.workstation.dto.TeamMemberDTO;
import com.ctw.workstation.exception.EntityNotFoundException;
import com.ctw.workstation.mapper.TeamMemberMapper;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Singleton
public class TeamMemberService {

    @Inject
    TeamMemberRepository teamMemberRepository;
    @Inject
    TeamRepository teamRepository;

    public List<TeamMemberDTO> getTeamMembers() {

        return teamMemberRepository
                .findAll()
                .stream()
                .map(TeamMemberMapper::toDTOWithTimestamps)
                .toList();
    }

    public TeamMemberDTO getTeamMemberById(Long id) throws EntityNotFoundException {
        TeamMember foundMember = teamMemberRepository.findById(id);
        if (foundMember == null) {
            throw new EntityNotFoundException(String.format("Team member with id " + id + " not found"));
        }

        return TeamMemberMapper.toDTOWithTimestamps(foundMember);
    }

    @Transactional
    public TeamMemberDTO createTeamMember(TeamMemberDTO dto) throws EntityNotFoundException {
        Team foundTeam = teamRepository.findById(dto.getTeamId());
        if (foundTeam == null) {
            throw new EntityNotFoundException("Team with id " + dto.getTeamId() + " not found");
        }

        TeamMember teamMember = TeamMemberMapper.toTeamMember(dto);
        teamMember.setTeam(foundTeam);
        teamMemberRepository.persist(teamMember);

        return TeamMemberMapper.toDTOWithTimestamps(teamMember);
    }

    @Transactional
    public TeamMemberDTO updateTeamMember(Long id, TeamMemberDTO dto) throws EntityNotFoundException {
        TeamMember foundTeamMember = teamMemberRepository.findById(id);
        if (foundTeamMember == null) {
            throw new EntityNotFoundException(String.format("Team member with id " + id + " not found"));
        }

        Team foundTeam = teamRepository.findById(dto.getTeamId());
        if (foundTeam == null) {
            throw new EntityNotFoundException(String.format("Team with id " + dto.getTeamId() + " not found"));
        }

        foundTeamMember.setName(dto.getName());
        foundTeamMember.setCtwId(dto.getCtwId());
        foundTeamMember.setTeam(foundTeam);
        foundTeamMember.setModifiedAt(LocalDateTime.now());

        return TeamMemberMapper.toDTOWithTimestamps(foundTeamMember);
    }

    @Transactional
    public void deleteTeamMember(Long id) throws EntityNotFoundException {
        TeamMember foundTeamMember = teamMemberRepository.findById(id);
        if (foundTeamMember == null) {
            throw new EntityNotFoundException(String.format("Team member with id " + id + " not found"));
        }

        teamMemberRepository.delete(foundTeamMember);
    }

    @Transactional
    public void deleteAllTeamMembers() {
        teamMemberRepository.deleteAll();
    }
}
