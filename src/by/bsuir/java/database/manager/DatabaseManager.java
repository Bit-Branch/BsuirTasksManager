package by.bsuir.java.database.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseManager {

    private Connection connection = null;
    private static DatabaseManager databaseManager = null;

    private DatabaseManager() {
        try {
            connection = this.getConnection();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Can't connect to database");
            e.printStackTrace();
            return;
        }
        if (connection != null) {
            System.out.println("Connected to database");
        } else {
            System.out.println("Can't connect to database, probably, there is no database");
        }
    }

    public static DatabaseManager getDatabaseManager()
    {
        if(databaseManager == null)
        {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public void disconnectDatabase() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public synchronized Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost/";
        Properties p =new Properties();
        p.setProperty("user","root");
        p.setProperty("password","8337");
        p.setProperty("useUnicode","true");
        p.setProperty("characterEncoding","cp1251");
        p.setProperty("autoReconnect","true");
        p.setProperty("useSSL","false");

        Connection connection = DriverManager.getConnection(url,p);
        return connection;
    }
}
