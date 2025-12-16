package langgame.app;

import java.util.HashMap;

class TileTypes {
    HashMap<String, Tile> types;

    public TileTypes() {
        types = new HashMap<>();
    }

    public Tile getTile(String name) {
        if (!types.containsKey(name)) {
            types.put(name, new Tile(name));
        }

        return types.get(name);
    }

    public boolean hasTile(String name) {
        return types.containsKey(name);
    }
}
