package org.todotrack.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.todotrack.dao.TodoEntityDAO;

import java.util.Date;

@Path("/api")
public class TodoResource
{
    private final TodoEntityDAO todoEntityDAO;

    public TodoResource(TodoEntityDAO todoEntityDAO) {
        this.todoEntityDAO = todoEntityDAO;
    }

    @GET
    @Path("/healthCheck")
    public String healthCheck()
    {
        return "Hi date is " + new Date();
    }
}
