package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Homepage extends JFrame {
    public static void getHomepage(String username, String language) {

        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
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
                languageLabel= new JLabel(resultSet.getString("language_name") + " Course");
                languageLabel.setBounds(270, 160, 500, 40);
                languageLabel.setFont(new Font("Century Gothic", Font.BOLD, 40));
                languageLabel.setForeground(Color.BLACK);
                panel.add(languageLabel);
            }

            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();

        }

    }

}