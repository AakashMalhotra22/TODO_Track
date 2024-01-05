package org.todotrack.dao;

import io.dropwizard.hibernate.AbstractDAO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.todotrack.entity.TodoEntity;

import java.util.ArrayList;
import java.util.List;

import java.util.UUID;

public class TodoEntityDAO extends AbstractDAO<TodoEntity> {

    private final SessionFactory sessionFactory;
    public TodoEntityDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public TodoEntity findById(Integer id) {
        return get(id);
    }

    public List<TodoEntity> findAll() {
        try(Session session = sessionFactory.openSession())
        {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TodoEntity> criteria = builder.createQuery(TodoEntity.class);

            criteria.from(TodoEntity.class);
            return session.createQuery(criteria).getResultList();
        }catch (Exception e){
            return new ArrayList<>();
        }
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
    public void delete(Integer taskId) {
        TodoEntity entity = get(taskId);
        if (entity != null) {
            currentSession().delete(entity);
        }
    }
}