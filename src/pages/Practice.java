package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.Level;

import java.awt.*;
import javax.swing.*;

public class Practice {
    public static void getPractice(String username, String language) {
        JFrame frame = BuildFrame.getFrame();
        JPanel panel = BuildFrame.getPanel();
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username, language);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("PRACTICE");

        JLabel practiceLabel = new JLabel("Practice what you've learned");
        practiceLabel.setBounds(250,160,800,35);
        practiceLabel.setFont(new Font("Century Gothic", Font.BOLD, 40));
        practiceLabel.setForeground(Color.BLACK);
        panel.add(practiceLabel);

        JButton practiceButton = new JButton("LEVEL " + Level.getLevel(username, language) + " PRACTICE");
        practiceButton.setBounds(250, 390, 500, 100);
        practiceButton.setFont(new Font("Century Gothic", Font.BOLD, 35));
        practiceButton.setForeground(Color.BLACK);
        practiceButton.setBackground(new Color(245, 212, 66));
        practiceButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(practiceButton);
    }
}
