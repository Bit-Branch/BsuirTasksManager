package by.bsuir.java.model;


//Wrapper class for displaying info about employee, project,task entries in javafx table
public class Wrapper {
    private Employee employee;
    private Project project;
    private Task task;

    public Wrapper() {
    }

    public Wrapper(Employee employee, Project project, Task task) {
        this.employee = employee;
        this.project = project;
        this.task = task;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
