import logic.Board;
import logic.Square;
import org.junit.Test;
import pieces.Pawn;
import pieces.Piece;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PawnTest {

	public void boardInit(Board b) {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				b.chessBoard[x][y] = new Square(b, new Point(x, y));
			}
		}
	}
	
	@Test
	public void testMove() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Pawn(test.chessBoard[0][1], "Черные");
		Piece tryBeEaten = new Pawn(test.chessBoard[1][4], "Черные");
		Piece toBeEaten = new Pawn(test.chessBoard[1][5], "Белые");
		Piece cantMove = new Pawn(test.chessBoard[0][7], "Черные");
		Piece blocker = new Pawn(test.chessBoard[1][6], "Черные");
		
		test.chessBoard[0][1].setOccupier(p);
		test.chessBoard[1][4].setOccupier(tryBeEaten);
		test.chessBoard[1][5].setOccupier(toBeEaten);
		test.chessBoard[0][7].setOccupier(cantMove);
		test.chessBoard[1][6].setOccupier(blocker);
		
		assertFalse("Выход за пределы", test.move(new Point(0, 7), new Point(-1, 7)));
		assertFalse("Атака по диагонали на пустую клетку", test.move(new Point(0, 1), new Point(1, 2)));
		assertTrue("Переход на 2 клетки, если фигура не двигалась", test.move(new Point(0, 1), new Point(0, 3)));
		assertFalse("Атака союзника", test.move(new Point(0, 3), new Point(1, 4)));
		assertFalse("Переход на 2 клетки, если фигура двигалась", test.move(new Point(0, 3), new Point(0, 5)));
		assertTrue("Переход на 1 клетку", test.move(new Point(0, 3), new Point(0, 4)));
		assertFalse("Переход на клетку назад", test.move(new Point(0, 4), new Point(0, 3)));
		assertTrue("Атака вражеской фигуры", test.move(new Point(0, 4), new Point(1, 5)));
		assertFalse("Переход на 1 клетку, которая занята своей фигурой", test.move(new Point(1, 5), new Point(1, 6)));
	}
}
