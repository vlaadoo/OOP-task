import logic.Board;
import logic.Square;
import org.junit.Test;
import pieces.Knight;
import pieces.Piece;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class KnightTest {

	public void boardInit(Board b) {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				b.chessBoard[x][y] = new Square(b, new Point(x, y));
			}
		}
	}

	@Test
	public void testUpLeft1() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Knight(test.chessBoard[3][3], "Белые");

		test.chessBoard[3][3].setOccupier(p);

		assertTrue("Вверх влево 1", test.move(new Point(3, 3), new Point(1, 2)));
	}

	@Test
	public void testUpLeft2() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Knight(test.chessBoard[3][3], "Белые");

		test.chessBoard[3][3].setOccupier(p);

		assertTrue("Вверх влево 2", test.move(new Point(3, 3), new Point(2, 1)));
	}

	@Test
	public void testUpRight1() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Knight(test.chessBoard[3][3], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		
		assertTrue("Вверх вправо 1", test.move(new Point(3, 3), new Point(2, 5)));
	}
	
	@Test
	public void testUpRight2() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Knight(test.chessBoard[3][3], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		
		assertTrue("Вверх вправо 2", test.move(new Point(3, 3), new Point(1, 4)));
	}
	
	@Test
	public void testDownLeft1() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Knight(test.chessBoard[3][3], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		
		assertTrue("Вниз влево 1", test.move(new Point(3, 3), new Point(4, 1)));
	}
	
	@Test
	public void testDownLeft2() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Knight(test.chessBoard[3][3], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		
		assertTrue("Вниз влево 2", test.move(new Point(3, 3), new Point(5, 2)));
	}

	@Test
	public void testDownRight1() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Knight(test.chessBoard[3][3], "Белые");

		test.chessBoard[3][3].setOccupier(p);

		assertTrue("Вниз вправо 1", test.move(new Point(3, 3), new Point(4, 5)));
	}

	@Test
	public void testDownRight2() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Knight(test.chessBoard[3][3], "Белые");

		test.chessBoard[3][3].setOccupier(p);

		assertTrue("Вниз вправо 2", test.move(new Point(3, 3), new Point(5, 4)));
	}
	
	@Test
	public void testOutOfBoundsMove() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Knight(test.chessBoard[0][7], "Белые");
		
		test.chessBoard[0][7].setOccupier(p);
		
		assertFalse("DiagOutOfBoundsMove", test.move(new Point(0, 7), new Point(1, 9)));
	}
	
	@Test
	public void testAttackEnemy() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Knight(test.chessBoard[3][3], "Белые");
		Piece enemy = new Knight(test.chessBoard[4][5], "Черные");
		
		test.chessBoard[3][3].setOccupier(p);
		test.chessBoard[4][5].setOccupier(enemy);
		
		assertTrue("AttackEnemy", test.move(new Point(3, 3), new Point(4, 5)));
	}
	
	@Test
	public void testAttackFriendly() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Knight(test.chessBoard[3][3], "Белые");
		Piece friendly = new Knight(test.chessBoard[4][5], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		test.chessBoard[4][5].setOccupier(friendly);
		
		assertFalse("AttackFriendly", test.move(new Point(3, 3), new Point(4, 5)));
	}
}