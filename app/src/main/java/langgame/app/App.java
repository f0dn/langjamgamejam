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
                new Window("testing", grid, entities);
            }
        });
    }
}
