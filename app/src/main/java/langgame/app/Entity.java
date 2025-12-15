package langgame.app;

import javax.swing.JPanel;

class Entity extends JPanel {
    private int x;
    private int y;

    public Entity() {
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        // TODO: Load from sprite
        g.setColor(java.awt.Color.RED);
        g.fillOval(x, y, 32, 32);
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        return new java.awt.Dimension(32, 32);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }
}
