package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.Level;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.*;

public class Practice {
    public static void getPractice(String username, String language) {

        JLabel optionPaneFont = new JLabel();
        optionPaneFont.setFont(new Font("Century Gothic", Font.BOLD, 23));

        JFrame frame = BuildFrame.getFrame();
        JPanel panel = BuildFrame.getPanel();
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username, language);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("PRACTICE");

        JLabel practiceLabel = new JLabel("Practice what you've learned so far");
        practiceLabel.setBounds(250, 310, 800, 55);
        practiceLabel.setFont(new Font("Century Gothic", Font.BOLD, 35));
        practiceLabel.setForeground(Color.BLACK);
        panel.add(practiceLabel);

        JButton practiceButton = new JButton("LEVEL " + Level.getLevel(username, language) + " PRACTICE");
        practiceButton.setBounds(250, 480, 500, 100);
        practiceButton.setFont(new Font("Century Gothic", Font.BOLD, 35));
        practiceButton.setForeground(Color.BLACK);
        practiceButton.setBackground(Buttons.yellowButtonColor);
        practiceButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButtonMouseAdapter(practiceButton);
        practiceButton.addActionListener(
                e -> {
                    File tmpDir = new File("content/" + language + "/level" + Level.getLevel(username, language) + "/level" + Level.getLevel(username, language) + ".csv");
                    if(!tmpDir.exists()) {
                        optionPaneFont.setText("Coming soon");
                        JOptionPane.showMessageDialog(null, optionPaneFont);
                        return;
                    }

                    frame.dispose();
                    Exercise.getExercise(username, language, "level" + Level.getLevel(username, language));
                }
        );
        panel.add(practiceButton);
    }

    public static void addButtonMouseAdapter(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(Buttons.mouseEnteredColor);
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(Buttons.yellowButtonColor);
            }
        });
    }
}
