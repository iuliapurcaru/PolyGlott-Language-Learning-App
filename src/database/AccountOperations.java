package database;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountOperations {

    public static void updatePassword(String username, String password) {

        JLabel optionPaneFont = new JLabel();
        optionPaneFont.setFont(new Font("Century Gothic", Font.BOLD, 23));

        Connection connection;
        PreparedStatement preparedStatement;
        String updateUser = "UPDATE users SET password = ? WHERE username = ?";

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(updateUser);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (Exception err) {
            err.printStackTrace();
        }

        optionPaneFont.setText("Password successfully changed!");
        JOptionPane.showMessageDialog(null, optionPaneFont);
    }

    public static void deleteAccount(String username) {

            JLabel optionPaneFont = new JLabel();
            optionPaneFont.setFont(new Font("Century Gothic", Font.BOLD, 23));
            UIManager.put("OptionPane.minimumSize", new Dimension(100, 50));

            Connection connection;
            PreparedStatement preparedStatement;
            String deleteUser = "DELETE FROM users WHERE username = ?";

            try {
                connection = DatabaseConnection.getConnection();
                preparedStatement = connection.prepareStatement(deleteUser);
                preparedStatement.setString(1, username);
                preparedStatement.executeUpdate();

                connection.close();
            } catch (Exception err) {
                err.printStackTrace();
            }

            optionPaneFont.setText("Account successfully deleted!");
            JOptionPane.showMessageDialog(null, optionPaneFont);

    }

    public static void checkCourse(String username, String languageID) {

        Connection connection;
        ResultSet resultSetUser;
        PreparedStatement preparedStatementUser;
        String checkUser = "SELECT username, languageID FROM user_progress WHERE username = ? and languageID = ?";

        try {
            connection = DatabaseConnection.getConnection();

            preparedStatementUser = connection.prepareStatement(checkUser);
            preparedStatementUser.setString(1, username);
            preparedStatementUser.setString(2, languageID);
            resultSetUser = preparedStatementUser.executeQuery();

            if (!resultSetUser.next()) {
                String insertUser = "INSERT INTO user_progress (username, languageID) VALUES (?, ?)";
                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement(insertUser);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, languageID);
                preparedStatement.executeUpdate();
            }
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
