package langgame.app;

import java.awt.Color;

class Tile {
    private String type;

    // TODO: load from sprite
    private Color color;

    public Tile(String type) {
        this.type = type;
        this.color = new Color((int)(Math.random() * 0x1000000));
    }

    public String getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
