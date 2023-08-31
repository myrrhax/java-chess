package game;

import pieces.Piece;
import utils.Pair;

import javax.swing.*;

public class Square {
    public static final int SQUARE_WIDTH = 70;
    public static final int SQUARE_HEIGHT = 70;
    private Color color;
    private Piece piece;
    private Pair<Integer, Integer> coords;
    public Square(Color color, Pair<Integer, Integer> coords) {
        this.color = color;
        this.coords = coords;
    }

    public Square(Color color, Pair<Integer, Integer> coords, Piece p) {
        this(color, coords);
        this.piece = p;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Pair<Integer, Integer> getCoords() {
        return coords;
    }

    public void setCoords(Pair<Integer, Integer> coords) {
        this.coords = coords;
    }
}
