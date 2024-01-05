package org.todotrack.dao;

import io.dropwizard.hibernate.AbstractDAO;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.todotrack.entity.TodoEntity;

import java.util.UUID;

public class TodoEntityDAO extends AbstractDAO<TodoEntity> {
    public TodoEntityDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public TodoEntity findById(Integer id) {
        return get(id);
    }

    public String save(TodoEntity entity)
    {
        try{
            persist(entity);
            return Response.ok().toString();
        } catch (Exception exception) {
            return Response.serverError().toString();
        }
    }
    public String update(TodoEntity entity) {
        try{
            persist(entity);
            return Response.ok().toString();
        } catch (Exception exception) {
            return Response.serverError().toString();
        }
    }
}