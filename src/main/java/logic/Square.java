package logic;

import pieces.Piece;

import javax.swing.*;
import java.awt.*;

//TODO перевести
public class Square extends JButton {

	private Piece occupier;
	private Point position;
	private Board board;
	private boolean highlighted;
	private Color origColor;
	
	static Dimension size = new Dimension(48, 48);
	
	/** A constructor for a logic.Square object
	 * @param board The chess board that the logic.Square makes up
	 * @param position The location of the logic.Square on the chess board
	 */
	public Square(Board board, Point position) {
		this.setBoard(board);
		this.setOccupier(null);
		this.setPosition(position);
		this.setPreferredSize(size);
		this.setHighlighted(false);
	}


	/** The getter for the pieces.Piece occupier variable
	 * @return the piece occupying the logic.Square
	 */
	public Piece getOccupier() {
		return occupier;
	}

	/** The setter for the pieces.Piece occupier variable
	 * @param occupier The pieces.Piece to occupy the logic.Square
	 */
	public void setOccupier(Piece occupier) {
		this.occupier = occupier;
		
		if(this.occupier != null) {
			setIcon(this.occupier.getIcon());
		}
	}

	/** The getter for the Point position variable
	 * @return the point in the logic.Square
	 */
	public Point getPosition() {
		return position;
	}

	/** The setter for the Point position variable
	 * @param position The Point of the logic.Square
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/** The getter for the logic.Board variable that the logic.Square makes up
	 * @return the board that the logic.Square makes up
	 */
	public Board getBoard() {
		return board;
	}

	/** The setter for the logic.Board variable
	 * @param board the logic.Board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}


	/** returns whether the logic.Square is highlighted or not
	 * @return if the logic.Square is highlighted or not
	 */
	public boolean isHighlighted() {
		return highlighted;
	}


	/**Sets whether the logic.Square is highlighted or not
	 * @param highlighted sets whether the logic.Square is highlighted or not
	 */
	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}


	/** Returns the original color of the logic.Square
	 * @return orignial color
	 */
	public Color getOrigColor() {
		return origColor;
	}


	/** Sets the orignial color of the square
	 * @param origColor the color to set as teh orignial color of the square
	 */
	public void setOrigColor(Color origColor) {
		this.origColor = origColor;
	}

}	