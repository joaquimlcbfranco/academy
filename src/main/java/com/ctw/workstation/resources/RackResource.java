package com.ctw.workstation.resources;

import com.ctw.workstation.dto.RackDTO;
import com.ctw.workstation.exception.EntityNotFoundException;
import com.ctw.workstation.service.RackService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/racks")

public class RackResource {

    @Inject
    RackService rackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRacks() {
        List<RackDTO> racks = rackService.getRacks();
        return Response
                .ok(racks)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getRack(@PathParam("id") Long id) {
        try {
            RackDTO foundRack = rackService.getRackById(id);
            return Response
                    .ok(foundRack)
                    .build();

        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/teams/{teamId}")

    public Response getRacksByTeamId(@PathParam("teamId") Long teamId) {
        try {
            List<RackDTO> foundRacks = rackService.getRacksByTeamId(teamId);
            return Response
                    .ok(foundRacks)
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
    public Response createRack(RackDTO dto) {
        try {
            RackDTO created = rackService.createRack(dto);
            return Response
                    .created(URI.create("/racks/" + created.getId()))
                    .entity(created)
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
    public Response updateRack(@PathParam("id") Long id, RackDTO dto) {
        try {
            RackDTO updated = rackService.updateRack(id, dto);
            return Response
                    .ok(URI.create("/racks" + updated.getId()))
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
    public Response deleteRack(@PathParam("id") Long id) {
        try {
            rackService.deleteRack(id);
            return Response.noContent().build();
        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }

}
