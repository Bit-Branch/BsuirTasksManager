package by.bsuir.java.database.dao.impl;


import by.bsuir.java.database.dao.Dao;
import by.bsuir.java.database.manager.DatabaseManager;
import by.bsuir.java.model.Employee;
import by.bsuir.java.model.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements Dao<Employee> {

    private DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();

    private final String SELECT_ALL_EMPLOYEES = "select * from vdishptwo.employee";
    private final String INSERT_EMPLOYEE = "insert into vdishptwo.employee(id,surname,name,position,laborInput) values (?,?,?,?,?)";
    private final String DELETE_EMPLOYEE = "delete from vdishptwo.employee where id = ? ";
    private final String UPDATE_EMPLOYEE = "UPDATE vdishptwo.employee SET surname = ?,name = ?,position = ?, laborInput = ? WHERE id = ?";
    private final String SELECT_EMPLOYEE = "select * from vdishptwo.employee WHERE id = ?";

    private final String ID = "id";
    private final String NAME = "name";
    private final String SURNAME = "surname";
    private final String POSITION = "position";
    private final String LABOR_INPUT = "laborInput";

    @Override
    public List<Employee> getAll() {
        Connection connection = null;
        List<Employee> employees = null;
        ResultSet resultSet = null;
        try {
            connection = databaseManager.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_EMPLOYEES);

            employees = new ArrayList<>();
            while (resultSet.next())
            {
                employees.add(new Employee(resultSet.getInt(ID),resultSet.getString(SURNAME),
                        resultSet.getString(NAME), Position.valueOf(resultSet.getString(POSITION)),
                        resultSet.getDouble(LABOR_INPUT)));
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
        return employees;
    }

    @Override
    public void save(Employee employee)
    {
        Connection connection = null;
        try {
            connection = databaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE);
            preparedStatement.setLong(1, employee.getId());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getName());
            preparedStatement.setString(4,employee.getPosition().toString());
            preparedStatement.setDouble(5,employee.getLaborInput());
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE);
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
    public void update(Employee employee){
        Connection connection = null;
        try {
            connection = databaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE);
            preparedStatement.setString(1, employee.getSurname());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3,employee.getPosition().toString());
            preparedStatement.setDouble(4, employee.getLaborInput());
            preparedStatement.setLong(5,employee.getId());
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
    public Employee get(long id){
        Connection connection = null;
        Employee employee = null;
        ResultSet resultSet = null;
        try {

            connection = databaseManager.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                employee = new Employee(resultSet.getInt(ID),resultSet.getString(SURNAME),
                        resultSet.getString(NAME), Position.valueOf(resultSet.getString(POSITION)),
                        resultSet.getDouble(LABOR_INPUT));
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }finally
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
        return employee;
    }
}
