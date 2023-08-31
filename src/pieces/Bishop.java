package pieces;

import game.Board;
import game.Color;
import utils.Pair;
import utils.PieceHelper;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {
    public Bishop(int xLine, int yLine, Color color, Board board) {
        super(xLine, yLine, color, board);
        this.rank = 3;
        this.type = PieceType.Bishop;
    }

    @Override
    public Set<Pair<Integer, Integer>> generatePossibleMoves() {
        Set<Pair<Integer, Integer>> res = new HashSet<>();
        PieceHelper.bishopTypeMovements(res, board, this.xLine, this.yLine, false);

        return res;
    }
}
