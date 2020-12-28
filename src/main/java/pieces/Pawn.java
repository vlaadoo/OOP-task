package pieces;

import logic.Square;
import pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {
	
	public Pawn(Square occupying, String color) {
		super(occupying, color);
		setMoved(false);
		if(getColor() == "Белые") {
			this.icon = new ImageIcon("images/whitepawn.png");
		}
		else if(getColor() == "Черные") {
			this.icon = new ImageIcon("images/blackpawn.png");
		}
	}
	
	
	public List<Point> getPossibleMoves() {
		List<Point> moves = new ArrayList<Point>();
		
		Point moveIterator = new Point(occupying.getPosition());
		
		if(getColor() == "Черные") {
			if(moveIterator.y + 1 <= 7) {
				if(occupying.getBoard().getSquare(moveIterator.x, moveIterator.y + 1).getOccupier() == null) {
					moves.add(new Point(moveIterator.x, moveIterator.y + 1)); // движение вниз, если пешка не находится около верхнего края доски и в клетке нет фигуры
					if(this.isMoved() == false && moveIterator.y + 2 <= 7) {
						if(occupying.getBoard().getSquare(moveIterator.x, moveIterator.y + 2).getOccupier() == null) {
							moves.add(new Point(moveIterator.x, moveIterator.y + 2)); // переход через 2 клетки в начале игры, если пешка еще не была тронута
						}
					}
				}	
			}
			// диагональные атаки
			if(moveIterator.x - 1 >= 0 && moveIterator.y + 1 <= 7) {
				if(occupying.getBoard().getSquare(moveIterator.x - 1, moveIterator.y + 1).getOccupier() != null &&
						occupying.getBoard().getSquare(moveIterator.x - 1, moveIterator.y + 1).getOccupier().getColor() != getColor()) {
					moves.add(new Point(moveIterator.x - 1, moveIterator.y + 1)); // атака вниз и влево
				}
			}
			if(moveIterator.x + 1 <= 7 && moveIterator.y + 1 <= 7) {
				if(occupying.getBoard().getSquare(moveIterator.x + 1, moveIterator.y + 1).getOccupier() != null &&
						occupying.getBoard().getSquare(moveIterator.x + 1, moveIterator.y + 1).getOccupier().getColor() != getColor()) {
					moves.add(new Point(moveIterator.x + 1, moveIterator.y + 1));// атака вниз и вправо
				}
			}	
		}
		
		else if(getColor() == "Белые") {
			if(moveIterator.y - 1 >= 0) {
				if(occupying.getBoard().getSquare(moveIterator.x, moveIterator.y - 1).getOccupier() == null) {
					moves.add(new Point(moveIterator.x, moveIterator.y - 1));
					if(this.isMoved() == false && moveIterator.y - 2 >= 0 &&
							occupying.getBoard().getSquare(moveIterator.x, moveIterator.y - 2).getOccupier() == null) {
						moves.add(new Point(moveIterator.x, moveIterator.y - 2)); // движение вверх, если пешка не находится около верхнего края доски и в клетке нет фигуры
					}
				}
			}
			//diagonal attacks
			if(moveIterator.x - 1 >= 0 && moveIterator.y - 1 >= 0) { 
				if(occupying.getBoard().getSquare(moveIterator.x - 1, moveIterator.y - 1).getOccupier() != null &&
						occupying.getBoard().getSquare(moveIterator.x - 1, moveIterator.y - 1).getOccupier().getColor() != getColor()) {
					moves.add(new Point(moveIterator.x - 1, moveIterator.y - 1)); // атака вверх и влево
				}
			}
			if(moveIterator.x + 1 <= 7 && moveIterator.y - 1 >= 0) {
				if(occupying.getBoard().getSquare(moveIterator.x + 1, moveIterator.y - 1).getOccupier() != null &&
						occupying.getBoard().getSquare(moveIterator.x + 1, moveIterator.y - 1).getOccupier().getColor() != getColor()) {
					moves.add(new Point(moveIterator.x + 1, moveIterator.y - 1)); // атака вверх и вправо
				}
			}	
		}
		return moves;
	}
}
