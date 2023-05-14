package pages;

import awt.BuildFrame;
import awt.Buttons;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class LessonExercise extends JFrame {
    public static void getLessonExercise(String username, String language, String lesson) {

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

        String line = "";
        JLabel question;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("content/beginner.csv"));
            while ((line = br.readLine()) != null)
            {
                String[] exercise = line.split(",");
                question = new JLabel(exercise[0]);
                question.setBounds(300,410,300,30);
                question.setForeground(Color.BLACK);
                question.setFont(new Font("Century Gothic", Font.BOLD, 23));
                panel.add(question);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
