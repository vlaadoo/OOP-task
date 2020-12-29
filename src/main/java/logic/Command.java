package logic;

import pieces.Piece;

import java.awt.*;

public class Command {

    private Piece moved;
    private Piece taken;
    private Point from;
    private Point to;

    public Command(Piece moved, Piece taken, Point from, Point to) {
        this.setMoved(moved);
        this.setTaken(taken);
        this.setFrom(from);
        this.setTo(to);
    }

    public Command(Point from, Point to) {
        this.setMoved(null);
        this.setTaken(null);
        this.setFrom(from);
        this.setTo(to);
    }

    public Piece getMoved() {
        return moved;
    }

    public void setMoved(Piece moved) {
        this.moved = moved;
    }

    public Piece getTaken() {
        return taken;
    }

    public void setTaken(Piece taken) {
        this.taken = taken;
    }

    public Point getFrom() {
        return from;
    }

    public void setFrom(Point from) {
        this.from = from;
    }

    public Point getTo() {
        return to;
    }

    public void setTo(Point to) {
        this.to = to;
    }
}
