package sql.demo.DAO;

import sql.demo.config.DBConnection;
import sql.demo.entity.*;

import java.sql.Connection;
import java.sql.SQLException;

public class CatTypeDAO extends BaseTableDAO {


    public CatTypeDAO() {
        super("types");
    }

    public void createTable(DBConnection dbConnection) throws SQLException {
        Connection connection = dbConnection.open();
        sql = "CREATE TABLE if not exists " + super.tableName +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "type VARCHAR(100) NOT NULL)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        dbConnection.close();
    }

    public void addData(BaseModel baseModel, DBConnection dbConnection) throws SQLException {
        CatType catType = (CatType) baseModel;
        if (!isExistsInDB(catType, dbConnection)) {
            Connection connection = dbConnection.open();
            String typeOfCat = catType.getType();
            sql = "INSERT INTO types (type) VALUES (?); ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, typeOfCat);
            preparedStatement.executeUpdate();
            dbConnection.close();
        } else {
            System.out.println("This data already exist in database");
        }
    }

    public boolean isExistsInDB(BaseModel baseModel, DBConnection dbConnection) throws SQLException {
        CatType catType = (CatType) baseModel;
        String typeOfCat = catType.getType();
        Connection connection = dbConnection.open();
        sql = "SELECT * FROM types WHERE type = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, typeOfCat);
        resultSet = preparedStatement.executeQuery();
        boolean isExistsInDB = resultSet.next();
        dbConnection.close();
        return isExistsInDB;
    }

    public void addAllTypes(String[] allTypes, DBConnection dbConnection) throws SQLException {
        for (String type:allTypes) {
            CatType catType = new CatType(type);
            if (!isExistsInDB(catType, dbConnection)) {
                addData(catType, dbConnection);
            }
        }
    }

    public void deleteData(int id, DBConnection dbConnection) throws SQLException {
        String where = "id = " + id;
        deleteData(where, dbConnection);
    }

    public void deleteData(String where, DBConnection dbConnection) throws SQLException {
        connection = dbConnection.open();
        sql = "DELETE FROM types WHERE " + where;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        dbConnection.close();
    }

    public void updateData(int id, String newType, DBConnection dbConnection) throws SQLException {
        connection = dbConnection.open();
        sql = "UPDATE types SET type = ? WHERE id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newType);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
        dbConnection.close();
    }

    public void updateData(int id, String set, String where, DBConnection dbConnection) throws SQLException {
        connection = dbConnection.open();
        sql = "UPDATE types SET " + set + " WHERE id = " + id + " AND " + where;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        dbConnection.close();
    }

    public String getType(int id, DBConnection dbConnection) throws SQLException {
        connection = dbConnection.open();
        sql = "SELECT type FROM types WHERE id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        String catType = resultSet.getString("type");
        dbConnection.close();
        return catType;
    }

    public int getId(String type, DBConnection dbConnection) throws SQLException {
        connection = dbConnection.open();
        sql = "SELECT id FROM types WHERE type = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, type);
        resultSet = preparedStatement.executeQuery();
        int id = resultSet.getInt("id");
        dbConnection.close();
        return id;
    }

    public void getTypeWhere(String where, DBConnection dbConnection) throws SQLException {
        connection = dbConnection.open();
        sql = "SELECT type FROM types WHERE " + where;
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("type"));
        }
        dbConnection.close();
    }

    public void getAllTypes(DBConnection dbConnection) throws SQLException {
        connection = dbConnection.open();
        sql = "SELECT type FROM types";
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("type"));
        }
        dbConnection.close();
    }
}
