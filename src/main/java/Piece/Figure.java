package Piece;

public abstract class Figure {

    public GameState.GameColor color;

    public GameState.GameColor getColor(){
        return color;
    }

    abstract public boolean checkMove(GameState gameState, int x, int y, int x2, int y2);
    abstract public boolean checkEat(GameState gameState, int x, int y, int x2, int y2);


    abstract public char getChar();
}

