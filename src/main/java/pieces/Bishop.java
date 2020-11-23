package pieces;

public class Bishop extends AbstractPiece {

	public Bishop(boolean isWhite) {
		super(isWhite);
	}

	@Override
	public String pieceName() {
		return "Bishop";
	}

	@Override
	public void draw() {
		if (isWhite) {
			System.out.print("\u265D");
		} else {
			System.out.print("\u2657");
		}
	}

	private Boolean diagonalPath(int srcRow, int srcCol,
			int destRow, int destCol) {
		/*
		 * Проверка хода:
		 * Возвращается true если задано движение по диагонали.
		 * Аргументы - начальная и конечная координата хода на
		 * шахматной доске
		*/
		return ((Math.abs(srcRow - destRow) == Math.abs(srcCol
				- destCol)));
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		return diagonalPath(srcRow, srcCol, destRow, destCol);
	}

	@Override
	public int relativeValue() {
		return 3;
	}


}
