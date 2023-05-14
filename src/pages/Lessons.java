package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLOutput;

public class Lessons extends JFrame {

    static Color buttonColor = new Color(245, 117, 5);
    static Color mouseEnteredColor = new Color(255, 143, 23);
    public static void getLessons(String username, String language) {

        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username, language);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("LESSONS");

        JButton beginnerLesson = new JButton("Beginner");
        beginnerLesson.setFont(new Font("Century Gothic", Font.BOLD, 27));
        beginnerLesson.setForeground(Color.WHITE);
        beginnerLesson.setBackground(buttonColor);
        beginnerLesson.setBounds(250,190,300,70);
        beginnerLesson.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButtonMouseAdapter(beginnerLesson);
        beginnerLesson.addActionListener(
                e -> {
                    frame.dispose();
                    LessonExercise.getLessonExercise(username, language, "beginner");
                }
        );
        panel.add(beginnerLesson);

    }

    public static void addButtonMouseAdapter(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(buttonColor);
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(mouseEnteredColor);
            }
        });

    }

}