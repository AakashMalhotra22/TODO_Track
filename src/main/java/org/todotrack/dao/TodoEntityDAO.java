package org.todotrack.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.todotrack.entity.TodoEntity;

public class TodoEntityDAO extends AbstractDAO<TodoEntity> {
    public TodoEntityDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public TodoEntity findById(Long id) {
        return get(id);
    }

    public TodoEntity create(TodoEntity entity) {
        return persist(entity);
    }
}
