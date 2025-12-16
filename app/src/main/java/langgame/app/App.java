package langgame.app;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TileTypes tileTypes = new TileTypes();
                String[][] initialTiles = {
                        { "grass", "water", "sand" },
                        { "sand", "grass", "water" },
                        { "water", "sand", "grass" }
                };
                Grid grid = new Grid(initialTiles, tileTypes);
                Entity player = new Entity();
                HashMap<String, Entity> entities = new HashMap<>();
                entities.put("player", player);
                Window window = new Window("testing", grid, entities);
                window.addKeyHeldListener(
                        KeyStroke.getKeyStroke("UP"),
                        new AbstractAction() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                player.move(0, -1);
                            }
                        });
                window.addKeyHeldListener(
                        KeyStroke.getKeyStroke("DOWN"),
                        new AbstractAction() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                player.move(0, 1);
                            }
                        });
                window.addKeyHeldListener(
                        KeyStroke.getKeyStroke("LEFT"),
                        new AbstractAction() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                player.move(-1, 0);
                            }
                        });
                window.addKeyHeldListener(
                        KeyStroke.getKeyStroke("RIGHT"),
                        new AbstractAction() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                player.move(1, 0);
                            }
                        });
            }
        });
    }
}
