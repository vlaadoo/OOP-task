package Game;

import Piece.GameState;

public class Main {

    public static void main(String...args) {

        GameState game = new GameState();

        game.print();

        game.A[3][4] = game.A[1][4];
        game.A[1][4] = null;

        game.print();
    }
}
