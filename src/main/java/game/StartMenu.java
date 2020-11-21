package game;

import pieces.Chessboard;

import java.sql.SQLException;

public class StartMenu {
    public static void StartMenu() throws SQLException {
        Chessboard myChessboard = new Chessboard();
        while (myChessboard.getGameRunning()) {
            myChessboard.printBoard();
            myChessboard.move();
        }
    }
}
