package pages;

import awt.BuildFrame;
import awt.Buttons;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class LessonExercise extends JFrame {

    static int currentQuestion;
    static Color buttonColor = new Color(245, 212, 66);
    static int numberOfQuestions = 6;

    public static void getLessonExercise(String username, String language, String lesson) {

        currentQuestion = 0;
        String line = "";
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("content/" + language + "/" + lesson + "/" + lesson + ".csv"));
            String[][] file = new String[numberOfQuestions][3];
            while((line = reader.readLine()) != null) {
                file[currentQuestion] = line.split(",");
                currentQuestion++;
            }
            String[][] exercises = new String[3][numberOfQuestions];
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < numberOfQuestions ; j++){
                    exercises[i][j] = file[j][i];
                }
            }
            currentQuestion = 0;
            showExercise(username, language, exercises, "CHECK");
        }
        catch (IOException err)
        {
            err.printStackTrace();
        }
    }

    public static void showExercise(String username, String language, String exercises[][], String buttonText) {

        JLabel optionPaneFont = new JLabel();
        optionPaneFont.setFont(new Font("Century Gothic", Font.BOLD, 23));

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
        
        JLabel questionLabel = new JLabel(exercises[currentQuestion][0]);
        questionLabel.setBounds(500,310,500,35);
        questionLabel.setForeground(Color.BLACK);
        questionLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
        panel.add(questionLabel);
        
        JButton nextButton = new JButton(buttonText);
        nextButton.setFont(new Font("Century Gothic", Font.BOLD, 27));
        nextButton.setBounds(500,620, 170,70);
        nextButton.setForeground(Color.BLACK);
        nextButton.setBackground(buttonColor);
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextButton.addActionListener(
                e -> {
                    if(currentQuestion < numberOfQuestions - 1) {
                        frame.dispose();
                        currentQuestion++;
                        showExercise(username, language, exercises, "CHECK");
                    }
                    else {
                        optionPaneFont.setText("<html>Congratulations! You finished the lesson!<br>+10XP</html>");
                        JOptionPane.showMessageDialog(null, "<html><font face=\"Century Gothic\" size=\"18\"><b>Congratulations! You finished the lesson!</b><br>+10XP</font></html>");
                        frame.dispose();
                        Lessons.getLessons(username, language);
                    }
                }

        );
        panel.add(nextButton);
    }
}
