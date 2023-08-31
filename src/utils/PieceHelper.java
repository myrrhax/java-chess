package utils;

import game.Board;
import game.Square;
import pieces.Piece;

import java.util.HashSet;
import java.util.Set;

public class PieceHelper {
    public static void rookTypeMovement(Set<Pair<Integer, Integer>> res, Board board, int xLine, int yLine,
                                        boolean lockDistance) {
        int xPointer = xLine;
        int yPointer = yLine;

        Pair<Integer, Integer> p;
        Square[][] squares = board.getSquares();
        Piece thisPiece = squares[xLine][yLine].getPiece();
        // Left horizontal line
        while (xPointer > 0) {
            Square sq = squares[--xPointer][yPointer];
            p = new Pair<>(xPointer, yPointer);
            if (sq.getPiece() == null) {
                res.add(p);
                if (lockDistance) break;
                continue;
            }
            // Take enemy's piece
            if (sq.getPiece() != null && !sq.getPiece().getColor().equals(thisPiece.getColor())) {
                res.add(p);
                if (lockDistance) break;
            }
            if (sq.getPiece() != null || lockDistance) break;
        }
        xPointer = xLine;
        // Right horizontal line
        while (xPointer < Board.HEIGHT - 1) {
            Square sq = squares[++xPointer][yPointer];
            p = new Pair<>(xPointer, yPointer);
            if (sq.getPiece() == null) {
                res.add(p);
                if (lockDistance) break;
                continue;
            }
            // Take enemy's piece
            if (sq.getPiece() != null && !sq.getPiece().getColor().equals(thisPiece.getColor())) {
                res.add(p);
                if (lockDistance) break;
            }
            if (sq.getPiece() != null || lockDistance) {
                break;
            }
        }
        xPointer = xLine;
        // Top vertical line
        while (yPointer > 0) {
            Square sq = squares[xPointer][--yPointer];
            p = new Pair<>(xPointer, yPointer);
            if (sq.getPiece() == null) {
                res.add(p);
                if (lockDistance) break;
                continue;
            }
            // Take enemy's piece
            if (sq.getPiece() != null && !sq.getPiece().getColor().equals(thisPiece.getColor())) {
                res.add(p);
                if (lockDistance) break;
            }
            if (sq.getPiece() != null || lockDistance) {
                break;
            }
        }
        yPointer = yLine;
        // Bottom vertical line
        while (yPointer < Board.WIDTH - 1) {
            Square sq = squares[xPointer][++yPointer];
            p = new Pair<>(xPointer, yPointer);
            if (sq.getPiece() == null) {
                res.add(p);
                if (lockDistance) break;
                continue;
            }
            // Take enemy's piece
            if (sq.getPiece() != null && !sq.getPiece().getColor().equals(thisPiece.getColor())) {
                res.add(p);
                if (lockDistance) break;
            }
            if (sq.getPiece() != null || lockDistance) break;
        }
    }

    public static void bishopTypeMovements(Set<Pair<Integer, Integer>> res, Board board, int xLine, int yLine,
                                           boolean lockDistance) {
        int xPointer = xLine;
        int yPointer = yLine;
        Square[][] squares = board.getSquares();
        Piece thisPiece = squares[xLine][yLine].getPiece();
        Pair<Integer, Integer> p;

        // Top left diagonal
        while (yPointer > 0 &&  xPointer > 0) {
            Square sq = squares[--xPointer][--yPointer];
            p = new Pair<>(xPointer, yPointer);
            if (sq.getPiece() == null) {
                res.add(p);
                if (lockDistance) break;
                continue;
            }
            // Take enemy's piece
            if (sq.getPiece() != null && !sq.getPiece().getColor().equals(thisPiece.getColor())) {
                res.add(p);
                if (lockDistance) break;
            }
            if (sq.getPiece() != null || lockDistance) {
                break;
            }
        }
        xPointer = xLine;
        yPointer = yLine;

        // Top right diagonal
        while (xPointer > 0 && yPointer < Board.WIDTH - 1) {
            Square sq = squares[--xPointer][++yPointer];
            p = new Pair<>(xPointer, yPointer);
            if (sq.getPiece() == null) {
                res.add(p);
                if (lockDistance) break;
                continue;
            }
            // Take enemy's piece
            if (sq.getPiece() != null && !sq.getPiece().getColor().equals(thisPiece.getColor())) {
                res.add(p);
                if (lockDistance) break;
            }
            if (sq.getPiece() != null || lockDistance) {
                break;
            }
        }

        xPointer = xLine;
        yPointer = yLine;

        // Bottom left diagonal
        while (xPointer < Board.HEIGHT - 1 && yPointer > 0) {
            Square sq = squares[++xPointer][--yPointer];
            p = new Pair<>(xPointer, yPointer);
            if (sq.getPiece() == null) {
                res.add(p);
                if (lockDistance) break;
                continue;
            }
            // Take enemy's piece
            if (sq.getPiece() != null && !sq.getPiece().getColor().equals(thisPiece.getColor())) {
                res.add(p);
                if (lockDistance) break;
            }
            if (sq.getPiece() != null || lockDistance) {
                break;
            }
        }

        xPointer = xLine;
        yPointer = yLine;

        // Bottom right diagonal
        while (xPointer < Board.HEIGHT - 1 && yPointer < Board.WIDTH - 1) {
            Square sq = squares[++xPointer][++yPointer];
            p = new Pair<>(xPointer, yPointer);
            if (sq.getPiece() == null) {
                res.add(p);
                if (lockDistance) break;
                continue;
            }
            // Take enemy's piece
            if (sq.getPiece() != null && !sq.getPiece().getColor().equals(thisPiece.getColor())) {
                res.add(p);
                if (lockDistance) break;
            }
            if (sq.getPiece() != null || lockDistance) {
                break;
            }
        }
    }
}
