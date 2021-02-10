package by.bsuir.java.command.impl;

import by.bsuir.java.command.AddingOperation;
import by.bsuir.java.database.dao.impl.TaskDao;
import by.bsuir.java.model.Task;

public class AddTaskOperation implements AddingOperation<Task> {
    @Override
    public void execute(Task task) {
        new TaskDao().save(task);
    }
}
