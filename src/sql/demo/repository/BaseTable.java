package sql.demo.repository;

import sql.demo.StockExchangeDB;
import sql.demo.model.BaseModel;

import java.io.Closeable;
import java.sql.*;

// Сервисный родительский класс, куда вынесена реализация общих действий для всех таблиц
public class BaseTable implements Closeable, TableOperations {
    Connection connection; // JDBC-соединение для работы с таблицей
    Statement statement;
    ResultSet resultSet;

    PreparedStatement preparedStatement;

    String tableName;       // Имя таблицы

    BaseTable(String tableName) throws SQLException { // Для реальной таблицы передадим в конструктор её имя
        this.tableName = tableName;
        this.connection = StockExchangeDB.getConnection(); // Установим соединение с СУБД для дальнейшей работы
    }

    // Закрытие
    public void close() {
        try {
            connection.close();
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Ошибка закрытия SQL соединения!");
        } catch (NullPointerException n) {
            System.out.println("Ошибка закрытия SQL соединения! Соединение было закрыто ранее");
        }
    }

    // Выполнить SQL команду без параметров в СУБД, по завершению выдать сообщение в консоль
    void executeSqlStatement(PreparedStatement preparedStatement) throws SQLException {
        reopenConnection(); // переоткрываем (если оно неактивно) соединение с СУБД
        preparedStatement.execute(); // Выполняем statement - sql команду
        close();      // Закрываем statement для фиксации изменений в СУБД
    }

    // Активизация соединения с СУБД, если оно не активно.
    void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = StockExchangeDB.getConnection();
        }
    }

    @Override
    public void addData(BaseModel baseModel) throws SQLException {}

    @Override
    public boolean isExistsInDB(BaseModel baseModel) throws SQLException {
        return false;
    }

    @Override
    public void createTable() throws SQLException {}

    @Override
    public void addAllTypes(String[] allTypes) throws SQLException {
    }

    @Override
    public void deleteData(int id) throws SQLException {

    }

    @Override
    public void updateData(int id, String newType) throws SQLException {

    }
}