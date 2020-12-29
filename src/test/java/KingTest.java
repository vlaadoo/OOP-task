import logic.Board;
import logic.Square;
import org.junit.Test;
import pieces.King;
import pieces.Piece;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class KingTest {

	public void boardInit(Board b) {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				b.chessBoard[x][y] = new Square(b, new Point(x, y));
			}
		}
	}


	@Test
	public void testDiagUpLeft() {
		Board test = new Board();
		boardInit(test);
		Piece p = new King(test.chessBoard[3][3], "Белые");

		test.chessBoard[3][3].setOccupier(p);

		assertTrue("Диагональ вверх влево", test.move(new Point(3, 3), new Point(2, 2)));
	}

	@Test
	public void testDiagUpRight() {
		Board test = new Board();
		boardInit(test);
		Piece p = new King(test.chessBoard[3][3], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		
		assertTrue("Диагональ вверх вправо", test.move(new Point(3, 3), new Point(2, 4)));
	}

	@Test
	public void testDiagDownLeft() {
		Board test = new Board();
		boardInit(test);
		Piece p = new King(test.chessBoard[3][3], "Белые");

		test.chessBoard[3][3].setOccupier(p);

		assertTrue("Диагональ вниз влево", test.move(new Point(3, 3), new Point(4, 2)));
	}

	@Test
	public void testDiagDownRight() {
		Board test = new Board();
		boardInit(test);
		Piece p = new King(test.chessBoard[3][3], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		
		assertTrue("Диагональ вниз вправо", test.move(new Point(3, 3), new Point(4, 4)));
	}
	
	@Test
	public void testOutOfBoundsMove() {
		Board test = new Board();
		boardInit(test);
		Piece p = new King(test.chessBoard[0][7], "Белые");
		
		test.chessBoard[0][7].setOccupier(p);
		
		assertFalse("Диагональ, выход за пределы доски", test.move(new Point(0, 7), new Point(1, 8)));
	}
	
	@Test
	public void testAttackEnemy() {
		Board test = new Board();
		boardInit(test);
		Piece p = new King(test.chessBoard[3][3], "Белые");
		Piece enemy = new King(test.chessBoard[4][4], "Черные");
		
		test.chessBoard[3][3].setOccupier(p);
		test.chessBoard[4][4].setOccupier(enemy);
		
		assertTrue("Атака соперника", test.move(new Point(3, 3), new Point(4, 4)));
	}
	
	@Test
	public void testAttackFriendly() {
		Board test = new Board();
		boardInit(test);
		Piece p = new King(test.chessBoard[3][3], "Белые");
		Piece friendly = new King(test.chessBoard[4][4], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		test.chessBoard[4][4].setOccupier(friendly);
		
		assertFalse("Атака союзника", test.move(new Point(3, 3), new Point(4, 4)));
	}
}