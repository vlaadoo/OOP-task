package pieces;

import logic.Square;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class Bishop extends Piece {

    public Bishop(Square occupying, String color) {
        super(occupying, color);

        if (getColor() == "Белые") {
            this.icon = new ImageIcon("images/whitebishop.png");
        } else if (getColor() == "Черные") {
            this.icon = new ImageIcon("images/blackbishop.png");
        }
    }

    public List<Point> getPossibleMoves() {
        List<Point> moves = new ArrayList<Point>();

        Point moveIterator = occupying.getPosition();

        int startx = moveIterator.x;
        int starty = moveIterator.y;

        getDiagUpRight(moves, moveIterator, startx, starty);
        getDiagDownRight(moves, moveIterator, startx, starty);
        getDiagUpLeft(moves, moveIterator, startx, starty);
        getDiagDownLeft(moves, moveIterator, startx, starty);

        moveIterator.x = startx;
        moveIterator.y = starty;
        return moves;
    }
}
