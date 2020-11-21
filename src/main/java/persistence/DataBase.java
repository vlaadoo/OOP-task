package persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {


    private Connection getConnect() throws SQLException{
        return DriverManager.getConnection("jdbc:h2:tcp://localhost:1521/chess", "", "");
    }

    public void saveToDb(String winColor) throws SQLException {
        try (Connection c = getConnect()){
            PreparedStatement statement = c.prepareStatement("insert into ChessGame(color) values (?)");
            statement.setString(1, winColor);
            statement.executeUpdate();
        }
    }

    public void getAllGames(){
        try (Connection c = getConnect()) {
            PreparedStatement statement = c.prepareStatement("select GAME_ID, COLOR from CHESSGAME");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("GAME_ID");
                String color = rs.getString("COLOR");
                System.out.println("Игра: " + id + " Результат: " + color);
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Не удается получить данные из таблицы!", exception);
        }
    }
}
