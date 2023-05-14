package pages;

import awt.BuildFrame;
import awt.Buttons;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class LessonExercise extends JFrame {

    static int currentQuestion;
    static Color selectedColor = new Color(245, 212, 66);
    static Color buttonColor = new Color(245, 117, 5);
    static int numberOfQuestions = 10;

    public static void getLessonExercise(String username, String language, String lesson) {

        currentQuestion = 0;
        String line;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("content/" + language + "/" + lesson + "/" + lesson + ".csv"));
            String[][] file = new String[numberOfQuestions][7];
            while((line = reader.readLine()) != null) {
                file[currentQuestion] = line.split(",");
                currentQuestion++;
            }
            String[][] exercises = new String[7][numberOfQuestions];
            for(int i = 0; i < 7; i++){
                for(int j = 0; j < numberOfQuestions ; j++){
                    exercises[i][j] = file[j][i];
                }
            }
            currentQuestion = 0;
            showExercises(username, language,exercises);
        }
        catch (IOException err)
        {
            err.printStackTrace();
        }
    }

    public static void showExercises(String username, String language, String[][] exercises) {

        if(Objects.equals(exercises[1][currentQuestion], "multiple")) {
            multipleChoiceExercise(username, language, exercises);
        }
    }

    public static void multipleChoiceExercise(String username, String language, String[][] exercises) {

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

        JLabel questionLabel = new JLabel(exercises[0][currentQuestion]);
        questionLabel.setBounds(280,210,500,35);
        questionLabel.setForeground(Color.BLACK);
        questionLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
        panel.add(questionLabel);

        String correctAnswer = null;
        String[] choiceAnswer = {null};
        JButton[] choices = new JButton[3];
        int choiceNumber = 3;
        final JButton[] selectedButton = {null};
        JLabel[] choicesLabels = new JLabel[3];
        ImageIcon choice;
        for(int i = 0; i < 3; i++) {
            correctAnswer = exercises[6][currentQuestion];

            if(Objects.equals(exercises[2][currentQuestion], "text")) {
                choices[i] = new JButton(exercises[choiceNumber + i][currentQuestion]);
                choices[i].setFont(new Font("Century Gothic", Font.BOLD, 23));
                choices[i].setBounds(280 + i * 250, 400, 200, 100);
                choices[i].setForeground(Color.BLACK);
                choices[i].setBackground(buttonColor);
                choices[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                int iFinal = i;
                choices[i].addActionListener(
                        e -> {
                            if (selectedButton[0] != null) {
                                selectedButton[0].setBackground(buttonColor);
                            }
                            selectedButton[0] = (JButton) e.getSource();
                            selectedButton[0].setBackground(selectedColor);
                            choiceAnswer[0] = Integer.toString(iFinal);
                        }
                );
            }
            else if(Objects.equals(exercises[2][currentQuestion], "img")) {
                choicesLabels[i] = new JLabel(exercises[i + 3][currentQuestion]);
                choicesLabels[i].setBounds(390 + i * 400,670,200,35);
                choicesLabels[i].setForeground(Color.BLACK);
                choicesLabels[i].setFont(new Font("Century Gothic", Font.BOLD, 23));
                panel.add(choicesLabels[i]);

                choice = new ImageIcon("content/ROU/beginner/img/" + exercises[i + 3][currentQuestion] + ".png");
                choices[i] = new JButton(choice);
                choices[i].setBounds(280 + i * 400, 300, 360,360);
                choices[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                int iFinal = i;
                choices[i].addActionListener(
                        e -> {
                            if (selectedButton[0] != null) {
                                selectedButton[0].setEnabled(true);
                            }
                            selectedButton[0] = (JButton) e.getSource();
                            selectedButton[0].setEnabled(false);
                            choiceAnswer[0] = Integer.toString(iFinal);
                        }
                );
            }
            panel.add(choices[i]);
        }

        JButton checkButton = new JButton("CHECK");
        checkButton.setFont(new Font("Century Gothic", Font.BOLD, 27));
        checkButton.setBounds(280,750, 170,70);
        checkButton.setForeground(Color.BLACK);
        checkButton.setBackground(selectedColor);
        checkButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        String finalCorrectAnswer = correctAnswer;
        checkButton.addActionListener(
                e -> {
                    if(Objects.equals(finalCorrectAnswer, choiceAnswer[0])) {
                        optionPaneFont.setText("Correct!");
                        JOptionPane.showMessageDialog(null, optionPaneFont);
                        if(currentQuestion < numberOfQuestions - 1) {
                            frame.dispose();
                            currentQuestion++;
                            showExercises(username, language, exercises);
                        }
                        else {
                            optionPaneFont.setText("Congratulations! You finished the lesson!      +10XP");
                            JOptionPane.showMessageDialog(null, optionPaneFont);
                            frame.dispose();
                            Lessons.getLessons(username, language);
                        }
                    }
                    else {
                        optionPaneFont.setText("Wrong answer!");
                        JOptionPane.showMessageDialog(null, optionPaneFont);
                    }
                }
        );
        panel.add(checkButton);
    }

}
