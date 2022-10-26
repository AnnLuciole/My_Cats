package sql.demo.repository;

import sql.demo.StockExchangeDB;
import sql.demo.model.BaseModel;
import sql.demo.model.CatType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CatTypes extends BaseTable {

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
        preparedStatement.executeUpdate();
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
            preparedStatement.executeUpdate();
        } else {
            System.out.println("This data already exist in database");
        }
    }

    @Override
    public boolean isExistsInDB(BaseModel baseModel) throws SQLException {
        CatType type = (CatType) baseModel;
        String typeOfCat = type.getType();
        super.reopenConnection();
        sql = "SELECT * FROM types WHERE type = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, typeOfCat);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    @Override
    public void addAllTypes(String[] allTypes) throws SQLException {
        for (String type:allTypes) {
            CatType catType = new CatType(type);
            if (!isExistsInDB(catType)) {
                addData(catType);
            }
        }
    }

    @Override
    public void deleteData(int id) throws SQLException {
        reopenConnection();
        sql = "DELETE FROM types WHERE id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateData(int id, String newType) throws SQLException {
        reopenConnection();
        sql = "UPDATE types SET type = ? WHERE id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newType);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
    }

    public String getType(int id) throws SQLException {
        reopenConnection();
        sql = "SELECT type FROM types WHERE id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        return resultSet.getString("type");
    }

    public void getTypeWhere(String where) throws SQLException {
        reopenConnection();
        sql = "SELECT type FROM types WHERE " + where;
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("type"));
        }
    }

    public void getAllTypes() throws SQLException {
        reopenConnection();
        sql = "SELECT type FROM types";
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("type"));
        }
    }
}
