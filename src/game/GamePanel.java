package game;

import pieces.Piece;
import utils.ColorUtils;
import utils.Colors;
import utils.ImageUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final int BORDER_THICKNESS = 1;
    private static Application app;
    private final JLabel[][] grid = new JLabel[8][8];
    public GamePanel() {
        Board board = new Board();
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(Square.SQUARE_WIDTH * Board.WIDTH, Square.SQUARE_HEIGHT * Board.HEIGHT));
        this.setLayout(new GridLayout(Board.WIDTH, Board.HEIGHT));
        MouseEventListener.setPanels(grid);
        MouseEventListener.setBoard(board);
        for (int i = 0; i < Board.HEIGHT; i++) {
            for (int j = 0; j < Board.WIDTH; j++) {
                JLabel label = new JLabel();
                label.setVisible(true);
                label.setBorder(new LineBorder(java.awt.Color.BLACK, BORDER_THICKNESS));
                label.setSize(Square.SQUARE_WIDTH, Square.SQUARE_HEIGHT);
                Square sq = board.getSquares()[i][j];
                label.setOpaque(true);
                label.addMouseListener(new MouseEventListener(i, j));
                label.setBackground(sq.getColor() == Color.Black
                        ? ColorUtils.getColor(Colors.BLACK_SQUARE_COLOR)
                        : ColorUtils.getColor(Colors.WHITE_SQUARE_COLOR));
                if (sq.getPiece() != null) {
                    label.setIcon(ImageUtil.getPieceIcon(sq.getPiece().getType(), sq.getPiece().getColor()));
                }
                grid[i][j] = label;
                this.add(label);
            }
        }
        this.setVisible(true);
    }

    public static void restart() {
        app.restart();
    }

    public static void setApp(Application app) {
        GamePanel.app = app;
    }
}
