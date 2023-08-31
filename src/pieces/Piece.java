package pieces;

import game.Board;
import game.Color;
import game.Square;
import utils.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Piece {
    protected PieceType type;
    protected Board board;
    protected int xLine;
    protected int yLine;
    protected Color color;
    protected int rank;
    protected Set<Pair<Integer, Integer>> cachedMoves;
    public Piece(int xLine, int yLine, Color color, Board board) {
        this.xLine = xLine;
        this.yLine = yLine;
        this.color = color;
        this.board = board;
        this.cachedMoves = new HashSet<>();
    }

    public boolean move(Pair<Integer, Integer> move, Set<Pair<Integer, Integer>> possibleMoves) {
        if (!possibleMoves.contains(move)) return false;
        Square oldSqr = board.getSquares()[this.getxLine()][this.getyLine()];
        oldSqr.setPiece(null);
        this.setyLine(move.getY());
        this.setxLine(move.getX());
        Square newSqr = board.getSquares()[this.getxLine()][this.getyLine()];
        if (newSqr.getPiece() != null) take(newSqr);
        newSqr.setPiece(this);

        return true;
    }

    protected void undoMove(Square oldSq, Square newSq, Piece newSqPiece) {
        newSq.setPiece(newSqPiece);
        if (newSqPiece != null) {
            List<Piece> pieces = newSqPiece.getColor().equals(Color.Black)
                    ? board.getBlackPieces()
                    : board.getWhitePieces();
            pieces.add(newSqPiece);

        }
        // check if king castled
        if (this instanceof King king && king.getMovesCount() - 1 == 0) {
            Pair<Integer, Integer> startPos = this.color.equals(Color.Black)
                    ? King.BLACK_START
                    : King.WHITE_START;
            int line = this.color.equals(Color.Black)
                    ? 0
                    : Board.HEIGHT - 1;
            if (oldSq.getCoords().equals(startPos)) {
                // long castled
                if (newSq.getCoords().equals(new Pair<>(line, 2))) {
                    System.out.println("Trying to undo long castle");
                    Piece rook = board.getSquares()[line][3].getPiece();
                    rook.undoMove(board.getSquares()[line][0], board.getSquares()[line][3], null);
                }

                // short castled
                else if (newSq.getCoords().equals(new Pair<>(line, Board.WIDTH - 2))) {
                    System.out.println("Trying to undo short castle");
                    Piece rook = board.getSquares()[line][Board.WIDTH - 3].getPiece();
                    rook.undoMove(board.getSquares()[line][Board.WIDTH - 1], board.getSquares()[line][Board.WIDTH - 3], null);
                }
            }
        }
        this.setxLine(oldSq.getCoords().getX());
        this.setyLine(oldSq.getCoords().getY());
        oldSq.setPiece(this);
        if (this instanceof MoveFactoredPiece mv) mv.setMovesCount(mv.getMovesCount() - 1);
    }

    public abstract Set<Pair<Integer, Integer>> generatePossibleMoves();

    public Pair<Integer, Integer> getCoords() {
        return new Pair<>(xLine, yLine);
    }

    public PieceType getType() {
        return type;
    }

    public int getxLine() {
        return xLine;
    }

    public void setxLine(int xLine) {
        this.xLine = xLine;
    }

    public int getyLine() {
        return yLine;
    }

    public void setyLine(int yLine) {
        this.yLine = yLine;
    }

    public int getRank() {
        return rank;
    }

    public Color getColor() {
        return color;
    }

    public Set<Pair<Integer, Integer>> validate(Set<Pair<Integer, Integer>> moves) {
        Set<Pair<Integer, Integer>> result = new HashSet<>(moves);
        Piece king = this.getColor().equals(Color.Black) ? this.board.getBlackKing() : board.getWhiteKing();
        List<Piece> enemies = this.getColor().equals(Color.Black) ? board.getWhitePieces() : board.getBlackPieces();

        Square currentSquare = board.getSquares()[this.getxLine()][this.getyLine()];

        for (Pair<Integer, Integer> move : moves) {
            Square newSquare = board.getSquares()[move.getX()][move.getY()];
            Piece newSqPiece = newSquare.getPiece();
            this.move(move, moves);
            for (Piece enemy : enemies) {
                Set<Pair<Integer, Integer>> enemiesPossibleMoves = enemy.generatePossibleMoves();
                if (this instanceof King && enemiesPossibleMoves.contains(this.getCoords())
                    || enemiesPossibleMoves.contains(king.getCoords())) {
                    result.remove(move);
                    break;
                }
            }
            this.undoMove(currentSquare, newSquare, newSqPiece);
        }
        return result;
    }

    public void take(Square square) {
        Piece takenPiece = square.getPiece();
        List<Piece> enemyPieceCollection = takenPiece.getColor().equals(Color.Black) ? board.getBlackPieces()
                : board.getWhitePieces();
        enemyPieceCollection.remove(takenPiece);

    }

    public Set<Pair<Integer, Integer>> getCachedMoves() {
        return cachedMoves;
    }

    public void setCachedMoves(Set<Pair<Integer, Integer>> cachedMoves) {
        this.cachedMoves = cachedMoves;
    }
}
