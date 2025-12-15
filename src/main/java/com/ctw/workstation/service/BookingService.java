package com.ctw.workstation.service;

import com.ctw.workstation.data.entity.Booking;
import com.ctw.workstation.data.entity.Rack;
import com.ctw.workstation.data.entity.Team;
import com.ctw.workstation.data.entity.TeamMember;
import com.ctw.workstation.data.repository.BookingRepository;
import com.ctw.workstation.data.repository.RackRepository;
import com.ctw.workstation.data.repository.TeamMemberRepository;
import com.ctw.workstation.data.repository.TeamRepository;
import com.ctw.workstation.dto.BookingDTO;
import com.ctw.workstation.exception.EntityNotFoundException;
import com.ctw.workstation.mapper.BookingMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.List;

@Singleton
public class BookingService {

    @Inject
    BookingRepository bookingRepository;
    @Inject
    RackRepository rackRepository;
    @Inject
    TeamMemberRepository teamMemberRepository;

    public List<BookingDTO> getBookings() {
        return bookingRepository
                .findAll()
                .stream()
                .map(entity -> BookingMapper.toDTO(entity))
                .toList();
    }

    public BookingDTO getBookingById(long id) throws EntityNotFoundException {
        Booking foundBooking = bookingRepository.findById(id);
        if (foundBooking == null) {
            throw new EntityNotFoundException("Booking with id " + id + " not found");
        }

        return BookingMapper.toDTO(foundBooking);
    }

    @Transactional
    public BookingDTO createBooking(BookingDTO dto) throws EntityNotFoundException {
        Rack foundRack = rackRepository.findById(dto.rackId);
        if (foundRack == null) {
            throw new EntityNotFoundException("Rack with id " + dto.rackId + " not found");
        }

        TeamMember foundMember = teamMemberRepository.findById(dto.requesterId);
        if (foundMember == null) {
            throw new EntityNotFoundException("Team member with id " + dto.requesterId + " not found");
        }

        Booking booking = BookingMapper.toEntity(dto);
        booking.setRack(foundRack);
        booking.setRequester(foundMember);
        bookingRepository.persist(booking);

        return BookingMapper.toDTO(booking);
    }

    @Transactional
    public BookingDTO updateBooking(Long id, BookingDTO dto) throws EntityNotFoundException {
        Booking foundBooking = bookingRepository.findById(dto.id);
        if (foundBooking == null) {
            throw new EntityNotFoundException("Booking with id " + dto.id + " not found");
        }

        Rack foundRack = rackRepository.findById(dto.rackId);
        if (foundRack == null) {
            throw new EntityNotFoundException("Rack with id " + dto.rackId + " not found");
        }

        TeamMember foundMember = teamMemberRepository.findById(dto.requesterId);
        if (foundMember == null) {
            throw new EntityNotFoundException("Team member with id " + dto.requesterId + " not found");
        }

        foundBooking.setFrom(dto.from);
        foundBooking.setTo(dto.to);
        foundBooking.setRack(foundRack);
        foundBooking.setRequester(foundMember);

        return BookingMapper.toDTO(foundBooking);

    }

    @Transactional
    public void deleteBooking(Long id) throws EntityNotFoundException {
        Booking foundBooking = bookingRepository.findById(id);
        if (foundBooking == null) {
            throw new EntityNotFoundException("Booking with id " + id + " not found");
        }
    }

    @Transactional
    public void deleteAllBookings() {
        bookingRepository.deleteAll();
    }


}
