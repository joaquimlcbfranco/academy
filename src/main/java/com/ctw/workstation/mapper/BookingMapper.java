package com.ctw.workstation.mapper;

import com.ctw.workstation.data.entity.Booking;
import com.ctw.workstation.dto.BookingDTO;

public class BookingMapper {

    public static BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO(booking.getFrom(), booking.getTo());
        dto.setId(booking.getId());
        dto.setRackId(booking.getRack().getId());
        dto.setRequesterId(booking.getRequester().getId());

        return dto;
    }

    public static Booking toEntity(BookingDTO dto) {
        Booking booking = new Booking();
        booking.setFrom(dto.from);
        booking.setTo(dto.to);

        return booking;
    }
}
