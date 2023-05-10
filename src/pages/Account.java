package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Objects;

public class Account {

    public static void getAccount(String username) {

        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        JLabel optionPaneFont = new JLabel();
        optionPaneFont.setFont(new Font("Century Gothic", Font.BOLD, 27));

        JButton[] buttons = Buttons.getButtons(frame, username);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("MY ACCOUNT");

        JLabel user = new JLabel("HELLO, " + username);
        user.setBounds(300, 160, 500, 40);
        user.setFont(new Font("Century Gothic", Font.BOLD, 40));
        user.setForeground(Color.BLACK);
        panel.add(user);

        Font font = new Font("Century Gothic", Font.BOLD, 27);
        Color buttonColor = new Color(245, 117, 5);

        JLabel changeLabel = new JLabel("Change password:");
        changeLabel.setBounds(300,238,800,33);
        changeLabel.setForeground(Color.BLACK);
        changeLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
        panel.add(changeLabel);

        JLabel newPassword = new JLabel("New password");
        newPassword.setBounds(300,278,800,30);
        newPassword.setForeground(Color.BLACK);
        newPassword.setFont(font);
        panel.add(newPassword);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(300,320,300,40);
        passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(passwordField);

        JLabel confirmNewPassword = new JLabel("Confirm new password");
        confirmNewPassword.setBounds(300,390,800,30);
        confirmNewPassword.setForeground(Color.BLACK);
        confirmNewPassword.setFont(font);
        panel.add(confirmNewPassword);
        JPasswordField confirmPassword;
        confirmPassword = new JPasswordField();
        confirmPassword.setBounds(300,432,300,40);
        confirmPassword.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(confirmPassword);

        JButton changePassword = new JButton("CHANGE PASSWORD");
        changePassword.setFont(new Font("Century Gothic", Font.BOLD, 23));
        changePassword.setBounds(300,520, 300,50);
        changePassword.setForeground(Color.WHITE);
        changePassword.setBackground(buttonColor);
        changePassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changePassword.addActionListener(
                e -> {
                    if (!Objects.equals(String.valueOf(passwordField.getPassword()), String.valueOf(confirmPassword.getPassword()))) {
                        optionPaneFont.setText("Passwords do not match!");
                        JOptionPane.showMessageDialog(null, optionPaneFont);
                    }
                    else if (Objects.equals(String.valueOf(passwordField.getPassword()), "")) {
                        optionPaneFont.setText("Password cannot be empty!");
                        JOptionPane.showMessageDialog(null, optionPaneFont);
                    }
                    else {

                        Connection connection;
                        PreparedStatement preparedStatement;
                        String updateUser = "UPDATE users SET password = ? WHERE username = ?";
                        UIManager.put("OptionPane.minimumSize", new Dimension(100, 50));

                        try {
                            connection = DatabaseConnection.getConnection();
                            preparedStatement = connection.prepareStatement(updateUser);
                            preparedStatement.setString(1, String.valueOf(passwordField.getPassword()));
                            preparedStatement.setString(2, username);
                            preparedStatement.executeUpdate();

                            connection.close();
                        } catch (Exception err) {
                            err.printStackTrace();
                        }

                        optionPaneFont.setText("Password successfully changed!");
                        JOptionPane.showMessageDialog(null, optionPaneFont);

                    }

                }
        );
        panel.add(changePassword);

        JButton deleteAccount = new JButton("DELETE ACCOUNT");
        deleteAccount.setFont(font);
        deleteAccount.setBounds(300,700, 300,50);
        deleteAccount.setForeground(Color.WHITE);
        deleteAccount.setBackground(Color.RED);
        deleteAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteAccount.addActionListener(
                e -> {
                    int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?");
                    if (input == 0) {
                        Connection connection;
                        PreparedStatement preparedStatement;
                        String deleteUser = "DELETE FROM users WHERE username = ?";
                        UIManager.put("OptionPane.minimumSize", new Dimension(100, 50));

                        try {
                            connection = DatabaseConnection.getConnection();
                            preparedStatement = connection.prepareStatement(deleteUser);
                            preparedStatement.setString(1, username);
                            preparedStatement.executeUpdate();

                            connection.close();
                        } catch (Exception err) {
                            err.printStackTrace();
                        }

                        frame.dispose();
                        Login.getLogin();
                    }
                }
        );

        panel.add(deleteAccount);

        JLabel progressLabel = new JLabel("Your progress:");
        progressLabel.setBounds(1000,238,800,33);
        progressLabel.setForeground(Color.BLACK);
        progressLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
        panel.add(progressLabel);

    }
}