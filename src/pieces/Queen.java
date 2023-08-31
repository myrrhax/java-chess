package pieces;

import game.Board;
import game.Color;
import utils.Pair;
import utils.PieceHelper;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {
    public Queen(int xLine, int yLine, Color color, Board board) {
        super(xLine, yLine, color, board);
        this.type = PieceType.Queen;
        this.rank = 8;
    }

    @Override
    public Set<Pair<Integer, Integer>> generatePossibleMoves() {
        Set<Pair<Integer, Integer>> moves = new HashSet<>();

        PieceHelper.rookTypeMovement(moves, board, this.xLine, this.yLine, false);
        PieceHelper.bishopTypeMovements(moves, board, this.xLine, this.yLine, false);

        return moves;
    }
}
