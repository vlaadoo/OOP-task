package logic;

import pieces.Piece;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Model {
	
	private Board board;
	private int whiteScore;
	private int blackScore;
	private Stack<Command> commands;
	
	public Model() {
		setWhiteScore(0);
		setBlackScore(0);
		setBoard(new Board());
		setCommands(new Stack<Command>());
	}

	public Model(int whiteScore, int blackScore) {
		setWhiteScore(whiteScore);
		setBlackScore(blackScore);
		setCommands(new Stack<Command>());
	}

	// Добавляет ActionListener на все клетки
	public void addActionListeners(ActionListener a) {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				getBoard().chessBoard[x][y].setActionCommand("square");
				getBoard().chessBoard[x][y].addActionListener(a);
			}
		}
	}

	// Передвигает фигуру с одной точки в другую
	public void move(Point from, Point to) {
		Piece moved = getBoard().getSquare(from).getOccupier();
		Piece taken = getBoard().getSquare(to).getOccupier();
		Command command = new Command(moved, taken, new Point(from), new Point(to));
		board.move(from, to);
		commands.push(command);
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