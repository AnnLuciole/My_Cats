package sql.demo.repository;

import sql.demo.model.BaseModel;

import java.sql.SQLException;

public interface TableOperations {

    void createTable() throws SQLException; // создание таблицы
    void addData(BaseModel baseModel) throws SQLException; //добавление данных в таблицу
    void addAllTypes(String[] allTypes) throws SQLException; //добавление данных из массива

    boolean isExistsInDB(BaseModel baseModel) throws SQLException;
}
