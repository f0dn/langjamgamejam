package langgame.app;

import javax.swing.JFrame;

public class Window extends JFrame {

    public Window(String title, Grid grid) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(grid);
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }
}
