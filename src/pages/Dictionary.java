package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dictionary extends JFrame {
    public static void getDictionary(String username, String language) {

        JPanel panel = BuildFrame.getPanel();
        JFrame frame = BuildFrame.getFrame();
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username, language);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("DICTIONARY");

        JLabel dictionaryLabel = new JLabel("Here you can see every word you have learned in lessons");
        dictionaryLabel.setFont(new Font("Century Gothic", Font.BOLD, 28));
        dictionaryLabel.setBounds(250, 140, 1100, 35);
        panel.add(dictionaryLabel);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Century Gothic", Font.BOLD, 25));
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(250, 200, 1250, 700);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        Connection connection;
        String searchWord = "SELECT * FROM user_dictionary WHERE username = ? AND languageID = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection = DatabaseConnection.getConnection();

            preparedStatement = connection.prepareStatement(searchWord);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, language);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                textArea.append(" " + resultSet.getString("word") + " = " + resultSet.getString("translation") + "\n");
            }

            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static boolean wordExists(String username, String language, String word) {
        Connection connection;
        String searchWord = "SELECT * FROM user_dictionary WHERE username = ? AND languageID = ? AND word = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection = DatabaseConnection.getConnection();

            preparedStatement = connection.prepareStatement(searchWord);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, language);
            preparedStatement.setString(3, word);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return true;
            }

            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static void addToDictionary(String username, String language, String word, String translation) {

        if (!wordExists(username, language, word)) {
            Connection connection;
            String insertWord = "INSERT INTO user_dictionary (`username`, `languageID`, `word`, `translation`) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement;

            try {
                connection = DatabaseConnection.getConnection();

                preparedStatement = connection.prepareStatement(insertWord);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, language);
                preparedStatement.setString(3, word);
                preparedStatement.setString(4, translation);
                preparedStatement.executeUpdate();

                connection.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static int getCountWords(String language) {
        int words = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("content/" + language + "/dictionary.csv"))) {
            while ((reader.readLine()) != null) {
                words++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

}