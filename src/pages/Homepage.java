package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;
import database.Level;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Homepage extends JFrame {
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

        JLabel languageLevel = new JLabel();
        languageLevel.setBounds(270, 190, 500, 120);
        languageLevel.setFont(infoFont);
        languageLevel.setForeground(Color.BLACK);
        languageLevel.setText("Level " + level);
        panel.add(languageLevel);

        JLabel languageXP = new JLabel();
        languageXP.setBounds(270, 250, 500, 120);
        languageXP.setFont(infoFont);
        languageXP.setForeground(Color.BLACK);
        languageXP.setText("XP until next level: " + ((level + 1) * 100 - exp));
        panel.add(languageXP);

        JLabel lastLesson = new JLabel();
        lastLesson.setBounds(270, 310, 500, 120);
        lastLesson.setFont(infoFont);
        lastLesson.setForeground(Color.BLACK);
        lastLesson.setText("Last lesson: ");
        panel.add(lastLesson);
    }

}