package game;

import pieces.Chessboard;

public class Main {

    public static void main(String[] args) {
        Chessboard myChessboard = new Chessboard();
        while (myChessboard.getGameRunning()) {
            myChessboard.printBoard();
            myChessboard.move();
        }
    }
}
