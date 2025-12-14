package langgame.app;

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
                new Window("testing", 800, 600);
            }
        });
    }
}
