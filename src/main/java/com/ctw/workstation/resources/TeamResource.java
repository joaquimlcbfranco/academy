package com.ctw.workstation.resources;

import com.ctw.workstation.data.entity.Team;
import com.ctw.workstation.dto.TeamDTO;
import com.ctw.workstation.exception.CustomError;
import com.ctw.workstation.exception.EntityNotFoundException;
import com.ctw.workstation.mapper.TeamMapper;
import com.ctw.workstation.service.TeamService;
import io.quarkus.logging.Log;
import io.vertx.codegen.annotations.Unstable;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.jboss.logging.MDC;

import javax.naming.directory.InvalidAttributesException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/teams")
public class TeamResource {

    @Inject
    TeamService teamService;
    @Inject
    Logger logger;

    @Inject
    public TeamResource(TeamService teamService) {
        this.teamService = teamService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeams() {
//        MDC.put("logId", UUID.randomUUID().toString());
//        logger.debugf("Fetching all teams");
        List<TeamDTO> teams = teamService.getTeams();

//        TeamDTO teamDTO = teams.stream().findAny().orElseThrow();
//        logger.debugf("Found team with id: %s", teamDTO.getId());

        return Response
                .ok(teams)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getTeam(@PathParam("id") long id) {
//        logger.debug("Fetching team with id " + id);
        try {
            teamService.getTeamById(id);
            return Response
                    .status(Response.Status.OK)
                    .entity(teamService.getTeamById(id))
                    .build();
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();

        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTeam(TeamDTO teamDTO) {
//        logger.info("Creating team");
        try {
            TeamDTO addedTeam =  teamService.addTeam(teamDTO);
            return Response.created(URI.create("/teams/" + addedTeam.getId())).entity(addedTeam).build();

        } catch (InvalidAttributesException e) {
            CustomError error = new CustomError(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateTeam(@PathParam("id") Long id, TeamDTO teamDTO) {
        try {
            TeamDTO updated = teamService.updateTeam(id, teamDTO);

            return Response
                    .status(Response.Status.CREATED)
                    .entity(updated)
                    .build();

        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteTeam(@PathParam("id") Long id) {
        try {
            teamService.deleteTeam(id);
            return Response
                    .status(Response.Status.OK)
                    .build();

        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAllTeams() {
        teamService.deleteAllTeams();
        return Response
                .status(Response.Status.OK)
                .build();
    }


}
