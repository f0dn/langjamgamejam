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
}
