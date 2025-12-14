package langgame.app;

class Grid {
    private Tile[][] tiles;
    private TileTypes tileTypes;

    public Grid(String[][] tiles, TileTypes tileTypes) {
        this.tileTypes = tileTypes;

        this.tiles = new Tile[tiles.length][];
        for (int row = 0; row < tiles.length; row++) {
            this.tiles[row] = new Tile[tiles[row].length];
            for (int col = 0; col < tiles[row].length; col++) {
                this.tiles[row][col] = tileTypes.getTile(tiles[row][col]);
            }
        }
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
