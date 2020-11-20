package pieces;

public abstract class AbstractPiece {

    boolean isWhite;

    // Ставится true если фигура белая. Иначе ставится false
    public AbstractPiece(boolean isWhite) {
        this.isWhite = isWhite;
    }


    //Отрисовка фигуры в консоли
    public abstract void draw();


    /**
     * Проверка хода на правильность для конкретной фигуры. True - если правильный,
     * false - если неправильный
     */
    public abstract boolean isMoveValid(int srcRow, int srcCol, int destRow,
                                        int destCol);

    // Ценность фигуры
    public abstract int relativeValue();

}
