package sql.demo.DAO;

import sql.demo.config.DBConnection;
import sql.demo.dataForTables.*;
import sql.demo.entity.*;

import java.sql.SQLException;

public class CatDAO extends BaseTableDAO {

    CatTypeDAO catTypeDAO;

    public CatDAO() {
        super("cats");
    }

    public void createTable(DBConnection dbConnection) throws SQLException {
        connection = dbConnection.open();
        sql = "CREATE TABLE if not exists " + super.tableName +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "name VARCHAR(20) NOT NULL, " +
                "typeId INTEGER REFERENCES types (id) NOT NULL, " +
                "age INTEGER NOT NULL, " +
                "weight DOUBLE)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        dbConnection.close();
    }

    public void insertCat(String name, String type, int age, double weight, DBConnection dbConnection) throws SQLException {
        Cat cat = new Cat(name, age, weight);
        CatType catType = new CatType(type);
        catTypeDAO = new CatTypeDAO();
        catTypeDAO.addData(catType, dbConnection);
        cat.setTypeId(catTypeDAO.getId(type, dbConnection));
        addData(cat, dbConnection);
    }

    public boolean isExistsInDB(BaseModel baseModel, DBConnection dbConnection) throws SQLException {
        Cat cat = (Cat) baseModel;
        String name = cat.getName();
        int age = cat.getAge();
        double weight = cat.getWeight();
        int typeId = cat.getTypeId();
        connection = dbConnection.open();
        sql = "SELECT * FROM cats WHERE name = ? AND age = ? AND weight = ? AND typeId = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, age);
        preparedStatement.setDouble(3, weight);
        preparedStatement.setInt(4, typeId);
        resultSet = preparedStatement.executeQuery();
        boolean isExistsInDB = resultSet.next();
        dbConnection.close();
        return isExistsInDB;
    }

    public void addData(BaseModel baseModel, DBConnection dbConnection) throws SQLException {
        Cat cat = (Cat) baseModel;
        if (!isExistsInDB(cat, dbConnection)) {
            connection = dbConnection.open();
            String name = cat.getName();
            int age = cat.getAge();
            double weight = cat.getWeight();
            int typeId = cat.getTypeId();
            sql = "INSERT INTO cats (name, typeId, age, weight) VALUES (?, ?, ?, ?); ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, typeId);
            preparedStatement.setInt(3, age);
            preparedStatement.setDouble(4, weight);
            preparedStatement.executeUpdate();
        } else {
            System.out.println("This data already exist in database");
        }
        dbConnection.close();
    }

    public void addMoreCats(int n, DBConnection dbConnection) throws SQLException {
        while (n >= 0) {
            double randomNumber = Math.random();
            String name = DataForCats.getRandom();
            int age = (int) (randomNumber * 25);
            double weight = Math.ceil(randomNumber * 8 * 100) / 100;
            String type = DataForCatTypes.getRandom();
            insertCat(name, type, age, weight, dbConnection);
            n -= 1;
        }
    }

    public void deleteData(int id, DBConnection dbConnection) throws SQLException {
        String where = "id = " + id;
        deleteData(where, dbConnection);
    }

    public void deleteData(String where, DBConnection dbConnection) throws SQLException {
        connection = dbConnection.open();
        sql = "DELETE FROM cats WHERE " + where;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        dbConnection.close();
    }

    public void updateData(int id, String set, String where, DBConnection dbConnection) throws SQLException {
        connection = dbConnection.open();
        sql = "UPDATE cats SET " + set + " WHERE " + where;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        dbConnection.close();
    }

    public Cat getCat(int id, DBConnection dbConnection) throws SQLException {
        connection = dbConnection.open();
        sql = "SELECT * FROM cats WHERE id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        String name = resultSet.getString("name");
        int typeId = resultSet.getInt("typeId");
        int age = resultSet.getInt("age");
        double weight = resultSet.getDouble("weight");
        Cat cat = new Cat(name, typeId, age, weight);
        dbConnection.close();
        return cat;
    }

    public void getCatWhere(String where, DBConnection dbConnection) throws SQLException {
        connection = dbConnection.open();
        sql = "SELECT * FROM cats WHERE " + where;
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int typeId = resultSet.getInt("typeId");
            int age = resultSet.getInt("age");
            double weight = resultSet.getDouble("weight");
            Cat cat = new Cat(name, typeId, age, weight);
            System.out.println(cat);
        }
        dbConnection.close();
    }

    public void getAllCats(DBConnection dbConnection) throws SQLException {
        connection = dbConnection.open();
        sql = "SELECT * FROM cats";
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int typeId = resultSet.getInt("typeId");
            int age = resultSet.getInt("age");
            double weight = resultSet.getDouble("weight");
            Cat cat = new Cat(name, typeId, age, weight);
            System.out.println(cat);
        }
        dbConnection.close();
    }
}
