package langgame.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Action;
import javax.swing.KeyStroke;

public class KeyHandler implements KeyListener {
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

    public void addKeyPressedListener(KeyStroke keyStroke, Action action) {
        KeyInfo keyInfo = getKeyInfo(keyStroke);
        keyInfo.pressedActions.add(action);
    }

    public void addKeyReleasedListener(KeyStroke keyStroke, Action action) {
        KeyInfo keyInfo = getKeyInfo(keyStroke);
        keyInfo.releasedActions.add(action);
    }

    public void addKeyHeldListener(KeyStroke keyStroke, Action action) {
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

    public void update() {
        for (KeyInfo keyInfo : keyBindings.values()) {
            if (keyInfo.pressed) {
                for (Action action : keyInfo.heldActions) {
                    action.actionPerformed(null);
                }
            }
        }
    }

}
