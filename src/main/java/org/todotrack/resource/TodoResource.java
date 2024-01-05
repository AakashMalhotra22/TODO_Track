package org.todotrack.resource;

import io.dropwizard.hibernate.UnitOfWork;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.todotrack.dao.TodoEntityDAO;
import org.todotrack.entity.TodoEntity;

import java.util.Date;
import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource
{
    private final TodoEntityDAO todoEntityDAO;

    public TodoResource(TodoEntityDAO todoEntityDAO) {
        this.todoEntityDAO = todoEntityDAO;
    }


    @GET
    @Path("/getTask/{taskId}")
    @UnitOfWork
    public Response getTask(@PathParam("taskId") Integer taskId) {
        TodoEntity task = todoEntityDAO.findById(taskId);
        if (task == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Task with ID " + taskId + " not found")
                    .build();
        }
        return Response.ok().entity(task).build();
    }

    @GET
    @Path("/allTasks")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TodoEntity> getAllTasks() {
        return todoEntityDAO.findAll();
    }


    @POST
    @Path("/addTask")
    @UnitOfWork
    public String addTask(@Valid TodoEntity todoEntity)
    {
        System.out.println(todoEntity.getTitle());
        return todoEntityDAO.save(todoEntity);
    }

    @PUT
    @Path("/updateTask/{taskId}")
    @UnitOfWork
    public String updateTask(@PathParam("taskId") Integer taskId, @Valid TodoEntity updatedTask) {
        TodoEntity existingTask = todoEntityDAO.findById(taskId);
        if (existingTask == null) {
            throw new NotFoundException("Task with ID " + taskId + " not found");
        }
        System.out.println("welcome to update"+ taskId);

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStartDate(updatedTask.getStartDate());
        existingTask.setTargetDate(updatedTask.getTargetDate());
        existingTask.setStatus(updatedTask.getStatus());

        return todoEntityDAO.update(existingTask);
    }
    @DELETE
    @Path("/deleteTask/{taskId}")
    @UnitOfWork
    public Response deleteTask(@PathParam("taskId") Integer taskId) {
        if (todoEntityDAO.findById(taskId) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Task with ID " + taskId + " not found")
                    .build();
        }

        todoEntityDAO.delete(taskId);
        return Response.ok().entity("Task with ID " + taskId + " deleted").build();
    }
}
