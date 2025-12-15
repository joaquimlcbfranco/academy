package com.ctw.workstation.resources;

import com.ctw.workstation.data.entity.Booking;
import com.ctw.workstation.dto.BookingDTO;
import com.ctw.workstation.exception.EntityNotFoundException;
import com.ctw.workstation.service.BookingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/bookings")
public class BookingResource {

    @Inject
    BookingService bookingService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookings() {
        List<BookingDTO> bookings = bookingService.getBookings();
        return Response
                .status(Response.Status.OK)
                .entity(bookings)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getBooking(@PathParam("id") long id) {
        try {
            BookingDTO foundDTO = bookingService.getBookingById(id);
            return Response
                    .status(Response.Status.OK)
                    .entity(foundDTO)
                    .build();

        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBooking(BookingDTO bookingDTO) {
        try {
            BookingDTO createdDTO = bookingService.createBooking(bookingDTO);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(bookingDTO)
                    .build();

        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateBooking(@PathParam("id") Long id, BookingDTO bookingDTO) {
        try {
            BookingDTO updated = bookingService.updateBooking(id, bookingDTO);
            return Response
                    .status(Response.Status.OK)
                    .entity(updated)
                    .build();

        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteBooking(@PathParam("id") Long id) {
        try {
            bookingService.deleteBooking(id);
            return Response
                    .status(Response.Status.OK)
                    .build();
        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Produces
    public Response deleteAllBookings() {
        bookingService.deleteAllBookings();
        return Response
                .status(Response.Status.OK)
                .build();
    }
}

