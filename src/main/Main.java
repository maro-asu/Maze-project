package src.main;

import javax.swing.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Maze Game");
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}