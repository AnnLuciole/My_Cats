package sql.demo.repository;

import sql.demo.model.BaseModel;

import java.sql.SQLException;

public interface TableOperations {

    void createTable() throws SQLException; // создание таблицы
    void addData(BaseModel baseModel) throws SQLException; //добавление данных в таблицу
    void addAllTypes(String[] allTypes) throws SQLException; //добавление данных из массива

    void deleteData(int id) throws SQLException; //deleting data from database

    void deleteData(String where) throws SQLException;
    void updateData(int id, String newType) throws SQLException; //updating data in database
    void updateData(int id, String set, String where) throws SQLException;

    boolean isExistsInDB(BaseModel baseModel) throws SQLException;
}
