package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private Connection getConnect() throws SQLException{
        return DriverManager.getConnection("jdbc:h2:tcp://localhost:1521/chess", "", "");
    }
}
