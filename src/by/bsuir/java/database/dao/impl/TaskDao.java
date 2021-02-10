package by.bsuir.java.database.dao.impl;

import by.bsuir.java.database.dao.Dao;
import by.bsuir.java.database.manager.DatabaseManager;
import by.bsuir.java.model.Task;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDao implements Dao<Task> {
    private DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();

    private final String SELECT_ALL_TASKS = "select * from vdishptwo.task";
    private final String INSERT_TASK = "insert into vdishptwo.task(id, name, startDate, endDate, projectID, employeeID) values (?,?,?,?,?,?)";
    private final String DELETE_TASK = "delete from vdishptwo.task where id = ? ";
    private final String UPDATE_TASK = "UPDATE vdishptwo.task SET name = ?,startDate = ?,endDate = ?, projectID = ?, employeeID = ? WHERE id = ?";
    private final String SELECT_TASK = "select * from vdishptwo.task WHERE id = ?";

    private final String ID = "id";
    private final String NAME = "name";
    private final String START_DATE = "startDate";
    private final String END_DATE = "endDate";
    private final String PROJECT_ID = "projectID";
    private final String EMPLOYEE_ID = "employeeID";

    @Override
    public List<Task> getAll() {
        Connection connection = null;
        List<Task> tasks = null;
        ResultSet resultSet = null;
        try {
            connection = databaseManager.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_TASKS);
            tasks = new ArrayList<>();
            while (resultSet.next()) {
                tasks.add(new Task(resultSet.getLong(ID),resultSet.getString(NAME), resultSet.getTimestamp(START_DATE),
                        resultSet.getTimestamp(END_DATE), resultSet.getInt(PROJECT_ID),resultSet.getInt(EMPLOYEE_ID)));
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return tasks;
    }

    @Override
    public void save(Task task)
    {
        Connection connection = null;
        try
        {
            connection = databaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK);
            preparedStatement.setLong(1, task.getId());
            preparedStatement.setString(2, task.getName());
            preparedStatement.setTimestamp(3, task.getStartDate());
            preparedStatement.setTimestamp(4,task.getEndDate());
            preparedStatement.setLong(5,task.getProjectID());
            preparedStatement.setLong(6,task.getEmployeeID());
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        } finally
        {
            try
            {
                if (connection != null)
                {
                    connection.close();
                }
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void delete(long id){
        Connection connection = null;
        try {
            connection = databaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TASK);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        } finally
        {
            try
            {
                if (connection != null)
                {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void update(Task task){
        Connection connection = null;
        try {
            connection = databaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASK);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setTimestamp(2, task.getStartDate());
            preparedStatement.setTimestamp(3,task.getEndDate());
            preparedStatement.setLong(4, task.getProjectID());
            preparedStatement.setLong(5, task.getEmployeeID());
            preparedStatement.setLong(6,task.getId());
            preparedStatement.executeUpdate();


        } catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        } finally {
            try
            {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public Task get(long id){
        Connection connection = null;
        Task task = null;
        ResultSet resultSet = null;
        try {
            connection = databaseManager.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                task = new Task(resultSet.getLong(ID),resultSet.getString(NAME), resultSet.getTimestamp(START_DATE),
                        resultSet.getTimestamp(END_DATE), resultSet.getInt(PROJECT_ID),resultSet.getInt(EMPLOYEE_ID));
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return task;
    }
}
