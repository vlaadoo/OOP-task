package logic;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class Board extends JPanel {

    public Square[][] chessBoard;
    public static double steps = 0;


    public Board() {
        chessBoard = new Square[8][8];
        setLayout(new GridLayout(8, 8));
        boardInit();
    }


    public void boardInit() {
        removeAll();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {

                this.chessBoard[x][y] = new Square(this, new Point(x, y));

                if ((x + y) % 2 == 0) {
                    this.chessBoard[x][y].setBackground(Color.white);
                    this.chessBoard[x][y].setOrigColor(Color.white);
                    this.chessBoard[x][y].setOpaque(true);
                    this.chessBoard[x][y].setBorderPainted(false);
                } else {
                    this.chessBoard[x][y].setBackground(Color.gray);
                    this.chessBoard[x][y].setOrigColor(Color.gray);
                    this.chessBoard[x][y].setOpaque(true);
                    this.chessBoard[x][y].setBorderPainted(false);
                }

                Piece p = null;
                String color = null;

                if (y == 0 || y == 1) {
                    color = "Черные"; // Присвается черный цвет фигурам, находящимся на стороне черных (y = 0 и y = 1)
                } else if (y == 6 || y == 7) {
                    color = "Белые"; // Присвается белый цвет фигурам, находящимся на стороне белых (y = 6 и y = 7)
                }

                if (y == 1 || y == 6) {
                    p = new Pawn(chessBoard[x][y], color);
                }
                if (y == 0 || y == 7) {
                    switch (x) {
                        case 0:
                        case 7:
                            p = new Rook(chessBoard[x][y], color);
                            break;
                        case 1:
                        case 6:
                            p = new Knight(chessBoard[x][y], color);
                            break;
                        case 2:
                        case 5:
                            p = new Bishop(chessBoard[x][y], color);
                            break;
                        case 3:
                            p = new Queen(chessBoard[x][y], color);
                            break;
                        case 4:
                            p = new King(chessBoard[x][y], color);
                            break;
                    }
                }

                // добавление фигур на квадрат или оставлется значение пустым
                chessBoard[x][y].setOccupier(p);
                add(chessBoard[x][y]);
            }
        }
    }

    /*
     * Перемещение фигуры из одной точки в другую, возвращая true если перемещение успешное
     */
    public boolean move(Point from, Point to) {

        if (from.x <= 7 && from.x >= 0 && to.x <= 7 && to.y >= 0) {

            Square origin = getSquare(from);
            Square destination = getSquare(to);
            if (origin.getOccupier() != null) {
                List<Point> possibleMoves = origin.getOccupier().getPossibleMoves();
                if (possibleMoves.contains(to)) {
                    if (testSelfCheck(origin, destination) == false) {
                        destination.setOccupier(origin.getOccupier());
                        destination.getOccupier().setOccupying(destination);
                        origin.setOccupier(null);
                        origin.setIcon(null);
                        destination.getOccupier().setMoved(true);
                        steps++;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
     * Проверка на то, чтобы ход игрока не перевел короля в "шах"
     */
    public boolean testSelfCheck(Square from, Square to) {

        boolean check;
        Piece mover = from.getOccupier();
        Piece replaced = to.getOccupier();
        String moverColor = mover.getColor();

        Square holdReplacedPiece = new Square(from.getBoard(), new Point(-1, -1));

        holdReplacedPiece.setOccupier(replaced);
        to.setOccupier(mover);   // удаление "съеденной" фигуры и установка атакующей фигуры на это место
        from.setOccupier(null);

        check = isChecked(moverColor);  // проверка шаха на текущем игроке

        if (replaced == null) {
            to.setIcon(null);
        }
        from.setOccupier(to.getOccupier());                // замена перемещенной фигуры
        to.setOccupier(holdReplacedPiece.getOccupier());  // замена удаленной фигуры
        return check;
    }

    /*
     * Возвращает клетку в точке x, y
     */
    public Square getSquare(int x, int y) {
        if (x >= 0 && x <= 7 && y <= 7 && y >= 0) {
            return chessBoard[x][y];
        }
        return null;
    }

    public Square getSquare(Point point) {
        return getSquare(point.x, point.y);
    }

    /*
     * Определяет наличие шаха на определенной стороне
     */
    public boolean isChecked(String color) {
        Point kingLoc = kingLocation(color);
        String opponentColor = null;
        if (color.equals("Белые")) {
            opponentColor = "Черные";
        } else if (color.equals("Черные")) {
            opponentColor = "Белые";
        }

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece testPiece = getSquare(x, y).getOccupier();

                if (testPiece != null) {
                    if (testPiece.getColor().equals(opponentColor) && testPiece.getPossibleMoves().contains(kingLoc)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
     * Находит, есть ли у текущего игрока какие-либо доступные ходы
     */
    public boolean hasMoves(String color) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece testPiece = getSquare(x, y).getOccupier();

                if (testPiece != null) {
                    if (testPiece.getColor().equals(color)) {
                        List<Point> test = testPiece.getPossibleMoves();
                        if (!testPiece.getPossibleMoves().isEmpty()) {
                            Square origin = testPiece.getOccupying();
                            for (Point potentialMoves : test) {
                                Square destination = getSquare(potentialMoves);
                                if (testSelfCheck(origin, destination) == false) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /*
     * Определяет местоположение короля игрока
     */
    public Point kingLocation(String color) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece testPiece = getSquare(x, y).getOccupier();
                if (testPiece != null && testPiece.getClass().equals(King.class) && testPiece.getColor().equals(color)) {
                    return new Point(x, y);
                }
            }
        }
        return new Point(-1, -1);
    }

    /*
     * Проверяет наличие в игре ситуации, когда ни одна фигура не может ходить
     */
    public boolean isStalemate(String color) {
        boolean check = isChecked(color);
        boolean hasMoves = hasMoves(color);

        if (check == false) {
            if (hasMoves == false) {
                return true;
            }
        }
        return false;
    }

    /*
     * Проверяет сторону игрока на наличие мата
     */
    public boolean isCheckmate(String color) {
        boolean check = isChecked(color);
        boolean hasMoves = hasMoves(color);

        if (check == true) {
            if (hasMoves == false) {
                return true;
            }
        }
        return false;
    }
}
