package com.ctw.workstation.mapper;

import com.ctw.workstation.data.entity.Rack;
import com.ctw.workstation.dto.RackDTO;

public class RackMapper {

    public static RackDTO toDTO(Rack rack) {
        RackDTO dto = new RackDTO(rack.getSerialNumber(), rack.getLocation(), rack.getStatus());
        dto.setId(rack.getId());
        dto.setTeamId(rack.getTeam().getId());

        return dto;

    }

    public static RackDTO toDTOWithTimestamps(Rack rack) {
        RackDTO dto = toDTO(rack);
        dto.setCreatedAt(rack.getCreatedAt());
        dto.setModifiedAt(rack.getModifiedAt());

        return dto;
    }

    public static Rack toRack(RackDTO dto) {
        Rack rack = new Rack();
        rack.setSerialNumber(dto.getSerialNumber());
        rack.setLocation(dto.getLocation());
        rack.setStatus(dto.getStatus());

        return rack;
    }
}
