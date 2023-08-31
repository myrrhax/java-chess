package game;

import pieces.King;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceType;
import utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final PieceType[] pieceRow = new PieceType[] {
            PieceType.Rook, PieceType.Knight, PieceType.Bishop, PieceType.Queen, PieceType.King,
            PieceType.Bishop, PieceType.Knight, PieceType.Rook
    };
    private final List<Piece> blackPieces = new ArrayList<>(16);
    private final List<Piece> whitePieces = new ArrayList<>(16);
    private final King blackKing;
    private final King whiteKing;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    private static final int KING_START_SPOT_Y = 4;
    private Square[][] squares;

    public Board() {
        squares = new Square[WIDTH][HEIGHT];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Color c = i % 2 == j % 2 ? Color.White : Color.Black;
                squares[i][j] = new Square(c, new Pair<>(i,j));
                Piece piece = null;
                if (i == 0 || i == HEIGHT - 1) {
                    piece = PieceFactory.getPiece(pieceRow[j], i, j,
                            i == 0 ? Color.Black : Color.White, this);
                    squares[i][j].setPiece(piece);
                } else if (i == 1 || i == HEIGHT - 2) {
                    piece = PieceFactory.getPiece(PieceType.Pawn, i, j,
                            i == 1 ? Color.Black : Color.White, this);
                    squares[i][j].setPiece(piece);
                }
                if (piece != null) {
                    if (piece.getColor().equals(Color.Black)) blackPieces.add(piece);
                    else whitePieces.add(piece);
                    piece = null;
                }
            }
        }

        blackKing = (King) squares[0][KING_START_SPOT_Y].getPiece();
        whiteKing = (King) squares[WIDTH - 1][KING_START_SPOT_Y].getPiece();

        for (Piece piece : whitePieces) {
            piece.setCachedMoves(piece.validate(piece.generatePossibleMoves()));
        }

        for (Piece piece : blackPieces) {
            piece.setCachedMoves(piece.validate(piece.generatePossibleMoves()));
        }

        whiteKing.setCachedMoves(whiteKing.validate(whiteKing.generatePossibleMoves()));
        blackKing.setCachedMoves(blackKing.validate(blackKing.generatePossibleMoves()));
    }

    public Square[][] getSquares() {
        return squares;
    }

    public void setPieces(Square[][] pieces) {
        this.squares = pieces;
    }

    public King getBlackKing() {
        return blackKing;
    }

    public King getWhiteKing() {
        return whiteKing;
    }

    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }

    public static int getScore(List<? extends Piece> pieces) {
        int score = 0;
        for (Piece p : pieces) {
            score += p.getRank();
        }

        return score;
    }
}
