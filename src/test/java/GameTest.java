import org.junit.jupiter.api.Test;
import pieces.Chessboard;

import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    Chessboard chessboard = new Chessboard();

    @Test
    public void gameStartTest() throws SQLException {
        assertEquals(true, chessboard.getGameRunning());
    }
}
