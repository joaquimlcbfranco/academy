package com.ctw.workstation.resources;


import com.ctw.workstation.dto.TeamMemberDTO;
import com.ctw.workstation.exception.EntityNotFoundException;
import com.ctw.workstation.service.TeamMemberService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/members")
public class TeamMemberResource {

    @Inject
    TeamMemberService teamMemberService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeamMembers() {
        List<TeamMemberDTO> members = teamMemberService.getTeamMembers();
        return Response
                .ok(members)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getTeamMember(@PathParam("id") Long id) {
        try {
            TeamMemberDTO found = teamMemberService.getTeamMemberById(id);
            return Response
                    .ok(found)
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
    public Response createTeamMember(TeamMemberDTO teamMemberDTO) {
        try {
            TeamMemberDTO created = teamMemberService.createTeamMember(teamMemberDTO);
            return Response
                    .created(URI.create("/members/" + created.getId()))
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
    @Path("/{id}")
    public Response updateTeamMember(@PathParam("id") Long id, TeamMemberDTO teamMemberDTO) {
        try {
            TeamMemberDTO updated = teamMemberService.updateTeamMember(id, teamMemberDTO);
            return Response
                    .ok(URI.create("/members/" + updated.getId()))
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
    public Response deleteTeamMember(@PathParam("id") Long id) {
        try {
            teamMemberService.deleteTeamMember(id);
            return Response
                    .ok()
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
    public Response deleteAllTeamMembers() {
        teamMemberService.deleteAllTeamMembers();
        return Response
                .ok()
                .build();
    }
}
