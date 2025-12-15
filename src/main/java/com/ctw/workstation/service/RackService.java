package com.ctw.workstation.service;

import com.ctw.workstation.data.entity.Rack;
import com.ctw.workstation.data.entity.Team;
import com.ctw.workstation.data.repository.RackRepository;
import com.ctw.workstation.data.repository.TeamRepository;
import com.ctw.workstation.dto.RackDTO;
import com.ctw.workstation.exception.*;
import com.ctw.workstation.mapper.RackMapper;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * RackService to interact with the repositories and generate the business logic
 * Since this is a service class, it should contain the business logic of the application.
 */
@Singleton
public class RackService {

    @Inject
    RackRepository rackRepository;
    @Inject
    TeamRepository teamRepository;

    public List<RackDTO> getRacks() {
        return rackRepository
                .findAll()
                .stream()
                .map(RackMapper::toDTOWithTimestamps)
                .toList();
    }

    public RackDTO getRackById(Long id) throws EntityNotFoundException {
        Rack foundRack = rackRepository.findById(id);
        if  (foundRack == null) {
            throw new EntityNotFoundException("Rack with id " + id + " not found");
        }

        return RackMapper.toDTOWithTimestamps(foundRack);
    }

    public List<RackDTO> getRacksByTeamId(Long id) throws EntityNotFoundException {
        Team foundTeam = teamRepository.findById(id);
        if (foundTeam == null) {
            throw new EntityNotFoundException("Team with id " + id + " not found");
        }

        return rackRepository.find("select r from Rack r where r.teamId = $d", id).stream().map(RackMapper::toDTOWithTimestamps).toList();
    }

    @Transactional
    public RackDTO createRack(RackDTO dto) throws EntityNotFoundException {
        Team foundTeam = teamRepository.findById(dto.getTeamId());
        if (foundTeam == null) {
            throw new EntityNotFoundException("Team with id " + dto.getTeamId() + " not found");
        }

        Rack newRack = RackMapper.toRack(dto);
        newRack.setTeam(foundTeam);
        rackRepository.persist(newRack);
        rackRepository.flush();

        return RackMapper.toDTOWithTimestamps(newRack);
    }

    @Transactional
    public RackDTO updateRack(Long id, RackDTO dto) throws EntityNotFoundException {
        Rack foundRack = rackRepository.findById(id);
        if (foundRack == null) {
            throw new EntityNotFoundException("Rack with id " + dto.getId() + " not found");
        }

        Team foundTeam = teamRepository.findById(dto.getTeamId());
        if (foundTeam == null) {
            throw new EntityNotFoundException("Team with id " + dto.getTeamId() + " not found");
        }

        foundRack.setSerialNumber(dto.getSerialNumber());
        foundRack.setLocation(dto.getLocation());
        foundRack.setStatus(dto.getStatus());
        foundRack.setModifiedAt(LocalDateTime.now());
        foundRack.setTeam(foundTeam);

        return RackMapper.toDTOWithTimestamps(foundRack);
    }

    @Transactional
    public void deleteRack(Long id) throws EntityNotFoundException {
        Rack foundRack = rackRepository.findById(id);
        if (foundRack == null) {
            throw new EntityNotFoundException("Rack with id " + id + " not found");
        }

        rackRepository.delete(foundRack);
    }

    @Transactional
    public void deleteAllRacks() {
        rackRepository.deleteAll();
    }


}
