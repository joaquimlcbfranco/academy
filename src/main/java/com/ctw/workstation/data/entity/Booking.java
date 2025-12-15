package com.ctw.workstation.data.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a booking made by a team for a rack
 */

@Entity
@Table(name = "t_booking")
public class Booking extends BaseEntity {

    // Mandatory fields, from and to are the time slots for which the rack is being booked
    @Column(name = "book_from")
    private LocalDateTime from;
    @Column(name = "book_to")
    private LocalDateTime to;
    // Reference to the rack that is being booked (Rack entity)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rack_id")
    private Rack rack;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id")
    private TeamMember requester;

    public Booking() {}

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public TeamMember getRequester() {
        return this.requester;
    }

    public Rack getRack() {
        return this.rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public void setRequester(TeamMember requester) {
        this.requester = requester;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id && Objects.equals(from, booking.from) && Objects.equals(to, booking.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to);
    }

    @Override
    public String toString() {
        return "Booking {" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                '}';
    }


}
