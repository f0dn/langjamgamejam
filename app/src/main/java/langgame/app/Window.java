package langgame.app;

import javax.swing.JFrame;

public class Window extends JFrame {

    public Window(String title, int width, int height) {
        this.setTitle(title);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
