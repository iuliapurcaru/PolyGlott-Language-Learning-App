package pages;


import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;
import database.AccountOperations;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Languages {

    static int numberOfLanguages = 5;
    public static void getLanguages(String username) {

        JPanel panel = BuildFrame.getPanel();
        JFrame frame = BuildFrame.getFrame();
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username, "");
        buttons[0].setEnabled(false);
        panel.add(buttons[0]);
        panel.add(buttons[6]);
        panel.add(buttons[7]);
        buttons[7].setText("COURSES");

        JLabel chooseLanguage = new JLabel("Choose a Language Course");
        chooseLanguage.setBounds(130,160,800,55);
        chooseLanguage.setForeground(Color.BLACK);
        chooseLanguage.setFont(new Font("Century Gothic", Font.BOLD, 35));
        panel.add(chooseLanguage);

        int size = 0;
        String[] languageID = new String[10];
        JLabel[] languages = new JLabel[10];
        JButton[] languageButtons = new JButton[10];
        ImageIcon flag;

        try {
            Connection connection;
            ResultSet resultSet;
            Statement statement;
            String languagesQuery = "SELECT languageID, language_name FROM languages ORDER BY rank";

            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(languagesQuery);

            while(resultSet.next()) {
                languageID[size] = resultSet.getString("languageID");
                languages[size] = new JLabel(resultSet.getString("language_name"));
                flag = new ImageIcon("img/flags/" + languageID[size] + ".png");
                languageButtons[size] = new JButton(flag);
                languageButtons[size].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                size++;
            }

            for(int i = 0; i < size; i++) {
                int iFinal = i;
                languageButtons[i].addActionListener(
                        e -> {
                            AccountOperations.checkCourse(username, languageID[iFinal]);
                            frame.dispose();
                            Homepage.getHomepage(username, languageID[iFinal]);
                        }
                );
            }

            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < numberOfLanguages; i++) {
            languageButtons[i].setBounds(130 + i * 280, 220, 180, 120);
            panel.add(languageButtons[i]);
            languages[i].setBounds(130 + i * 280, 340, 130, 40);
            languages[i].setFont(new Font("Century Gothic", Font.BOLD, 25));
            panel.add(languages[i]);
        }
    }

}