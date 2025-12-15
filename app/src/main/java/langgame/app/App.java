package langgame.app;

import java.util.HashMap;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TileTypes tileTypes = new TileTypes();
                String[][] initialTiles = {
                    {"grass", "water", "sand"},
                    {"sand", "grass", "water"},
                    {"water", "sand", "grass"}
                };
                Grid grid = new Grid(initialTiles, tileTypes);
                Entity player = new Entity();
                HashMap<String, Entity> entities = new HashMap<>();
                entities.put("player", player);
                Window window = new Window("testing", grid, entities);
                window.attachKeyListener(
                    javax.swing.KeyStroke.getKeyStroke("UP"),
                    new javax.swing.AbstractAction() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            player.move(0, -1);
                            window.repaint();
                        }
                    }
                );
            }
        });
    }
}
