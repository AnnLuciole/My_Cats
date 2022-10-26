package sql.demo.repository;

import sql.demo.StockExchangeDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cats extends BaseTable{

    String sql;
    PreparedStatement preparedStatement;

    public Cats() throws SQLException {
        super("cats");
    }

    @Override
    public void createTable() throws SQLException {
        sql = "CREATE TABLE if not exists " + super.tableName +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "name VARCHAR(20) NOT NULL, " +
                "typeId INTEGER REFERENCES types (id) NOT NULL, " +
                "age INTEGER NOT NULL, " +
                "weight DOUBLE)";
        Connection connection = StockExchangeDB.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }
}
