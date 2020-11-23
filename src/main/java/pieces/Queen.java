package pieces;

public class Queen extends AbstractPiece {

	public Queen(boolean isWhite) {
		super(isWhite);
		}


	@Override
	public String pieceName() {
		return "Queen";
	}

	@Override
	public void draw() {
		if (isWhite){
			System.out.print("\u265B");
		}
		else{
			System.out.print("\u2655");
		}
	}

	private Boolean diagonalPath(int srcRow, int srcCol, int destRow,
			int destCol) {
		/*
		 * Проверка хода:
		 * Возвращается true если задано движение по диагонали.
		 * Аргументы - начальная и конечная координата хода на
		 * шахматной доске
		 */
		return ((Math.abs(srcRow - destRow) == Math.abs(srcCol - destCol)));
	}

	private Boolean straightPath(int srcRow, int srcCol, int destRow,
			int destCol) {
		/*
		 * Проверка хода:
		 * Возвращается true если задано движение по прямой.
		 * Аргументы - начальная и конечная координата хода на
		 * шахматной доске
		 */
		return !((srcRow != destRow) && (srcCol != destCol));
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		return (diagonalPath(srcRow, srcCol, destRow, destCol))
				|| straightPath(srcRow, srcCol, destRow, destCol);
	}

	@Override
	public int relativeValue() {
		return 9;
	}

}
