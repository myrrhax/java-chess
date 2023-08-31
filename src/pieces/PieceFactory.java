package pieces;

import game.Board;
import game.Color;

public class PieceFactory {
    public static Piece getPiece(PieceType pt, int x, int y, Color c, Board board) {
        Piece piece = null;
        switch (pt) {
            case Pawn -> piece = new Pawn(x, y, c, board);
            case Rook -> piece = new Rook(x, y, c, board);
            case Knight -> piece = new Knight(x, y, c, board);
            case Bishop ->  piece = new Bishop(x, y, c, board);
            case King -> piece = new King(x, y, c, board);
            case Queen -> piece = new Queen(x, y, c, board);
        }

        return piece;
    }
}
