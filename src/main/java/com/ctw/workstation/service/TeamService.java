package com.ctw.workstation.service;

import com.ctw.workstation.data.entity.Team;
import com.ctw.workstation.data.repository.TeamMemberRepository;
import com.ctw.workstation.data.repository.TeamRepository;
import com.ctw.workstation.dto.TeamDTO;
import com.ctw.workstation.exception.EntityNotFoundException;
import com.ctw.workstation.mapper.TeamMapper;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.jboss.logging.MDC;

import javax.naming.directory.InvalidAttributesException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Singleton
public class TeamService {

    @Inject
    TeamRepository teamRepository;

    public List<TeamDTO> getTeams() {
        String logId = UUID.randomUUID().toString();
        MDC.put("logId", logId);
        return teamRepository.findAll().list().stream().map(TeamMapper::toDTOWithTimestamps).toList();
    }

    public Team getTeamById(Long id) throws EntityNotFoundException {
        if (teamRepository.findById(id) == null) {
            throw new EntityNotFoundException("Team with id " + id + " not found");
        }

        return teamRepository.findById(id);
    }

    @Transactional
    public TeamDTO addTeam(TeamDTO teamDTO) throws InvalidAttributesException {
        Team team = TeamMapper.toTeam(teamDTO);
        if (team.getName().trim().isEmpty() || team.getProductName().trim().isEmpty() || team.getDefaultLocation().trim().isEmpty()) {
            throw new InvalidAttributesException("name, productName and defaultLocation must not be empty");
        }
        teamRepository.persist(team);
        teamRepository.flush();

        return TeamMapper.toDTOWithTimestamps(team);
    }

    @Transactional
    public TeamDTO updateTeam(Long id, TeamDTO teamDTO) throws EntityNotFoundException {
        Team foundTeam = teamRepository.findById(id);
        if (foundTeam == null) {
            throw new EntityNotFoundException("Team with id " + id + " not found");
        }

        foundTeam.setName(teamDTO.getName());
        foundTeam.setDefaultLocation(teamDTO.getDefaultLocation());
        foundTeam.setProductName(teamDTO.getProductName());
        foundTeam.setModifiedAt(LocalDateTime.now());

        return TeamMapper.toDTOWithTimestamps(foundTeam);
    }


    @Transactional
    public void deleteTeam(Long id) throws EntityNotFoundException {
        Team foundTeam = teamRepository.findById(id);

        if (foundTeam == null) {
            throw new EntityNotFoundException("Team with id " + id + " not found");
        }

        teamRepository.delete(teamRepository.findById(id));
    }

    @Transactional
    public void deleteAllTeams() {
        teamRepository.deleteAll();
    }

}
