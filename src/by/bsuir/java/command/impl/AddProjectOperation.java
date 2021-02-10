package by.bsuir.java.command.impl;

import by.bsuir.java.command.AddingOperation;
import by.bsuir.java.database.dao.impl.ProjectDao;
import by.bsuir.java.model.Project;

public class AddProjectOperation implements AddingOperation<Project> {
    @Override
    public void execute(Project project) {
            new ProjectDao().save(project);
    }
}
