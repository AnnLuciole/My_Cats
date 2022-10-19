package sql.demo.repository;

import java.sql.SQLException;

public class CatTypes extends BaseTable implements TableOperations {

    public CatTypes() throws SQLException {
        super("types");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE if not exists types (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "type VARCHAR(100) NOT NULL);", "Создана таблица " + super.tableName);
    }
}
