package by.bsuir.java.database.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    List<?> getAll();

    void save(T t) throws SQLException, ClassNotFoundException;

    void update(T t) throws SQLException, ClassNotFoundException;

    void delete(long id) throws SQLException, ClassNotFoundException;

    T get(long id);
}
