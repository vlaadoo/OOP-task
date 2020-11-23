import org.junit.jupiter.api.Test;
import pieces.Chessboard;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameTest {
    Chessboard myChessboard = new Chessboard();

    @Test
    public void gameStartTest() throws SQLException {
        while (myChessboard.getGameRunning()) {
            myChessboard.printBoard();
            myChessboard.move();
        }
    }


    @Test
    public void moveTest() {
        List<String> moves = new ArrayList<>();
        moves.add("b2-b4");
        moves.add("");
        moves.add("");
        moves.add("");

    }
}
