package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;

public class Practice extends JFrame {
    public static void getPractice(String username, String language) {

        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username, language);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("PRACTICE");

    }

}