package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Languages extends JFrame {
    public static void getLanguages(String username) {

        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        JLabel chooseLanguage = new JLabel("Choose a Language Course");
        chooseLanguage.setBounds(100,160,800,35);
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
            String languagesQuery = "SELECT languageID, language_name FROM languages ORDER BY language_name DESC";

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

                            Account.checkCourse(username, languageID[iFinal]);
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

        for(int i = 0; i < 2; i++) {
            languageButtons[i].setBounds(100 + i * 190, 220, 120, 80);
            panel.add(languageButtons[i]);
            languages[i].setBounds(100 + i * 190, 300, 130, 40);
            languages[i].setFont(new Font("Century Gothic", Font.BOLD, 22));
            panel.add(languages[i]);
        }

        JButton[] buttons = Buttons.getButtons(frame, username, "");
        buttons[0].setEnabled(false);
        panel.add(buttons[0]);
        panel.add(buttons[6]);
        panel.add(buttons[7]);
        buttons[7].setText("COURSES");

    }

}