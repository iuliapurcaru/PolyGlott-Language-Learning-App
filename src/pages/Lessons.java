package pages;

import awt.BuildFrame;
import awt.Buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class Lessons extends JFrame {

    static Color buttonColor = new Color(245, 212, 66);
    static Color mouseEnteredColor = new Color(255, 143, 23);
    public static void getLessons(String username, String language) {

        JPanel panel = BuildFrame.getPanel();
        JFrame frame = BuildFrame.getFrame();
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username, language);
        for (int i = 0; i < 7; i++) {
            panel.add(buttons[i]);
        }
        buttons[6].setText("LESSONS");

        JLabel chooseLesson = new JLabel("Choose a Lesson");
        chooseLesson.setBounds(250,160,800,35);
        chooseLesson.setForeground(Color.BLACK);
        chooseLesson.setFont(new Font("Century Gothic", Font.BOLD, 35));
        panel.add(chooseLesson);

        String[] lessons = {"beginner", "animals"};
        JButton[] lessonButtons = new JButton[2];
        for(int i = 0; i < 2; i++) {
            lessonButtons[i] = new JButton(i + 1 + ". " + toUpperCase(lessons[i]));
            lessonButtons[i].setFont(new Font("Century Gothic", Font.BOLD, 27));
            lessonButtons[i].setForeground(Color.BLACK);
            lessonButtons[i].setBackground(buttonColor);
            lessonButtons[i].setBounds(250 + i * 550,240,450,100);
            lessonButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addButtonMouseAdapter(lessonButtons[i]);
            int iFinal = i;
            lessonButtons[i].addActionListener(
                    e -> {
                        frame.dispose();
                        Exercise.getExercise(username, language, lessons[iFinal]);
                    }
            );
            panel.add(lessonButtons[i]);
        }
    }

    public static void addButtonMouseAdapter(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(mouseEnteredColor);
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(buttonColor);
            }
        });

    }

}