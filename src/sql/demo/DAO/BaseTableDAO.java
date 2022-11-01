package sql.demo.DAO;

import java.sql.*;

// Сервисный родительский класс, куда вынесена реализация общих действий для всех таблиц
public class BaseTableDAO {

    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    String tableName;// Имя таблицы
    String sql;

    BaseTableDAO(String tableName) { // Для реальной таблицы передадим в конструктор её имя
        this.tableName = tableName; // Установим соединение с СУБД для дальнейшей работы
    }
}