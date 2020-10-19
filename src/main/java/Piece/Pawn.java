package Piece;

public class Pawn extends Figure {


    public Pawn(GameState.GameColor c){
        color = c;
    }

    @Override
    public boolean checkMove(GameState gameState, int x, int y, int x2, int y2){
        // проверка хода без взятие

        if(x<0||x>= GameState.N||y<0||y>= GameState.N||x2<0||x2>= GameState.N||y2<0||y2>= GameState.N){
            return false;
        }

        if(x!=x2)return false;

        if(color==GameState.GameColor.WHITE){

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
            if(y== GameState.N-2 && y2== GameState.N-4 && gameState.at(x, GameState.N-3)==null && gameState.at(x, GameState.N-4)==null){
                return true;
            }
            return false;
        }
    }



    @Override
    public boolean checkEat(GameState gameState, int x, int y, int x2, int y2) {

        if(x<0||x>= GameState.N||y<0||y>= GameState.N||x2<0||x2>= GameState.N||y2<0||y2>= GameState.N){
            return false;
        }

        if(gameState.at(x2, y2)==null){
            return false;
        }

        if(gameState.at(x2, y2).color==color){
            return false;
        }

        // на целевой клетке противник (может быть король)

        if(color== GameState.GameColor.WHITE){

            if(y2==y+1 && (x2==x+1 || x2==x-1)){

                return true;
            }
            else{
                return false;
            }
        }
        else{

            if(y2==y-1 && (x2==x+1 || x2==x-1)){

                return true;
            }
            else{
                return false;
            }
        }
    }

    @Override
    public char getChar() {
        return '♙';
    }
}






















