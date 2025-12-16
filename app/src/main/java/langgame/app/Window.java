package langgame.app;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Window extends JFrame implements ActionListener {

    private final int FPS = 60;
    private final int FRAME_TIME = 1000 / FPS;
    private final Timer timer;
    private GamePanel gamePanel;
    private KeyHandler keyHandler = new KeyHandler();

    private class GamePanel extends JPanel {
        private Grid grid;
        private HashMap<String, Entity> entities;

        public GamePanel(Grid grid, HashMap<String, Entity> entities) {
            this.grid = grid;
            this.entities = entities;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            grid.paintComponent(g);
            for (Entity entity : entities.values()) {
                entity.paintComponent(g);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return grid.getPreferredSize();
        }

        private void setGrid(Grid grid) {
            this.grid = grid;
        }
    }

    private class KeyHandler implements KeyListener {
        private class KeyInfo {
            public boolean pressed = false;
            public ArrayList<Action> pressedActions = new ArrayList<>();
            public ArrayList<Action> releasedActions = new ArrayList<>();
            public ArrayList<Action> heldActions = new ArrayList<>();
        }

        private HashMap<KeyStroke, KeyInfo> keyBindings = new HashMap<>();

        private KeyInfo getKeyInfo(KeyStroke keyStroke) {
            // TODO: pretty hacky but whatever
            String stripped = keyStroke.toString().replace("pressed ", "").replace("released ", "");
            KeyStroke[] keyStrokes = { KeyStroke.getKeyStroke("pressed " + stripped),
                    KeyStroke.getKeyStroke("released " + stripped) };
            KeyInfo keyInfo = new KeyInfo();
            for (KeyStroke ks : keyStrokes) {
                keyBindings.putIfAbsent(ks, keyInfo);
            }
            return keyBindings.get(keyStrokes[0]);
        }

        private void addKeyPressedListener(KeyStroke keyStroke, Action action) {
            KeyInfo keyInfo = getKeyInfo(keyStroke);
            keyInfo.pressedActions.add(action);
        }

        private void addKeyReleasedListener(KeyStroke keyStroke, Action action) {
            KeyInfo keyInfo = getKeyInfo(keyStroke);
            keyInfo.releasedActions.add(action);
        }

        private void addKeyHeldListener(KeyStroke keyStroke, Action action) {
            KeyInfo keyInfo = getKeyInfo(keyStroke);
            keyInfo.heldActions.add(action);
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            KeyInfo keyInfo = keyBindings.get(KeyStroke.getKeyStrokeForEvent(e));
            if (keyInfo != null && !keyInfo.pressed) {
                for (Action action : keyInfo.pressedActions) {
                    action.actionPerformed(null);
                }
                keyInfo.pressed = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            KeyInfo keyInfo = keyBindings.get(KeyStroke.getKeyStrokeForEvent(e));
            if (keyInfo != null) {
                for (Action action : keyInfo.releasedActions) {
                    action.actionPerformed(null);
                }
                keyInfo.pressed = false;
            }
        }

        private void update() {
            for (KeyInfo keyInfo : keyBindings.values()) {
                if (keyInfo.pressed) {
                    for (Action action : keyInfo.heldActions) {
                        action.actionPerformed(null);
                    }
                }
            }
        }

    }

    public Window(String title, Grid grid, HashMap<String, Entity> entities) {
        this.gamePanel = new GamePanel(grid, entities);
        this.addKeyListener(this.keyHandler);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(gamePanel);
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
        timer = new Timer(FRAME_TIME, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.update();
    }

    public void addKeyReleasedListener(KeyStroke keyStroke, Action action) {
        this.keyHandler.addKeyReleasedListener(keyStroke, action);
    }

    public void addKeyPressedListener(KeyStroke keyStroke, Action action) {
        this.keyHandler.addKeyPressedListener(keyStroke, action);
    }

    public void addKeyHeldListener(KeyStroke keyStroke, Action action) {
        this.keyHandler.addKeyHeldListener(keyStroke, action);
    }

    private void update() {
        this.keyHandler.update();
        this.repaint();
    }

    public void setGrid(Grid grid) {
        this.gamePanel.setGrid(grid);
        this.repaint();
    }
}
