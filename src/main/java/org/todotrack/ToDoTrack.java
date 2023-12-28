package org.todotrack;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Environment;
import org.todotrack.config.TodoConfiguration;

public class ToDoTrack extends Application<TodoConfiguration> {
    public static void main(String[] args) throws Exception {
        System.out.println("hi");
        new ToDoTrack().run(args);
    }

    @Override
    public void run(TodoConfiguration configuration, Environment environment) throws Exception
    {

    }
}
