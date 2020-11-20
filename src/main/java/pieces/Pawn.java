package pieces;

public class Pawn extends AbstractPiece {

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void draw() {
        if (this.isWhite) {
            System.out.print("\u265F");
        }
        if (!(this.isWhite)) {
            System.out.print("\u2659");
        }

    }

    @Override
    public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {

        /**
         * Проверка хода:
         * Движение пешки вперед на 1 или 2 клетки
         * или уничтожает фигуру черного цвета, тогда
         * останавливается.
         * Иначе возвращает false как неправильный шаг
         */
        if (this.isWhite) {
            return (((srcCol == destCol) && srcRow == (destRow + 1))
                    || ((srcRow == 6) && (srcCol == destCol) && (srcRow == (destRow + 2)))
                    || ((srcRow == (destRow + 1))
                    && (Math.abs(srcCol - destCol) == 1)));
        } else {
            return (((srcCol == destCol) && srcRow == (destRow - 1))
                    || ((srcRow == 1) && (srcCol == destCol) && (srcRow == (destRow - 2)))
                    || ((srcRow == (destRow - 1))
                    && (Math.abs(srcCol - destCol) == 1)));
        }


    }

    @Override
    public int relativeValue() {
        return 1;
    }

}
