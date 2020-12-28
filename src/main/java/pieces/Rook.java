package pieces;

import logic.Square;
import pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public final class Rook extends Piece {
	
	public Rook(Square occupying, String color) {
		super(occupying, color);
		
		if(getColor() == "Белые") {
			icon = new ImageIcon("images/whiterook.png");
		}
		else if(getColor() == "Черные") {
			icon = new ImageIcon("images/blackrook.png");
		}
	}
	
	public List<Point> getPossibleMoves() {
		List<Point> moves = new ArrayList();
		
		Point moveIterator = new Point(occupying.getPosition());
		
		int startx = moveIterator.x;
		int starty = moveIterator.y;
		
		getHorizRight(moves, moveIterator, startx, starty);
		getHorizLeft(moves, moveIterator, startx, starty);
		getVertUp(moves, moveIterator, startx, starty);
		getVertDown(moves, moveIterator, startx, starty);
		
		moveIterator.x = startx;
		moveIterator.y = starty;
		
		return moves;
	}
}
