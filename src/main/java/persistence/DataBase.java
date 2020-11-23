package persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {


    public Connection getConnect() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:tcp://localhost:1521/chess", "", "");
    }

    public void saveToDb(String winColor) throws SQLException {
        try (Connection c = getConnect()) {
            PreparedStatement statement = c.prepareStatement("insert into ChessGame(color) values (?)");
            statement.setString(1, winColor);
            statement.executeUpdate();
        }
    }

    public List<String> getAllGames() {
        List<String> result = new ArrayList<>();
        try (Connection c = getConnect()) {
            PreparedStatement statement = c.prepareStatement("select GAME_ID, COLOR from CHESSGAME");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("GAME_ID");
                String color = rs.getString("COLOR");
                result.add("Игра: " + id + " Результат: " + color);
            }
            return result;
        } catch (SQLException exception) {
            throw new RuntimeException("Не удается получить данные из таблицы!", exception);
        }
    }

    public void deleteFromDb(int id) throws SQLException {
        try (Connection c = getConnect()) {
            PreparedStatement statement = c.prepareStatement("DELETE FROM ChessGame where GAME_ID = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public void clearTable() throws SQLException {
        try (Connection c = getConnect()) {
            PreparedStatement statement = c.prepareStatement(
                    "DELETE FROM ChessGame;" +
                            "ALTER TABLE CHESSGAME ALTER COLUMN GAME_ID RESTART WITH 1"
            );
            statement.executeUpdate();
        }
    }
}
