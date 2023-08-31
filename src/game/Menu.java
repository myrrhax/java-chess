package game;

import pieces.PieceType;
import utils.ColorUtils;
import utils.Colors;
import utils.ImageUtil;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private int firstPlayerScore;
    private int secondPlayerScore;
    public static final int HEIGHT = 125;
    private JLabel firstPlayerText;
    private JLabel secondPlayerText;

    public Menu() {
        this.setPreferredSize(new Dimension(Board.WIDTH * Square.SQUARE_WIDTH, HEIGHT));
        this.setVisible(true);
        this.setLayout(new GridLayout(1, 2));
        this.setBackground(ColorUtils.getColor(Colors.BLACK_SQUARE_COLOR));

        // first player gui
        JLabel firstPlayerLabel = new JLabel();
        firstPlayerLabel.setOpaque(true);
        firstPlayerLabel.setLayout(new GridLayout(1, 2));
        JLabel firstPlayerIcon = new JLabel();
        firstPlayerIcon.setIcon(ImageUtil.getPieceIcon(PieceType.Pawn, Color.White));
        firstPlayerLabel.add(firstPlayerIcon);
        firstPlayerText = new JLabel();
        firstPlayerText.setText("0");
        firstPlayerLabel.add(firstPlayerText);

        // second player gui
        JLabel secondPlayerLabel = new JLabel();
        secondPlayerLabel.setOpaque(true);
        secondPlayerLabel.setLayout(new GridLayout(1, 2));
        JLabel secondPlayerIcon = new JLabel();
        secondPlayerIcon.setIcon(ImageUtil.getPieceIcon(PieceType.Pawn, Color.Black));
        secondPlayerLabel.add(secondPlayerIcon);
        secondPlayerText = new JLabel();
        secondPlayerText.setText("0");
        secondPlayerLabel.add(secondPlayerText);

        this.add(firstPlayerLabel);
        this.add(secondPlayerLabel);

    }

    private void updateScore() {
        this.firstPlayerText.setText(String.format("%d", firstPlayerScore - secondPlayerScore));
        this.secondPlayerText.setText(String.format("%d", secondPlayerScore - firstPlayerScore));
    }

    public int getFirstPlayerScore() {
        return firstPlayerScore;
    }

    public int getSecondPlayerScore() {
        return secondPlayerScore;
    }

    public void setFirstPlayerScore(int firstPlayerScore) {
        this.firstPlayerScore = firstPlayerScore;
        updateScore();
    }

    public void setSecondPlayerScore(int secondPlayerScore) {
        this.secondPlayerScore = secondPlayerScore;
        updateScore();
    }
}
