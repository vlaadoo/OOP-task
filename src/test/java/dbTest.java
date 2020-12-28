import org.junit.jupiter.api.Test;
import persistence.DataBase;

import java.sql.*;
import java.util.List;

public class dbTest {
    DataBase db = new DataBase();

    @Test
    public void dbConTest() throws SQLException {
        DriverManager.getConnection("jdbc:h2:tcp://localhost:1521/chess", "", "");
    }
//
//    @Test
//    public void gamesFetchTest() {
//        final List<String> result = db.getAllGames();
//    }
//
//    @Test
//    public void addToDbTest() throws SQLException {
//        db.saveToDb("Test");
//    }
//
//    @Test
//    public void deleteFromDbTest() throws SQLException {
//        int maxId = 0;
//        try (Connection c = db.getConnect()) {
//            PreparedStatement st = c.prepareStatement("select max(GAME_ID) from CHESSGAME");
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                maxId = rs.getInt("MAX(GAME_ID)");
//            }
//            st = c.prepareStatement("DROP CONSTANT maxID;" +
//                    "CREATE CONSTANT maxID VALUE (SELECT MAX(GAME_ID) FROM CHESSGAME);" +
//                    "ALTER TABLE CHESSGAME ALTER COLUMN GAME_ID RESTART WITH maxID;"
//            );
//            st.executeUpdate();
//        } catch (SQLException exception) {
//            throw new RuntimeException("Error in delete test", exception);
//        }
//        db.deleteFromDb(maxId);
//    }
}