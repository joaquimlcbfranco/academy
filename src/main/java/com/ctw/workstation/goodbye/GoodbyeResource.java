package com.ctw.workstation.goodbye;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/goodbye")
public class GoodbyeResource {

    @Inject
    GoodbyeConfig goodbyeConfig;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String goodbye(@QueryParam("name") String name) {
        String message = goodbyeConfig.message();
        if (name == null) {
            return message;
        }
        return message + " " + name;
    }

}
