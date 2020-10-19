package Game;


import Piece.GameState;

public class Main {

    public static void main(String...args) {
        GameState game = new GameState();

        game.print();

        game.move(4, 1, 4, 3);

        game.print();

        game.move(5, 6, 5, 4);

        game.print();

        game.move(3, 0, 7, 4);

        int cnt = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(game.at(i,j)!=null && game.at(i, j).color== GameState.GameColor.BLACK){

                    for(int i2 = 0; i2 < 8; i2++){
                        for(int j2 = 0; j2 < 8; j2++){
                            if(game.isCorrectMove(i, j, i2, j2)){
                                System.out.format("'%c' can move\n", game.at(i,j).getChar());
                                cnt++;
                            }
                        }
                    }
                }
            }
        }

        game.print();


    }
}
