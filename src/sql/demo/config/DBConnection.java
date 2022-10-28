package sql.demo.config;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnection {
    Connection open() throws SQLException, ClassNotFoundException;
    void close() throws SQLException;
}