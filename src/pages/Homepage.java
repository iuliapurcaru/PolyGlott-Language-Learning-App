package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;
import database.Level;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Homepage {
    public static void getHomepage(String username, String language) {

        JPanel panel = BuildFrame.getPanel();
        JFrame frame = BuildFrame.getFrame();
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username, language);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("HOME");

        try {
            Connection connection;
            ResultSet resultSet;
            PreparedStatement preparedStatement;
            String query = "SELECT languageID, language_name FROM languages WHERE languageID = ?";

            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, language);
            resultSet = preparedStatement.executeQuery();

            JLabel languageLabel;

            while(resultSet.next()) {
                languageLabel= new JLabel(resultSet.getString("language_name") + " Course Overview");
                languageLabel.setBounds(270, 160, 700, 40);
                languageLabel.setFont(new Font("Century Gothic", Font.BOLD, 40));
                languageLabel.setForeground(Color.BLACK);
                panel.add(languageLabel);
            }

            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();

        }

        Font infoFont = new Font("Century Gothic", Font.PLAIN, 35);

        int level = Level.getLevel(username, language);
        int exp = Level.getExp(username, language);

        JLabel languageLevel = new JLabel("Level " + level);
        languageLevel.setBounds(270, 190, 500, 120);
        languageLevel.setFont(infoFont);
        languageLevel.setForeground(Color.BLACK);
        panel.add(languageLevel);

        JLabel languageXP = new JLabel("XP until next level: " + ((level + 1) * 100 - exp));
        languageXP.setBounds(270, 250, 500, 120);
        languageXP.setFont(infoFont);
        languageXP.setForeground(Color.BLACK);
        panel.add(languageXP);

        JLabel lastLesson = new JLabel("Last lesson: FIRST WORDS");
        lastLesson.setBounds(270, 310, 500, 120);
        lastLesson.setFont(infoFont);
        lastLesson.setForeground(Color.BLACK);
        panel.add(lastLesson);

        JLabel wordsLearned = new JLabel("Words in dictionary: " + Dictionary.countWords(username, language));
        wordsLearned.setBounds(270, 370, 500, 120);
        wordsLearned.setFont(infoFont);
        wordsLearned.setForeground(Color.BLACK);
        panel.add(wordsLearned);


    }

}