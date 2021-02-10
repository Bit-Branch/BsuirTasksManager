package by.bsuir.java.model;

import java.sql.Timestamp;

public class Task {
    private long id;
    private String name;
    private Timestamp startDate;
    private Timestamp endDate;
    private long projectID;
    private long employeeID;

    public Task() {
    }

    public Task(long id, String name, Timestamp startDate, Timestamp endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Task(String name, Timestamp startDate, Timestamp endDate, long projectID) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectID = projectID;
    }

    public Task(String name, Timestamp startDate, Timestamp endDate, long projectID, long employeeID) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectID = projectID;
        this.employeeID = employeeID;
    }

    public Task(long id, String name, Timestamp startDate, Timestamp endDate, long projectID) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectID = projectID;
    }

    public Task(long id,String name, Timestamp startDate, Timestamp endDate, long projectID, long employeeID) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectID = projectID;
        this.employeeID = employeeID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public long getProjectID() {
        return projectID;
    }

    public void setProjectID(long projectID) {
        this.projectID = projectID;
    }

    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return id == task.getId() &&
                name.equals(task.getName()) &&
                startDate.equals(task.getStartDate()) &&
                endDate.equals(task.getEndDate()) &&
                projectID == task.getProjectID() &&
                employeeID == task.getEmployeeID();
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (int) (projectID ^ (projectID >>> 32));
        result = 31 * result + (int) (employeeID ^ (employeeID >>> 32));
        return result;
    }
}
