package pieces;

import game.Board;
import game.Color;
import game.Square;
import utils.Pair;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {
    public Knight(int xLine, int yLine, Color color, Board board) {
        super(xLine, yLine, color, board);
        this.type = PieceType.Knight;
        this.rank = 3;
    }

    @Override
    public Set<Pair<Integer, Integer>> generatePossibleMoves() {
        Set<Pair<Integer, Integer>> res = new HashSet<>();

        Square[][] squares = board.getSquares();
        Pair<Integer, Integer> p;

        for (int i = 0; i < 2; i++) {
            int vert = i % 2 == 0 ? 1 : -1;
            int hor;
            for (int j = 0; j < 2; j++) {
                hor = j % 2 == 0 ? 1 : -1;
                p = new Pair<>(xLine + vert * 2, yLine + hor);
                if (checkMapCollisions(p.getX(), p.getY())) {
                    Square sq = squares[p.getX()][p.getY()];
                    if (sq.getPiece() == null || !sq.getPiece().getColor().equals(this.color)) {
                        res.add(p);
                    }
                }
            }
        }

        for (int i = 0; i < 2; i++) {
            int vert = i % 2 == 0 ? 1 : -1;
            int hor;
            for (int j = 0; j < 2; j++) {
                hor = j % 2 == 0 ? 1 : -1;
                p = new Pair<>(xLine + hor, yLine + vert * 2);
                if (checkMapCollisions(p.getX(), p.getY())) {
                    Square sq = squares[p.getX()][p.getY()];
                    if (sq.getPiece() == null || !sq.getPiece().getColor().equals(this.color)) {
                        res.add(p);
                    }
                }
            }
        }

        return res;
    }

    private boolean checkMapCollisions(int x, int y) {
        return x >= 0 && x < Board.WIDTH && y >= 0 && y < Board.HEIGHT;
    }
}
