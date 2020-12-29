import org.junit.Test;
import persistence.DataBase;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;

public class DatabaseTest {
    DataBase db = new DataBase();

    @Test
    public void dbConTest() throws SQLException {
        DriverManager.getConnection("jdbc:h2:tcp://localhost:1521/chess", "", "");
    }


    @Test
    public void addCheckmateTest() throws SQLException {
        db.saveCheckmate("checkmate test", "test");

        int maxId = 0;
        try (Connection c = db.getConnect()) {
            PreparedStatement st = c.prepareStatement("select max(GAME_ID) from CHESSGAME");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                maxId = rs.getInt("MAX(GAME_ID)");
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        List<String> result = db.getAllGames();
        assertEquals(result.get(result.size() - 1), maxId + "; checkmate test; test");
    }

    @Test
    public void addStalemateTest() throws SQLException {
        db.saveStalemate("stalemate test", "test");

        int maxId = 0;
        try (Connection c = db.getConnect()) {
            PreparedStatement st = c.prepareStatement("select max(GAME_ID) from CHESSGAME");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                maxId = rs.getInt("MAX(GAME_ID)");
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        List<String> result = db.getAllGames();
        assertEquals(result.get(result.size() - 1), maxId + "; stalemate test; test");
    }
}