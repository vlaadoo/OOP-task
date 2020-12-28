package logic;

import pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class Square extends JButton {

	private Piece occupier;
	private Point position;
	private Board board;
	private boolean highlighted;
	private Color origColor;
	
	static Dimension size = new Dimension(48, 48);

	// Конструктор для клеток
	public Square(Board board, Point position) {
		this.setBoard(board);
		this.setOccupier(null);
		this.setPosition(position);
		this.setPreferredSize(size);
		this.setHighlighted(false);
	}


	public Piece getOccupier() {
		return occupier;
	}

	public void setOccupier(Piece occupier) {
		this.occupier = occupier;
		
		if(this.occupier != null) {
			setIcon(this.occupier.getIcon());
		}
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	public Color getOrigColor() {
		return origColor;
	}

	public void setOrigColor(Color origColor) {
		this.origColor = origColor;
	}
}	