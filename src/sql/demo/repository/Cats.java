package sql.demo.repository;

import sql.demo.StockExchangeDB;
import sql.demo.dataForTables.*;
import sql.demo.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cats extends BaseTable{

    String sql;
    PreparedStatement preparedStatement;

    CatTypes catTypes;

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
        super.close();
    }

    public void insertCat(String name, String type, int age, double weight) throws SQLException {
        Cat cat = new Cat(name, age, weight);
        CatType catType = new CatType(type);
        catTypes = new CatTypes();
        catTypes.addData(catType);
        cat.setTypeId(catTypes.getId(type));
        addData(cat);
    }

    @Override
    public boolean isExistsInDB(BaseModel baseModel) throws SQLException {
        Cat cat = (Cat) baseModel;
        String name = cat.getName();
        int age = cat.getAge();
        double weight = cat.getWeight();
        int typeId = cat.getTypeId();
        super.reopenConnection();
        sql = "SELECT * FROM cats WHERE name = ? AND age = ? AND weight = ? AND typeId = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, age);
        preparedStatement.setDouble(3, weight);
        preparedStatement.setInt(4, typeId);
        resultSet = preparedStatement.executeQuery();
        boolean isExistsInDB = resultSet.next();
        super.close();
        return isExistsInDB;
    }

    @Override
    public void addData(BaseModel baseModel) throws SQLException {
        Cat cat = (Cat) baseModel;
        if (!isExistsInDB(cat)) {
            reopenConnection();
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
        super.close();
    }

    public void addMoreCats(int n) throws SQLException {
        while (n >= 0) {
            double randomNumber = Math.random();
            String name = DataForCats.getRandom();
            int age = (int) (randomNumber * 25);
            double weight = Math.ceil(randomNumber * 8 * 100) / 100;
            String type = DataForCatTypes.getRandom();
            insertCat(name, type, age, weight);
            n -= 1;
        }
    }

    @Override
    public void deleteData(int id) throws SQLException {
        reopenConnection();
        sql = "DELETE FROM cats WHERE id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        super.close();
    }

    @Override
    public void deleteData(String where) throws SQLException {
        reopenConnection();
        sql = "DELETE FROM cats WHERE " + where;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        super.close();
    }

    @Override
    public void updateData(int id, String set, String where) throws SQLException {
        reopenConnection();
        sql = "UPDATE cats SET " + set + " WHERE " + where;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        super.close();
    }

    public Cat getCat(int id) throws SQLException {
        reopenConnection();
        sql = "SELECT * FROM cats WHERE id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        String name = resultSet.getString("name");
        int typeId = resultSet.getInt("typeId");
        int age = resultSet.getInt("age");
        double weight = resultSet.getDouble("weight");
        Cat cat = new Cat(name, typeId, age, weight);
        super.close();
        return cat;
    }

    public void getCatWhere(String where) throws SQLException {
        reopenConnection();
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
        super.close();
    }

    public void getAllCats() throws SQLException {
        reopenConnection();
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
        super.close();
    }
}
