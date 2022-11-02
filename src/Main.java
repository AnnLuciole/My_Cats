import sql.demo.DAO.*;
import sql.demo.config.SQLiteConnectionConfiguration;
import sql.demo.entity.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        SQLiteConnectionConfiguration sqLiteConnectionConfiguration = new SQLiteConnectionConfiguration();

        CatDAO catDAO = new CatDAO();
        CatTypeDAO catTypeDAO = new CatTypeDAO();

        CatType catType;

        try {
            catDAO.createTable(sqLiteConnectionConfiguration);
            catTypeDAO.createTable(sqLiteConnectionConfiguration);
            catDAO.insertCat("Феофан", "Кот Бегемот", 2, 7, sqLiteConnectionConfiguration);
            catType = new CatType("Кот Бегемот");
            catTypeDAO.addData(catType, sqLiteConnectionConfiguration);
            catDAO.getCatWhere("weight > 7", sqLiteConnectionConfiguration);
            catDAO.deleteData(1848, sqLiteConnectionConfiguration);
        } catch (SQLException exception){
            exception.printStackTrace();
            System.out.println("SQL Error");
        }
    }
}