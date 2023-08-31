package pieces;

import game.Board;
import game.Color;
import game.Square;
import utils.Pair;

import java.util.Set;

public abstract class MoveFactoredPiece extends Piece {
    protected int movesCount = 0;
    public MoveFactoredPiece(int xLine, int yLine, Color color, Board board) {
        super(xLine, yLine, color, board);
    }

    @Override
    public boolean move(Pair<Integer, Integer> move, Set<Pair<Integer, Integer>> possibleMoves) {
        if (!super.move(move, possibleMoves)) return false;
        this.setMovesCount(this.getMovesCount() + 1);
        return true;
    }

    @Override
    protected void undoMove(Square oldSq, Square newSq, Piece newSqPiece) {
        super.undoMove(oldSq, newSq, newSqPiece);

    }

    public int getMovesCount() {
        return movesCount;
    }

    public void setMovesCount(int movesCount) {
        this.movesCount = movesCount;
    }
}
