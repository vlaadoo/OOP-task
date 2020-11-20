package pieces;


public class Knight extends AbstractPiece {

	public Knight(boolean isWhite) {
		super(isWhite);
			}

	@Override
	public void draw() {
		if (isWhite){
			System.out.print("\u265E");
		}
		else{
			System.out.print("\u2658");
		}		
	}
	
	private static Boolean lShapedPath(int srcRow, int srcCol,
			int destRow, int destCol) {
		/**
		 * Проверка хода:
		 * Возвращается true если движение имеет L-образный
		 * вид. Аргументы - начальная и конечная координата
		 * хода на шахматной доске
		 */

		return ((Math.abs(srcRow - destRow) == 2 && Math.abs(srcCol
				- destCol) == 1)
				|| (Math.abs(srcRow - destRow) == 1 && Math.abs(srcCol
						- destCol) == 2));
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		return lShapedPath(srcRow, srcCol, destRow, destCol);
	}

	@Override
	public int relativeValue() {
		return 3;
	}

}
