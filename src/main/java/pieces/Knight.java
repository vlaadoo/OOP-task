package pieces;

import logic.Square;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public final class Knight extends Piece {
	

	public Knight(Square occupying, String color) {
		super(occupying, color);
		
		if(getColor() == "Белые") {
			this.icon = new ImageIcon("images/whiteknight.png");
		}
		else if(getColor() == "Черные") {
			this.icon = new ImageIcon("images/blackknight.png");
		}
	}
	
	public List<Point> getPossibleMoves() {
		List<Point> moves = new ArrayList<Point>();
		
		Point moveIterator = new Point(occupying.getPosition());
		
		if(moveIterator.x + 1 <= 7 && moveIterator.y + 2 <= 7) {
			if(occupying.getBoard().getSquare(moveIterator.x + 1, moveIterator.y + 2).getOccupier() == null || occupying.getBoard().getSquare(moveIterator.x + 1, moveIterator.y + 2).getOccupier().getColor() != getColor()) {
				moves.add(new Point(moveIterator.x + 1, moveIterator.y + 2));
			}
		}
		if(moveIterator.x - 1 >= 0 && moveIterator.y + 2 <= 7) {
			if(occupying.getBoard().getSquare(moveIterator.x - 1, moveIterator.y + 2).getOccupier() == null || occupying.getBoard().getSquare(moveIterator.x - 1, moveIterator.y + 2).getOccupier().getColor() != getColor()) {
				moves.add(new Point(moveIterator.x - 1, moveIterator.y + 2));
			}
		}
		if(moveIterator.x + 2 <= 7 && moveIterator.y + 1 <= 7) {
			if(occupying.getBoard().getSquare(moveIterator.x + 2, moveIterator.y + 1).getOccupier() == null || occupying.getBoard().getSquare(moveIterator.x + 2, moveIterator.y + 1).getOccupier().getColor() != getColor()) {
				moves.add(new Point(moveIterator.x + 2, moveIterator.y + 1));
			}
		}
		if(moveIterator.x + 2 <= 7 && moveIterator.y - 1 >= 0) {
			if(occupying.getBoard().getSquare(moveIterator.x + 2, moveIterator.y - 1).getOccupier() == null || occupying.getBoard().getSquare(moveIterator.x + 2, moveIterator.y - 1).getOccupier().getColor() != getColor()) {
				moves.add(new Point(moveIterator.x + 2, moveIterator.y - 1));
			}
		}
		if(moveIterator.x + 1 <= 7 && moveIterator.y - 2 >= 0) {
			if(occupying.getBoard().getSquare(moveIterator.x + 1, moveIterator.y - 2).getOccupier() == null || occupying.getBoard().getSquare(moveIterator.x + 1, moveIterator.y - 2).getOccupier().getColor() != getColor()) {
				moves.add(new Point(moveIterator.x + 1, moveIterator.y - 2));
			}
		}
		if(moveIterator.x - 1 >= 0 && moveIterator.y - 2 >= 0) {
			if(occupying.getBoard().getSquare(moveIterator.x - 1, moveIterator.y - 2).getOccupier() == null || occupying.getBoard().getSquare(moveIterator.x - 1, moveIterator.y - 2).getOccupier().getColor() != getColor()) {
				moves.add(new Point(moveIterator.x - 1, moveIterator.y - 2));
			}
		}
		if(moveIterator.x - 2 >= 0 && moveIterator.y - 1 >= 0) {
			if(occupying.getBoard().getSquare(moveIterator.x - 2, moveIterator.y - 1).getOccupier() == null || occupying.getBoard().getSquare(moveIterator.x - 2, moveIterator.y - 1).getOccupier().getColor() != getColor()) {
				moves.add(new Point(moveIterator.x - 2, moveIterator.y - 1));
			}
		}
		if(moveIterator.x - 2 >= 0 && moveIterator.y + 1 <= 7) {
			if(occupying.getBoard().getSquare(moveIterator.x - 2, moveIterator.y + 1).getOccupier() == null || occupying.getBoard().getSquare(moveIterator.x - 2, moveIterator.y + 1).getOccupier().getColor() != getColor()) {
				moves.add(new Point(moveIterator.x - 2, moveIterator.y + 1));
			}
		}
		return moves;
	}
	
}
