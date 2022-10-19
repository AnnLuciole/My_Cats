package sql.demo;

import java.sql.*;

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


    public static void main(String[] args) {
        try{
            StockExchangeDB stockExchangeDB = new StockExchangeDB();
            stockExchangeDB.createTables();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC драйвер для СУБД не найден!");
        }
    }
}