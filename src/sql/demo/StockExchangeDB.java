package sql.demo;

import java.sql.*;

import sql.demo.dataForTables.DataForCatTypes;
import sql.demo.model.BaseModel;
import sql.demo.model.CatType;
import sql.demo.repository.*;

public class StockExchangeDB {
    // Блок объявления констант

    public static Connection connection;
    public static final String DB_URL = "jdbc:sqlite:/C:/Users/Manager/IdeaProjects/Cats/Cats/db/My_cats.db";
    public static final String DB_Driver = "org.sqlite.JDBC";

    CatTypes types;

    // Получить новое соединение с БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Инициализация
    public StockExchangeDB() throws SQLException, ClassNotFoundException {
        Class.forName(DB_Driver);
        connection = StockExchangeDB.getConnection();
        // Инициализируем таблицы
        types = new CatTypes();
    }

    // Создание всех таблиц и ключей между ними
    public void createTables() throws SQLException {
        types.createTable();
    }

    public void addDataInTables() throws SQLException {
        BaseModel baseModel = new CatType("Абиссинская кошка");
        types.addData(baseModel);
        baseModel = new CatType("Австралийский мист");
        types.addData(baseModel);
        baseModel = new CatType("Американская жесткошерстная");
        types.addData(baseModel);
        types.addAllTypes(DataForCatTypes.getTypes());
    }

    public void deleteDataFromTables() throws SQLException {
        types.deleteData(54);
    }

    public void updateDataInTables() throws SQLException {
        types.updateData(7, "Дворовая кошка");
    }

    public void getDataFromTables() throws SQLException {
        System.out.println(types.getType(10));
        types.getTypeWhere("id < 15");
        types.getAllTypes();
    }


    public static void main(String[] args) {
        try{
            StockExchangeDB stockExchangeDB = new StockExchangeDB();
            //stockExchangeDB.createTables();
            //stockExchangeDB.addDataInTables();
            //stockExchangeDB.deleteDataFromTables();
            //stockExchangeDB.updateDataInTables();
            stockExchangeDB.getDataFromTables();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error of SQL!");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC not found!");
        }
    }
}