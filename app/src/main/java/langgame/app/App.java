package langgame.app;

import java.awt.event.ActionEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Path rootPath = Paths.get(args[0]);
                Path mainFile = rootPath.resolve("game");
                Path mapFile = rootPath.resolve("map");
                Path assetsPath = rootPath.resolve("images");

                try {
                    String initialTiles = Files.readString(mapFile);

                    TileTypes tileTypes = new TileTypes();
                    Grid grid = new Grid(initialTiles, tileTypes);
                    HashMap<String, Entity> entities = new HashMap<>();
                    KeyHandler keyHandler = new KeyHandler();
                    String title = "The Game Ever";

                    Scanner scanner = new Scanner(Files.newBufferedReader(mainFile));

                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine().trim();
                        if (line.isEmpty()) {
                            continue;
                        }

                        List<String> parts = Arrays.asList(line.split("\\s+"));
                        int idx = parts.indexOf("is");
                        if (idx != -1) {
                            String name = String.join(" ", parts.subList(0, idx));
                            switch (name) {
                                case "this game":
                                    title = String.join(" ", parts.subList(idx + 1, parts.size()));
                                    break;
                                case "the main character":
                                    String characterName = String.join(" ", parts.subList(idx + 1, parts.size()));
                                    Entity player = new Entity();
                                    entities.put(characterName, player);
                                    break;
                                default:
                                    if (tileTypes.hasTile(name)) {
                                        Tile tile = tileTypes.getTile(name);
                                        for (String property : String.join(" ", parts.subList(idx + 1, parts.size()))
                                                .split(" and ")) {
                                            // TODO: Handle more properties
                                        }
                                    }
                            }
                            continue;
                        }

                        idx = parts.indexOf("moves");
                        if (idx != -1) {
                            if (parts.get(idx + 1).equals("with")) {
                                String characterName = String.join(" ", parts.subList(0, idx));
                                Entity entity = entities.get(characterName);
                                int[][] directions = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
                                List<String> keys = parts.subList(idx + 2, parts.size());

                                if (keys.get(0).equals("arrows")) {
                                    keys = Arrays.asList("up", "left", "down", "right");
                                }
                                for (int i = 0; i < 4; i++) {
                                    KeyStroke keyStroke = KeyStroke.getKeyStroke(keys.get(i).toUpperCase());
                                    final int dx = directions[i][0];
                                    final int dy = directions[i][1];
                                    keyHandler.addKeyHeldListener(
                                            keyStroke,
                                            new AbstractAction() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    entity.move(dy, dx);
                                                }
                                            });
                                }
                            }
                        }
                    }

                    scanner.close();

                    Window window = new Window(title, grid, entities, keyHandler);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
