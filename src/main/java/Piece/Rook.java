package Piece;

import static Piece.GameState.N;

public class Rook extends Figure {

    public Rook(int c){
        color = c;
    }

    @Override
    public boolean checkMove(GameState gameState, int x, int y, int x2, int y2){

        if(x<0||x>=N||y<0||y>=N||x2<0||x2>=N||y2<0||y2>=N){
            return false;
        }

        if(x==x2 && y==y2){
            return false;
        }

        // right
        if(y2==y && x2 > x){

            for(int i = x+1; i <= x2; ++i){
                if(gameState.at(i, y)!=null){
                    return false;
                }
            }
            return true;
        }
        // left
        if(y2==y && x2 < x){

            for(int i = x-1; i >= x2; --i){
                if(gameState.at(i, y)!=null){
                    return false;
                }
            }
            return true;
        }
        // up
        if(x==x2 && y2 > y){
            for(int i = y+1; i <= y2; ++i){
                if(gameState.at(x, i)!=null){
                    return false;
                }
            }
            return true;
        }
        // down
        if(x==x2 && y2 < y){
            for(int i = y-1; i >= y2; --i){
                if(gameState.at(x, i)!=null){
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    @Override
    public char getChar() {
        return 'R';
    }
}