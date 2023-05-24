package pages;

import awt.BuildFrame;
import awt.Buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class Lessons extends JFrame {

    public static void getLessons(String username, String language) {

        JLabel optionPaneFont = new JLabel();
        optionPaneFont.setFont(new Font("Century Gothic", Font.BOLD, 23));

        JPanel panel = BuildFrame.getPanel();
        JFrame frame = BuildFrame.getFrame();
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username, language);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("LESSONS");

        JLabel chooseLesson = new JLabel("Choose a Lesson");
        chooseLesson.setBounds(250,160,800,35);
        chooseLesson.setForeground(Color.BLACK);
        chooseLesson.setFont(new Font("Century Gothic", Font.BOLD, 35));
        panel.add(chooseLesson);

        String[] lessons = {"first words", "introductions", "common nouns",
                            "verbs and greetings", "pronouns and family", "articles and animals",
                            "adverbs and places", "adjectives and food", "test",
                            "test", "test", "test"};
        JButton[] lessonButtons = new JButton[12];
        int size = 0;
        for(int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {
                lessonButtons[size] = new JButton(size + 1 + ". " + toUpperCase(lessons[size]));
                lessonButtons[size].setFont(new Font("Century Gothic", Font.BOLD, 25));
                lessonButtons[size].setForeground(Color.BLACK);
                lessonButtons[size].setBackground(Buttons.yellowButtonColor);
                lessonButtons[size].setBounds(250 + i * 435, 240 + j * 150, 370, 90);
                lessonButtons[size].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                addButtonMouseAdapter(lessonButtons[size]);
                int finalSize = size;
                lessonButtons[size].addActionListener(
                        e -> {
                            File tmpDir = new File("content/" + language + "/" + lessons[finalSize] + "/" + lessons[finalSize] + ".csv");
                            if(!tmpDir.exists()) {
                                optionPaneFont.setText("Coming soon");
                                JOptionPane.showMessageDialog(null, optionPaneFont);
                                return;
                            }

                            frame.dispose();
                            Exercise.getExercise(username, language, lessons[finalSize]);
                        }
                );
                panel.add(lessonButtons[size]);
                size++;
            }
        }
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