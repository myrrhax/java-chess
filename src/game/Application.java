package game;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    private GamePanel gamePanel;
    public void start() {
        GamePanel.setApp(this);

        gamePanel = new GamePanel();
        this.setSize(new Dimension(Board.WIDTH * Square.SQUARE_WIDTH, Board.HEIGHT * Square.SQUARE_HEIGHT + Menu.HEIGHT));
        this.setResizable(false);
        this.setTitle("Chess");
        this.setVisible(true);
   //     Menu menu = new Menu();
     //   this.add(menu, BorderLayout.NORTH);
        this.add(gamePanel);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void restart() {
        this.remove(gamePanel);
        gamePanel = new GamePanel();
        this.add(gamePanel);
    }
}
