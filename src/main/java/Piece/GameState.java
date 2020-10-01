package Piece;

import java.nio.file.Files;

public class GameState {

    static final int WHITE = 1;
    static final int BLACK = 2;

    static final int N = 8;

    public Figure[][] A;

    public GameState(){

        A = new Figure[N][N];
        // расстановка

        for(int i = 0; i < N; ++i){

            for(int j = 0; j < N; j++){

                A[i][j] = null;
            }
        }

        for(int i = 0; i < N; ++i){
            A[1][i] = new Pawn(WHITE);
            A[N-2][i] = new Pawn(BLACK);
        }

        A[0][0] = new Rook(WHITE);
        A[0][N-1] = new Rook(WHITE);
        A[N-1][0] = new Rook(BLACK);
        A[N-1][N-1] = new Rook(BLACK);

        A[0][1] = new Knight(WHITE);
        A[0][N-2] = new Knight(WHITE);
        A[N-1][1] = new Knight(BLACK);
        A[N-1][N-2] = new Knight(BLACK);

        A[0][2] = new Bishop(WHITE);
        A[0][N-3] = new Bishop(WHITE);
        A[N-1][2] = new Bishop(BLACK);
        A[N-1][N-3] = new Bishop(BLACK);

        A[0][4] = new King(WHITE);
        A[N-1][4] = new King(BLACK);
        A[0][3] = new Queen(WHITE);
        A[N-1][3] = new Queen(BLACK);
    }

    Figure at(int x, int y){

        return A[y][x];
    }

    public void print(){

        int cnt = 0;
        for(int i = 0; i < N; ++i){
            for(int j = 0; j < N; ++j){

                if(A[i][j]==null){
                    System.out.format(" ");
                }
                else{

                    System.out.format("%c", A[i][j].getChar());
                    for(int i2= 0; i2<N; ++i2){
                        for(int j2=0; j2<N; ++j2){

                            if(A[i][j].checkMove(this, j, i, j2, i2)){
                                System.out.format("[%d;%d] -> [%d;%d]\n", j, i, j2, i2);
                                cnt++;
                            }
                        }
                    }

                }


            }
            System.out.format("\n");
        }
        System.out.format("Total possible moves = %d\n", cnt);
        System.out.format("----------------------\n");


    }
}