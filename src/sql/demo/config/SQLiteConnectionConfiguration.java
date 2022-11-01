package sql.demo.config;

import java.sql.*;

public class SQLiteConnectionConfiguration implements DBConnection {

    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    public static final String DB_Driver = "org.sqlite.JDBC";
    public String DB_URL = "jdbc:sqlite:/C:/Users/Manager/IdeaProjects/Cats/Cats/db/My_cats.db";

    @Override
    public Connection open() throws SQLException {
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException e) {
            System.out.println("Classpath not found");
        }
        return connection;
    }

    @Override
    public void close() throws SQLException {
        try {
            connection.close();
            resultSet.close();
            preparedStatement.close();
        } catch (NullPointerException exception) {
            System.out.println("NullPointerException.");
        }
    }
}
