package com.ctw.workstation.data.repository;

import com.ctw.workstation.data.entity.Booking;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * BookingDatabase to store the booking data
 */
@ApplicationScoped
public final class BookingRepository implements PanacheRepository<Booking> {
}