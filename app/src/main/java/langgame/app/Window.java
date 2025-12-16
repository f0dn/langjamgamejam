package langgame.app;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Window extends JFrame implements ActionListener {

    private final int FPS = 60;
    private final int FRAME_TIME = 1000 / FPS;
    private final Timer timer;
    private GamePanel gamePanel;
    private KeyHandler keyHandler = new KeyHandler();

    private class GamePanel extends JPanel {
        private Grid grid;
        private HashMap<String, Entity> entities;

        public GamePanel(Grid grid, HashMap<String, Entity> entities) {
            this.grid = grid;
            this.entities = entities;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            grid.paintComponent(g);
            for (Entity entity : entities.values()) {
                entity.paintComponent(g);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return grid.getPreferredSize();
        }

        private void setGrid(Grid grid) {
            this.grid = grid;
        }
    }

    public Window(String title, Grid grid, HashMap<String, Entity> entities, KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
        this.gamePanel = new GamePanel(grid, entities);
        this.addKeyListener(this.keyHandler);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(gamePanel);
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
        timer = new Timer(FRAME_TIME, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.update();
    }

    private void update() {
        this.keyHandler.update();
        this.repaint();
    }

    public void setGrid(Grid grid) {
        this.gamePanel.setGrid(grid);
        this.repaint();
    }
}
