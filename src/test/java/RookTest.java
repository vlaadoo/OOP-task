import logic.Board;
import logic.Square;
import org.junit.Test;
import pieces.Piece;
import pieces.Rook;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class RookTest {
	public void boardInit(Board b) {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				b.chessBoard[x][y] = new Square(b, new Point(x, y));
			}
		}
	}

	@Test
	public void testLeft() {
		Board test = new Board();
		boardInit(test);
		Piece p = new Rook(test.chessBoard[3][3], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		
		assertTrue("Влево", test.move(new Point(3, 3), new Point(1, 3)));
	}
	
	@Test
	public void testRight() {
		Board test = new Board();
		boardInit(test);
		Piece p = new Rook(test.chessBoard[3][3], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		
		assertTrue("Вправо", test.move(new Point(3, 3), new Point(5, 3)));
	}
	
	@Test
	public void testUp() {
		Board test = new Board();
		boardInit(test);
		Piece p = new Rook(test.chessBoard[3][3], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		
		assertTrue("Вверх", test.move(new Point(3, 3), new Point(3, 1)));
	}
	
	@Test
	public void testDown() {
		Board test = new Board();
		boardInit(test);
		Piece p = new Rook(test.chessBoard[3][3], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		
		assertTrue("Вниз", test.move(new Point(3, 3), new Point(3, 5)));
	}
	
	@Test
	public void testOutOfBoundsMove() {
		Board test = new Board();
		boardInit(test);
		Piece p = new Rook(test.chessBoard[0][7], "Белые");
		
		test.chessBoard[0][7].setOccupier(p);
		
		assertFalse("Диагональ, выход за пределы доски", test.move(new Point(0, 7), new Point(0, 9)));
	}
	
	@Test
	public void testAttackEnemy() {
		Board test = new Board();
		boardInit(test);
		Piece p = new Rook(test.chessBoard[3][3], "Белые");
		Piece enemy = new Rook(test.chessBoard[3][5], "Черные");
		
		test.chessBoard[3][3].setOccupier(p);
		test.chessBoard[3][5].setOccupier(enemy);
		
		assertTrue("Атака соперника", test.move(new Point(3, 3), new Point(3, 5)));
	}
	
	@Test
	public void testAttackFriendly() {
		Board test = new Board();
		boardInit(test);
		Piece p = new Rook(test.chessBoard[3][3], "Белые");
		Piece friendly = new Rook(test.chessBoard[3][5], "Белые");
		
		test.chessBoard[3][3].setOccupier(p);
		test.chessBoard[3][5].setOccupier(friendly);
		
		assertFalse("Атака союзника", test.move(new Point(3, 3), new Point(3, 5)));
	}
}