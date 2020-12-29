import logic.Board;
import logic.Square;
import org.junit.Test;
import pieces.Bishop;
import pieces.Piece;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BishopTest {

	public void boardInit(Board b) {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				b.chessBoard[x][y] = new Square(b, new Point(x, y));
			}
		}
	}

	@Test
	public void testDiagUpLeft() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Bishop(test.chessBoard[3][3], "Белые");

		test.chessBoard[3][3].setOccupier(p);

		assertTrue("Диагональ вверх влево", test.move(new Point(3, 3), new Point(1, 1)));
	}

	@Test
	public void testDiagUpRight() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Bishop(test.chessBoard[3][3], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		
		assertTrue("Диагональ вверх и вправо", test.move(new Point(3, 3), new Point(1, 5)));
	}

	@Test
	public void testDiagDownLeft() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Bishop(test.chessBoard[3][3], "Белые");

		test.chessBoard[3][3].setOccupier(p);

		assertTrue("Диагональ вниз влево", test.move(new Point(3, 3), new Point(5, 1)));
	}

	@Test
	public void testDiagDownRight() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Bishop(test.chessBoard[3][3], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		
		assertTrue("Диагональ вниз и вправо", test.move(new Point(3, 3), new Point(5, 5)));
	}
	
	@Test
	public void testOutOfBoundsMove() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Bishop(test.chessBoard[0][7], "Белые");
		
		test.chessBoard[0][7].setOccupier(p);
		
		assertFalse("Диагональ, выход за пределы доски", test.move(new Point(0, 7), new Point(2, 9)));
	}
	
	@Test
	public void testAttackEnemy() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Bishop(test.chessBoard[3][3], "Белые");
		Piece enemy = new Bishop(test.chessBoard[5][5], "Черные");
		
		test.chessBoard[3][3].setOccupier(p);
		test.chessBoard[5][5].setOccupier(enemy);
		
		assertTrue("Атака соперника", test.move(new Point(3, 3), new Point(5, 5)));
	}
	
	@Test
	public void testAttackFriendly() {
		Board test = new Board(false);
		boardInit(test);
		Piece p = new Bishop(test.chessBoard[3][3], "Белые");
		Piece friendly = new Bishop(test.chessBoard[5][5], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		test.chessBoard[5][5].setOccupier(friendly);
		
		assertFalse("Атака союзника", test.move(new Point(3, 3), new Point(5, 5)));
	}
}