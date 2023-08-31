package pieces;

import game.Board;
import game.Color;
import game.Square;
import utils.Pair;
import utils.PieceHelper;

import java.util.HashSet;
import java.util.Set;

// ToDo restart
// ToDo fix castling 
// ToDo en passant
// ToDo panel for stalemate/mate

public class King extends MoveFactoredPiece {
    public static final Pair<Integer, Integer> WHITE_START = new Pair<>(Board.HEIGHT - 1, 4);
    public static final Pair<Integer, Integer> BLACK_START = new Pair<>(0, 4);
    private int movesCount = 0;
    private boolean check = false;
    public King(int xLine, int yLine, Color color, Board board) {
        super(xLine, yLine, color, board);
        this.type = PieceType.King;
        this.rank = 0;
    }

    @Override
    public boolean move(Pair<Integer, Integer> move, Set<Pair<Integer, Integer>> possibleMoves) {
        if (!super.move(move, possibleMoves)) return false;
        int line = this.color.equals(Color.Black) ? 0 : Board.HEIGHT - 1;
        // queen side
        if (move.equals(new Pair<>(line, 2))) {
            System.out.println("TRYING TO LONG CASTLE");
            Rook rook = (Rook) board.getSquares()[line][0].getPiece();
            Pair<Integer, Integer> rookCastleMove = new Pair<>(line, 3);
            rook.getCachedMoves().add(rookCastleMove);
            rook.move(rookCastleMove, rook.getCachedMoves());
        }
        // king side
        else if (move.equals(new Pair<>(line, Board.WIDTH - 2))) {
            System.out.println("TRYING TO SHORT CASTLE");
            Rook rook = (Rook) board.getSquares()[line][Board.WIDTH - 1].getPiece();
            Pair<Integer, Integer> rookCastleMove = new Pair<>(line, Board.WIDTH - 3);
            rook.getCachedMoves().add(rookCastleMove);
            rook.move(rookCastleMove, rook.getCachedMoves());
        }

        return true;
    }

    @Override
    public Set<Pair<Integer, Integer>> generatePossibleMoves() {
        Set<Pair<Integer, Integer>> moves = new HashSet<>();
        PieceHelper.bishopTypeMovements(moves, board, this.xLine, this.yLine, true);
        PieceHelper.rookTypeMovement(moves, board, this.xLine, this.yLine, true);
        castleMoves(moves);

        return moves;
    }

    private void castleMoves(Set<Pair<Integer, Integer>> moves) {
        if (this.isCheck() || this.movesCount != 0) return;
        Piece leftRook = this.color.equals(Color.Black)
                ? board.getSquares()[0][0].getPiece()
                : board.getSquares()[Board.HEIGHT - 1][0].getPiece();
        Piece rightRook = this.color.equals(Color.Black)
                ? board.getSquares()[0][Board.WIDTH - 1].getPiece()
                : board.getSquares()[Board.HEIGHT - 1][Board.WIDTH - 1].getPiece();

        if (leftRook == null && rightRook == null) return;
        Square[][] squares = board.getSquares();
        int line = this.color.equals(Color.Black) ? 0 : Board.HEIGHT - 1;

        // long castle
        if (leftRook instanceof Rook rook) {

            if (squares[line][1].getPiece() == null
                    && squares[line][2].getPiece() == null
                    && squares[line][3].getPiece() == null
                    && rook.getMovesCount() == 0
                ) {
                moves.add(new Pair<>(line, 2));
            }
        }

        // short castle
        if (rightRook instanceof Rook rook) {
            if (squares[line][5].getPiece() == null
                    && squares[line][6].getPiece() == null
                    && rook.getMovesCount() == 0
            ) {
                moves.add(new Pair<>(line, 6));
            }
        }
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void setMovesCount(int movesCount) {
        this.movesCount = movesCount;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
