package org.todotrack;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import org.todotrack.config.TodoConfiguration;
import org.todotrack.dao.TodoEntityDAO;
import org.todotrack.entity.TodoEntity;
import org.todotrack.resource.TodoResource;

public class ToDoTrack extends Application<TodoConfiguration> {
    public static void main(String[] args) throws Exception {
        System.out.println("hi");
        new ToDoTrack().run(args);
    }

    HibernateBundle<TodoConfiguration> hibernateBundle = new HibernateBundle<TodoConfiguration>(TodoEntity.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(TodoConfiguration todoConfiguration) {
            return todoConfiguration.getDataSourceFactory();
        }
    };
    @Override
    public void initialize(Bootstrap<TodoConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(TodoConfiguration configuration, Environment environment) throws Exception
    {
        TodoEntityDAO todoEntityDAO = new TodoEntityDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new TodoResource(todoEntityDAO));
    }
}
