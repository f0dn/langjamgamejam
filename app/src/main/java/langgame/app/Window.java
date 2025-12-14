package langgame.app;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {

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
    }

    public Window(String title, Grid grid, HashMap<String, Entity> entities) {
        GamePanel gamePanel = new GamePanel(grid, entities);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(gamePanel);
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }
}
