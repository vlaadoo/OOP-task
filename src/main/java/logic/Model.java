package logic;

import logic.Board;
import logic.Command;
import logic.Square;
import pieces.Pawn;
import pieces.Piece;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Stack;

//TODO перевести
public class Model {
	
	private Board board;
	private int whiteScore;
	private int blackScore;
	private Stack<Command> commands;
	
	public Model() {
		setWhiteScore(0);
		setBlackScore(0);
		setBoard(new Board(false));
		setCommands(new Stack<Command>());
	}
	
	public Model(int whiteScore, int blackScore, boolean custom) {
		setWhiteScore(whiteScore);
		setBlackScore(blackScore);
		setBoard(new Board(custom));
		setCommands(new Stack<Command>());
	}
	
	/** Adds ActionListeners to all of the Squares
	 * @param a the ActionListener to add
	 */
	public void addActionListeners(ActionListener a) {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				getBoard().chessBoard[x][y].setActionCommand("square");
				getBoard().chessBoard[x][y].addActionListener(a);
			}
		}
	}

	/** Moves the pieces.Piece from one point to another on the board, and creates a logic.Command object to be pushed onto the commands stack to be used for the undo function
	 * @param from the origin of the piece to be moved
	 * @param to the destination of the piece to be moved
	 */
	public void move(Point from, Point to) {
		//get the pieces for the undo function
		Piece moved = getBoard().getSquare(from).getOccupier();
		Piece taken = getBoard().getSquare(to).getOccupier();
		Command command = new Command(moved, taken, new Point(from), new Point(to));
		board.move(from, to);
		commands.push(command);	//put the new data structure object into a stack
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getWhiteScore() {
		return whiteScore;
	}

	public void setWhiteScore(int whiteScore) {
		this.whiteScore = whiteScore;
	}
	
	public int getBlackScore() {
		return blackScore;
	}

	public void setBlackScore(int blackScore) {
		this.blackScore = blackScore;
	}

	public Stack<Command> getCommands() {
		return commands;
	}

	public void setCommands(Stack<Command> commands) {
		this.commands = commands;
	}
}
