package game;

import pieces.*;
import utils.ColorUtils;
import utils.Colors;
import utils.ImageUtil;
import utils.Pair;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Set;

public class MouseEventListener implements MouseListener {
    private final int x;
    private final int y;
    private static Board board;
    private static Color player;
    private static Piece selectedPiece;
    private static Set<Pair<Integer, Integer>> moves;
    private static JLabel[][] labels;

    static  {
        player = Color.White;
        selectedPiece = null;
    }
    public MouseEventListener(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Square sqr = board.getSquares()[x][y];

        // take piece
        if (selectedPiece == null || sqr.getPiece() != null && sqr.getPiece().getColor().equals(player)) {
            if (sqr.getPiece() == null || !sqr.getPiece().getColor().equals(player)) {
                return;
            }

            // turn off selection on double click
            if (selectedPiece != null && selectedPiece == sqr.getPiece()) {
                erasePossibleMoves();
                moves = null;
                selectedPiece = null;
                changeColorToDefault(x, y);
                return;
            }
            // Piece was reselected
            if (selectedPiece != null) {
                changeColorToDefault(selectedPiece.getxLine(), selectedPiece.getyLine());
                erasePossibleMoves();
            }
            selectedPiece = sqr.getPiece();
            moves = selectedPiece.getCachedMoves();
            drawPossibleMoves();
            labels[selectedPiece.getxLine()][selectedPiece.getyLine()].setBackground(ColorUtils.getColor(Colors.SELECTED_PIECE_COLOR));
            return;
        }

        Pair<Integer, Integer> tryMove = new Pair<>(x, y);
        int oldX = selectedPiece.getxLine();
        int oldY = selectedPiece.getyLine();

        // Can't move
        if (!selectedPiece.move(tryMove, moves)) {
            return;
        }

        boolean isCheck = checkState();

        if (isCheck) System.out.println("CHECK");

        if (noMovesState()) {
            if (isCheck) System.out.println(" MATE!");
            else System.out.println(" STALEMATE!");

            GamePanel.restart();
            return;
        }

        JLabel oldLabel = labels[oldX][oldY];
        changeColorToDefault(oldX, oldY);
        // king was moved for first time
        if (selectedPiece instanceof King king && king.getMovesCount() - 1 == 0) {
            int line = player.equals(Color.Black) ? 0 : Board.HEIGHT - 1;
            // long castled
            if (king.getCoords().equals(new Pair<>(line, 2))) {
                labels[line][0].setIcon(null);
                labels[line][3].setIcon(ImageUtil.getPieceIcon(PieceType.Rook, player));
            } else if (king.getCoords().equals(new Pair<>(line, Board.WIDTH - 2))) {
                labels[line][Board.WIDTH - 1].setIcon(null);
                labels[line][Board.WIDTH - 3].setIcon(ImageUtil.getPieceIcon(PieceType.Rook, player));
            }
        }
        if (selectedPiece instanceof Pawn pawn) {
            int queenLine = player.equals(Color.Black) ? Board.HEIGHT - 1 : 0;
            List<Piece> pieces = player.equals(Color.Black)
                    ? board.getBlackPieces()
                    : board.getWhitePieces();

            if (pawn.getxLine() == queenLine) {
                pieces.remove(pawn);
                Queen newQueen = new Queen(pawn.getxLine(), pawn.getyLine(), player, board);
                pieces.add(newQueen);
                selectedPiece = newQueen;
            }
        }
        player = player.equals(Color.White) ? Color.Black : Color.White;
        oldLabel.setIcon(null);
        labels[x][y]
                .setIcon(ImageUtil.getPieceIcon(selectedPiece.getType(), selectedPiece.getColor()));
        selectedPiece = null;
        erasePossibleMoves();
        moves = null;

    }

    private boolean noMovesState() {
        List<Piece> enemies = player.equals(Color.Black) ? board.getWhitePieces() : board.getBlackPieces();
        Piece enemyKing = player.equals(Color.Black) ? board.getWhiteKing() : board.getBlackKing();
        int c = 0;
        for (Piece enemy: enemies) {
            enemy.setCachedMoves(enemy.validate(enemy.generatePossibleMoves()));
            if (!enemy.getCachedMoves().isEmpty()) c++;
        }
        enemyKing.setCachedMoves(enemyKing.validate(enemyKing.generatePossibleMoves()));
        c = !enemyKing.getCachedMoves().isEmpty() ? c + 1 : c;
        return c == 0;
    }

    private boolean checkState() {
        List<Piece> allies = player.equals(Color.Black) ? board.getBlackPieces() : board.getWhitePieces();
        King enemyKing = player.equals(Color.Black) ? board.getWhiteKing() : board.getBlackKing();
        for (Piece ally : allies) {
            enemyKing.setCheck(false);
            ally.setCachedMoves(ally.validate(ally.generatePossibleMoves()));
            if (ally.getCachedMoves().contains(enemyKing.getCoords())) {
                enemyKing.setCheck(true);
                return true;
            }
        }
        return false;
    }

    private void drawPossibleMoves() {
        for (Pair<Integer, Integer> move : moves) {
            JLabel label = labels[move.getX()][move.getY()];
            label.setBackground(ColorUtils.getColor(Colors.POSSIBLE_MOVES_COLOR));
        }
    }

    private void erasePossibleMoves() {
        for (Pair<Integer, Integer> move : moves) {
            changeColorToDefault(move.getX(), move.getY());
        }
    }

    private void changeColorToDefault(int x, int y) {
        if (board.getSquares()[x][y].getColor().equals(Color.Black)) {
            labels[x][y].setBackground(ColorUtils.getColor(Colors.BLACK_SQUARE_COLOR));
        } else {
            labels[x][y].setBackground(ColorUtils.getColor(Colors.WHITE_SQUARE_COLOR));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static Board getBoard() {
        return board;
    }

    public static void setBoard(Board board) {
        MouseEventListener.board = board;
    }

    public static void setPanels(JLabel[][] labels) {
        MouseEventListener.labels = labels;
    }

}
