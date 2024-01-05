package org.todotrack.resource;

import io.dropwizard.hibernate.UnitOfWork;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import org.todotrack.dao.TodoEntityDAO;
import org.todotrack.entity.TodoEntity;

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
}
