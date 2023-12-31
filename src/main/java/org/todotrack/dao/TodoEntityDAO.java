package org.todotrack.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.todotrack.entity.TodoEntity;

public class TodoEntityDAO extends AbstractDAO<TodoEntity> {
    public TodoEntityDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
