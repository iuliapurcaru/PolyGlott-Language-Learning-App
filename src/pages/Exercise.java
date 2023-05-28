package pages;

import awt.Audio;
import awt.BuildFrame;
import awt.Buttons;
import database.Level;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Objects;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class Exercise extends JFrame {

    static int currentQuestion;
    static JLabel optionPaneFont = new JLabel();
    static int numberOfQuestions = 10;

    public static void getExercise(String username, String language, String title) {


        currentQuestion = 0;
        String line;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("content/" + language + "/" + title + "/" + title + ".csv"));
            String[][] file = new String[numberOfQuestions][9];
            reader.readLine();
            while((line = reader.readLine()) != null) {
                file[currentQuestion] = line.split(",");
                currentQuestion++;
            }
            String[][] exercises = new String[9][numberOfQuestions];
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < numberOfQuestions ; j++){
                    exercises[i][j] = file[j][i];
                }
            }
            currentQuestion = 0;
            showExercises(username, language,exercises, title);
        }
        catch (IOException err)
        {
            err.printStackTrace();
        }
    }

    public static void showExercises(String username, String language, String[][] exercises, String lesson) {

        if(Objects.equals(exercises[2][currentQuestion], "multiple")) {
            multipleChoiceExercise(username, language, exercises, lesson);
        }
        else if(Objects.equals(exercises[2][currentQuestion], "input")) {
            inputTextExercise(username, language, exercises, lesson);
        }
    }

    public static void multipleChoiceExercise(String username, String language, String[][] exercises, String lesson) {

        optionPaneFont.setFont(new Font("Century Gothic", Font.BOLD, 23));

        JPanel panel = BuildFrame.getPanel();
        JFrame frame = BuildFrame.getFrame();
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username, language);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText(toUpperCase(lesson));

        JLabel questionLabel = new JLabel(exercises[0][currentQuestion]);
        questionLabel.setBounds(300,210,500,35);
        questionLabel.setForeground(Color.BLACK);
        questionLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
        panel.add(questionLabel);

        JLabel questionNumber = getCurrentQuestion();
        panel.add(questionNumber);

        String correctAnswer;
        String[] choiceAnswer = {null};
        JButton[] choices = new JButton[3];
        int choiceNumber = 3;
        final JButton[] selectedButton = {null};
        JLabel[] choicesLabels = new JLabel[3];
        ImageIcon choice;
        correctAnswer = exercises[7][currentQuestion];

        for(int i = 0; i < 3; i++) {

            if(Objects.equals(exercises[3][currentQuestion], "text")) {
                choices[i] = new JButton(exercises[choiceNumber + i + 1][currentQuestion]);
                choices[i].setFont(new Font("Century Gothic", Font.BOLD, 25));
                choices[i].setBounds(300 + i * 250, 430, 200, 100);
                choices[i].setForeground(Color.BLACK);
                choices[i].setBackground(Buttons.orangeButtonColor);
                choices[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                int iFinal = i;
                choices[i].addActionListener(
                        e -> {
                            if (selectedButton[0] != null) {
                                selectedButton[0].setBackground(Buttons.orangeButtonColor);
                            }
                            try {
                                Audio.playAudio(exercises[choiceNumber + iFinal + 1][currentQuestion] + ".wav", language);
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                                throw new RuntimeException(ex);
                            }
                            selectedButton[0] = (JButton) e.getSource();
                            selectedButton[0].setBackground(Buttons.yellowButtonColor);
                            choiceAnswer[0] = Integer.toString(iFinal);
                        }
                );
            }
            else if(Objects.equals(exercises[3][currentQuestion], "img")) {
                choicesLabels[i] = new JLabel(exercises[i + 4][currentQuestion]);
                choicesLabels[i].setBounds(390 + i * 400,670,200,35);
                choicesLabels[i].setForeground(Color.BLACK);
                choicesLabels[i].setFont(new Font("Century Gothic", Font.BOLD, 25));
                panel.add(choicesLabels[i]);

                choice = new ImageIcon("content/" + language + "/" + lesson + "/img/" + exercises[i + 4][currentQuestion] + ".png");
                choices[i] = new JButton(choice);
                choices[i].setBounds(300 + i * 400, 300, 360,360);
                choices[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                int iFinal = i;
                choices[i].addActionListener(
                        e -> {
                            if (selectedButton[0] != null) {
                                selectedButton[0].setEnabled(true);
                            }
                            try {
                                Audio.playAudio(exercises[choiceNumber + iFinal + 1][currentQuestion] + ".wav", language);
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                                throw new RuntimeException(ex);
                            }
                            selectedButton[0] = (JButton) e.getSource();
                            selectedButton[0].setEnabled(false);
                            choiceAnswer[0] = Integer.toString(iFinal);
                        }
                );
            }
            panel.add(choices[i]);
        }

        JButton checkButton = getCheckButton(750);
        String finalCorrectAnswer = correctAnswer;

        checkButton.addActionListener(
                e -> {
                    if(Objects.equals(finalCorrectAnswer, choiceAnswer[0])) {
                        optionPaneFont.setText("Correct!");
                        JOptionPane.showMessageDialog(null, optionPaneFont);
                        if(currentQuestion < numberOfQuestions - 1) {
                            frame.dispose();
                            currentQuestion++;
                            showExercises(username, language, exercises, lesson);
                        }
                        else {
                            optionPaneFont.setText("Congratulations! You finished the lesson!      +10XP");
                            Level.calculateLevel(username, language);
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

    public static void inputTextExercise(String username, String language, String[][] exercises, String lesson) {

        optionPaneFont.setFont(new Font("Century Gothic", Font.BOLD, 23));

        JPanel panel = BuildFrame.getPanel();
        JFrame frame = BuildFrame.getFrame();
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username, language);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("LESSONS");

        JLabel questionLabel = new JLabel(exercises[0][currentQuestion]);
        questionLabel.setBounds(300,290,500,35);
        questionLabel.setForeground(Color.BLACK);
        questionLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
        panel.add(questionLabel);

        JLabel questionLabelPart2 = new JLabel(exercises[1][currentQuestion]);
        questionLabelPart2.setBounds(300,340,500,30);
        questionLabelPart2.setForeground(Color.BLACK);
        questionLabelPart2.setFont(new Font("Century Gothic", Font.BOLD, 28));
        panel.add(questionLabelPart2);

        JLabel questionNumber = getCurrentQuestion();
        panel.add(questionNumber);

        String correctAnswer;
        String[] inputAnswer = {null};
        correctAnswer = exercises[7][currentQuestion];
        JTextField inputAnswerField = BuildFrame.getTextField(300, 480);
        panel.add(inputAnswerField);

        JButton checkButton = getCheckButton(700);
        String finalCorrectAnswer = correctAnswer;

        if(Objects.equals(exercises[3][currentQuestion], "translate")) {
            ImageIcon icon = new ImageIcon("img/buttons/audio2.png");
            JButton playAudioButton = new JButton("PLAY AUDIO", icon);
            playAudioButton.setFont(new Font("Century Gothic", Font.BOLD, 23));
            playAudioButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            playAudioButton.setHorizontalTextPosition(SwingConstants.CENTER);
            playAudioButton.setBounds(750, 280, 170,110);
            playAudioButton.setForeground(Color.BLACK);
            playAudioButton.setBackground(Buttons.yellowButtonColor);
            playAudioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            playAudioButton.addActionListener(
                    e -> {
                        try {
                            Audio.playAudio("audio_" + lesson + "_" + currentQuestion + ".wav", language);
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
            );
            panel.add(playAudioButton);
        }

        checkButton.addActionListener(
                e -> {
                    inputAnswer[0] = inputAnswerField.getText();

                    if(Objects.equals(finalCorrectAnswer, inputAnswer[0])) {
                        optionPaneFont.setText("Correct!");
                        JOptionPane.showMessageDialog(null, optionPaneFont);
                        if(!Objects.equals(exercises[8][currentQuestion], " ")) {
                            Dictionary.addToDictionary(username, language, exercises[4 + Integer.parseInt(finalCorrectAnswer)][currentQuestion], exercises[8][currentQuestion]);
                        }
                        if(currentQuestion < numberOfQuestions - 1) {
                            frame.dispose();
                            currentQuestion++;
                            showExercises(username, language, exercises, lesson);
                        }
                        else {
                            Level.calculateLevel(username, language);
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

    public static JButton getCheckButton(int y) {
        JButton checkButton = new JButton("CHECK");
        checkButton.setFont(new Font("Century Gothic", Font.BOLD, 27));
        checkButton.setBounds(300, y, 170,70);
        checkButton.setForeground(Color.BLACK);
        checkButton.setBackground(Buttons.yellowButtonColor);
        checkButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return checkButton;
    }

    public static JLabel getCurrentQuestion() {
        JLabel questionNumber = new JLabel("Question " + (currentQuestion + 1) + "/" + numberOfQuestions);
        questionNumber.setBounds(1150,210,500,35);
        questionNumber.setForeground(Color.GRAY);
        questionNumber.setFont(new Font("Century Gothic", Font.BOLD, 30));

        return questionNumber;
    }

}
