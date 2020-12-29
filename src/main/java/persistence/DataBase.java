package persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    public Connection getConnect() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:tcp://localhost:1521/chess", "", "");
    }

    public void saveCheckmate(String situation, String steps) throws SQLException {
        try (Connection c = getConnect()) {
            PreparedStatement statement = c.prepareStatement("insert into ChessGame(result, steps) values (?, ?)");
            statement.setString(1, situation);
            statement.setString(2, steps);
            statement.executeUpdate();
        }
    }

    public void saveStalemate(String situation, String steps) throws SQLException {
        try (Connection c = getConnect()) {
            PreparedStatement statement = c.prepareStatement("insert into ChessGame(result, steps) values (?, ?)");
            statement.setString(1, situation);
            statement.setString(2, steps);
            statement.executeUpdate();
        }
    }
    public List<String> getAllGames() {
        List<String> result = new ArrayList<>();
        try (Connection c = getConnect()) {
            PreparedStatement statement = c.prepareStatement("select * from CHESSGAME");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("GAME_ID");
                String res = rs.getString("result");
                String steps = rs.getString("steps");
                result.add(id + "; " + res + "; " + steps);
            }
            return result;
        } catch (SQLException exception) {
            throw new RuntimeException("Не удается получить данные из таблицы!", exception);
        }
    }
}
