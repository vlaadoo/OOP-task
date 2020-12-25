package bak.pieces;

public class Rook extends AbstractPiece {

	public Rook(boolean isWhite) {
		super(isWhite);
	}

	@Override
	public String pieceName() {
		return "pieces.Rook";
	}

	@Override
	public void draw() {
		if (isWhite){
			System.out.print("\u265C");
		}
		else{
			System.out.print("\u2656");
		}		
	}
	
	private Boolean straightPath(int srcRow, int srcCol,
			int destRow, int destCol) {
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
		return straightPath(srcRow, srcCol, destRow, destCol);
	}

	@Override
	public int relativeValue() {
		return 5;
	}

}
