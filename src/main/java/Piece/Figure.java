package Piece;

public abstract class Figure {

    protected int color;

    public int getColor(){
        return color;
    }

    abstract public boolean checkMove(GameState gameState, int x, int y, int x2, int y2);
    abstract public char getChar();
}


