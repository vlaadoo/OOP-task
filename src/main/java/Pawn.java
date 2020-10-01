package org.chess;
import static org.chess.GameState.N;

public class Pawn extends Figure {


    public Pawn(int c){
        color = c;
    }

    @Override
    public boolean checkMove(GameState gameState, int x, int y, int x2, int y2){
        // проверка хода без взятие

        if(x<0||x>=N||y<0||y>=N||x2<0||x2>=N||y2<0||y2>=N){
            return false;
        }

        if(x!=x2)return false;

        if(color==GameState.WHITE){

            if(y2-y==1 && gameState.at(x, y+1)==null){
                return true;
            }
            if(y==1 && y2==3 && gameState.at(x, 2)==null && gameState.at(x, 3)==null){
                return true;
            }
            return false;
        }
        else{

            if(y-y2==1 && gameState.at(x, y-1)==null){
                return true;
            }
            if(y==N-2 && y2==N-4 && gameState.at(x, N-3)==null && gameState.at(x, N-4)==null){
                return true;
            }
            return false;
        }
    }

    @Override
    public char getChar() {
        return 'p';
    }
}

