package langgame.app;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Window extends JFrame {

    private GamePanel gamePanel;

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

    public Window(String title, Grid grid, HashMap<String, Entity> entities) {
        this.gamePanel = new GamePanel(grid, entities);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(gamePanel);
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }

    public void attachKeyListener(KeyStroke keyStroke, Action action) {
        this.gamePanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, keyStroke.toString());
        this.gamePanel.getActionMap().put(keyStroke.toString(), action);
    }

    public void setGrid(Grid grid) {
        this.gamePanel.setGrid(grid);
        this.repaint();
    }
}
