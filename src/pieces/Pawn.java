package pieces;

import game.Board;
import game.Color;
import game.MouseEventListener;
import game.Square;
import utils.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pawn extends MoveFactoredPiece {
    private int movesCount = 0;
    private static final int MAX_FRONT_MOVE = 1;
    public Pawn(int xLine, int yLine, Color color, Board board) {
        super(xLine, yLine, color, board);
        this.type = PieceType.Pawn;
        this.rank = 1;
    }

    @Override
    public boolean move(Pair<Integer, Integer> move, Set<Pair<Integer, Integer>> possibleMoves) {
        if (!super.move(move, possibleMoves)) return false;
        return true;
    }

    @Override
    public Set<Pair<Integer, Integer>> generatePossibleMoves() {
        Set<Pair<Integer, Integer>> moves = new HashSet<>();
        int dir = this.color.equals(Color.Black) ? 1 : -1;
        int move = MAX_FRONT_MOVE * dir;
        if (this.getxLine() > 0 && this.getxLine() < Board.HEIGHT - 1) {
            Square sq = board.getSquares()[getxLine() + move][getyLine()];
            // move forward
            if (sq.getPiece() == null) {
                moves.add(new Pair<>(getxLine() + move, getyLine()));
            }
            if (getyLine() > 0) {
                // beat left top enemy
                sq = board.getSquares()[getxLine() + move][getyLine() - 1];
                if (sq.getPiece() != null && !sq.getPiece().getColor().equals(this.color)) {
                    moves.add(new Pair<>(getxLine() + move, getyLine() - MAX_FRONT_MOVE));
                }
            }
            if (getyLine() < Board.WIDTH - 1) {
                sq = board.getSquares()[getxLine() + move][getyLine() + 1];
                if (sq.getPiece() != null && !sq.getPiece().getColor().equals(this.color)) {
                    moves.add(new Pair<>(getxLine() + move, getyLine() + MAX_FRONT_MOVE));
                }
            }
            if (movesCount == 0) {
                sq = board.getSquares()[getxLine() + move * 2][yLine];
                if (sq.getPiece() == null) {
                    moves.add(new Pair<>(getxLine() + move * 2, getyLine()));
                }
            }
        }

        return moves;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void setMovesCount(int movesCount) {
        this.movesCount = movesCount;
    }
}
