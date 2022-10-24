package sql.demo.repository;

import sql.demo.StockExchangeDB;
import sql.demo.model.BaseModel;
import sql.demo.model.CatType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CatTypes extends BaseTable implements TableOperations{

    String sql;
    PreparedStatement preparedStatement;

    public CatTypes() throws SQLException {
        super("types");
    }

    @Override
    public void createTable() throws SQLException {
        sql = "CREATE TABLE if not exists " + super.tableName +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "type VARCHAR(100) NOT NULL)";
        Connection connection = StockExchangeDB.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        super.executeSqlStatement(preparedStatement);
    }

    @Override
    public void addData(BaseModel baseModel) throws SQLException {
        CatType type = (CatType) baseModel;
        super.reopenConnection();
        if (!isExistsInDB(type)) {
            String typeOfCat = type.getType();
            sql = "INSERT INTO types (type) VALUES (?); ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, typeOfCat);
            super.executeSqlStatement(preparedStatement);
        } else {
            System.out.println("Данные уже существуют в БД");
        }
    }

    @Override
    public boolean isExistsInDB(BaseModel baseModel) throws SQLException {
        CatType type = (CatType) baseModel;
        String typeOfCat = type.getType();
        reopenConnection();
        sql = "SELECT * FROM types WHERE type = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, typeOfCat);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
}
