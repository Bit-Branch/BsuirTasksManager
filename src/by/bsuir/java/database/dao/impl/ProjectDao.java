package by.bsuir.java.database.dao.impl;

import by.bsuir.java.database.dao.Dao;
import by.bsuir.java.database.manager.DatabaseManager;
import by.bsuir.java.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao implements Dao<Project> {

    private DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();

    private final String SELECT_ALL_PROJECTS = "select * from vdishptwo.project";
    private final String INSERT_PROJECT = "insert into vdishptwo.project(id,name) values (?,?)";
    private final String DELETE_PROJECT = "delete from vdishptwo.project where id = ? ";
    private final String UPDATE_PROJECT = "UPDATE vdishptwo.project SET name = ? WHERE id = ?";
    private final String SELECT_PROJECT = "select * from vdishptwo.project WHERE id = ?";

    private final String ID = "id";
    private final String NAME = "name";

    @Override
    public List<Project> getAll() {

        Connection connection = null;
        List<Project> projects = null;
        ResultSet resultSet = null;
        try
        {
            connection = databaseManager.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_PROJECTS);
            projects = new ArrayList<>();

            while (resultSet.next())
            {
                projects.add(new Project(resultSet.getInt(ID),resultSet.getString(NAME)));
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
        return projects;
    }

    @Override
    public void save(Project project){
        Connection connection = null;
        try {
            connection = databaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJECT);
            preparedStatement.setLong(1, project.getId());
            preparedStatement.setString(2, project.getName());
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
    public void delete(long id){
        Connection connection = null;
        try {
            connection = databaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROJECT);
            preparedStatement.setLong(1,id);
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
    public void update(Project project){
        Connection connection = null;
        try {
            connection = databaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROJECT);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setLong(2,project.getId());
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
    public Project get(long id){
        Connection connection = null;
        Project project = null;
        ResultSet resultSet = null;
        try {
            connection = databaseManager.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECT);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                project = new Project(resultSet.getLong(ID), resultSet.getString(NAME));
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
        return project;
    }
}
