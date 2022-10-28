package sql.demo.config;

import sql.demo.dataBases.DBMyCats;
import sql.demo.dataBases.DataBase;

import java.sql.*;

public class SQLiteConnectionConfiguration implements DBConnection {

    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    String DB_URL;

    public static final String DB_Driver = "org.sqlite.JDBC";

    public SQLiteConnectionConfiguration(DataBase dataBase) {
        this.DB_URL = DataBase.DB_URL;
    }

    @Override
    public Connection open() throws SQLException, ClassNotFoundException {
        Class.forName(DB_Driver);
        return DriverManager.getConnection(DB_URL);
    }

    @Override
    public void close() throws SQLException {
        connection.close();
        resultSet.close();
        preparedStatement.close();
    }
}
