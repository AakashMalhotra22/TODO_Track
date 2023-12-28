package org.todotrack.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Date;

@Path("/api")
public class TodoResource
{
    @GET
    @Path("/healthCheck")
    public String healthCheck()
    {
        return "Hi date is " + new Date();
    }
}
