package com.ctw.workstation.dto;

import java.time.LocalDateTime;

public class BookingDTO {

    public Long id;
    public LocalDateTime from;
    public LocalDateTime to;
    public Long rackId;
    public Long requesterId;

    public BookingDTO(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

}
