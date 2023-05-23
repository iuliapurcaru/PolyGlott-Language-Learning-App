package pages;

import awt.BuildFrame;
import awt.Buttons;
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
    }
}
