package langgame.app;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

class Grid extends JPanel {
    private Tile[][] tiles;
    private TileTypes tileTypes;

    public Grid(String s, TileTypes tileTypes) {
        this.tileTypes = tileTypes;
        String[] rows = s.split("\n");
        this.tiles = new Tile[rows.length][];
        for (int row = 0; row < rows.length; row++) {
            String[] cols = rows[row].split("\\s+");
            this.tiles[row] = new Tile[cols.length];
            for (int col = 0; col < cols.length; col++) {
                this.tiles[row][col] = tileTypes.getTile(cols[col]);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                Tile tile = tiles[row][col];

                g.setColor(tile.getColor());
                g.fillRect(col * 32, row * 32, 32, 32);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // TODO: Make tile size configurable
        return new Dimension(tiles[0].length * 32, tiles.length * 32);
    }

    public Tile getTile(int row, int col) {
        if (row >= 0 && row < tiles.length && col >= 0 && col < tiles[row].length) {
            return tiles[row][col];
        }
        return null;
    }

    public void setTile(int row, int col, Tile tile) {
        if (row >= 0 && row < tiles.length && col >= 0 && col < tiles[row].length) {
            tiles[row][col] = tile;
        }
    }
}
