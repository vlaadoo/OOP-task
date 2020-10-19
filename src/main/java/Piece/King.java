package Piece;

public class King extends Figure {

    public King(GameState.GameColor c){
        color = c;
    }

    @Override
    public boolean checkMove(GameState gameState, int x, int y, int x2, int y2) {

        if (x < 0 || x >= GameState.N || y < 0 || y >= GameState.N || x2 < 0 || x2 >= GameState.N || y2 < 0 || y2 >= GameState.N) {
            return false;
        }

        if (x == x2 && y == y2) {
            return false;
        }

        if(gameState.at(x2, y2)!=null){
            return false;
        }



        int dx_abs = Math.abs(x-x2);
        int dy_abs = Math.abs(y-y2);

        if(dx_abs <= 1 && dy_abs <= 1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean checkEat(GameState gameState, int x, int y, int x2, int y2) {
        // не может бить никого никогда
        return false;
    }

    @Override
    public char getChar() {
        return '♔';
    }
}
