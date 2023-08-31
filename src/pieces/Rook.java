package pieces;

import game.Board;
import game.Color;
import utils.Pair;
import utils.PieceHelper;

import java.util.HashSet;
import java.util.Set;

public class Rook extends MoveFactoredPiece {
    private int movesCount = 0;
    public Rook(int xLine, int yLine, Color color, Board board) {
        super(xLine, yLine, color, board);
        this.rank = 5;
        this.type = PieceType.Rook;
    }

    @Override
    public Set<Pair<Integer, Integer>> generatePossibleMoves() {
        Set<Pair<Integer, Integer>> res = new HashSet<>();
        PieceHelper.rookTypeMovement(res, board, this.xLine, this.yLine, false);

        return res;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void setMovesCount(int movesCount) {
        this.movesCount = movesCount;
    }
}
