package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;
import database.Level;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.*;
import java.util.Objects;

public class Account {

    public static void getAccount(String username, String language) {

        JPanel panel = BuildFrame.getPanel();
        JFrame frame = BuildFrame.getFrame();
        frame.add(panel);

        JLabel optionPaneFont = new JLabel();
        optionPaneFont.setFont(new Font("Century Gothic", Font.BOLD, 23));

        JButton[] buttons = Buttons.getButtons(frame, username, language);
        for (int i = 0; i < 7; i++) {
            panel.add(buttons[i]);
        }
        buttons[6].setText("MY ACCOUNT");

        JLabel user = new JLabel("Manage your account");
        user.setBounds(1060, 160, 500, 40);
        user.setFont(new Font("Century Gothic", Font.BOLD, 38));
        user.setForeground(Color.BLACK);
        panel.add(user);

        Font font = new Font("Century Gothic", Font.BOLD, 27);
        Color buttonColor = new Color(245, 117, 5);

        JLabel changeLabel = new JLabel("Change password");
        changeLabel.setBounds(1100,238,800,33);
        changeLabel.setForeground(Color.BLACK);
        changeLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
        panel.add(changeLabel);

        JLabel newPassword = new JLabel("New password");
        newPassword.setBounds(1100,278,800,30);
        newPassword.setForeground(Color.BLACK);
        newPassword.setFont(font);
        panel.add(newPassword);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(1100,320,300,40);
        passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(passwordField);

        JLabel confirmNewPassword = new JLabel("Confirm new password");
        confirmNewPassword.setBounds(1100,390,800,30);
        confirmNewPassword.setForeground(Color.BLACK);
        confirmNewPassword.setFont(font);
        panel.add(confirmNewPassword);
        JPasswordField confirmPassword;
        confirmPassword = new JPasswordField();
        confirmPassword.setBounds(1100,432,300,40);
        confirmPassword.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(confirmPassword);

        JButton changePassword = new JButton("CHANGE PASSWORD");
        changePassword.setFont(new Font("Century Gothic", Font.BOLD, 23));
        changePassword.setBounds(1100,520, 300,50);
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
                        //UIManager.put("OptionPane.minimumSize", new Dimension(100, 50));

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
        deleteAccount.setBounds(1100,750, 300,50);
        deleteAccount.setForeground(Color.WHITE);
        deleteAccount.setBackground(Color.RED);
        deleteAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteAccount.addActionListener(
                e -> {
                    optionPaneFont.setText("Are you sure you want to delete your account?");
                    int input = JOptionPane.showConfirmDialog(null, optionPaneFont);
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

        JLabel usernameLabel = new JLabel("Hello, " + username + "!");
        usernameLabel.setBounds(300,140,800,40);
        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setFont(new Font("Century Gothic", Font.BOLD, 40));
        panel.add(usernameLabel);

        JLabel progressLabel = new JLabel("Your courses");
        progressLabel.setBounds(300,220,800,40);
        progressLabel.setForeground(Color.BLACK);
        progressLabel.setFont(new Font("Century Gothic", Font.BOLD, 38));
        panel.add(progressLabel);

        BufferedImage img = null;

        try {
            Connection connection;
            ResultSet resultSet;
            PreparedStatement preparedStatement;
            String getProgress = "SELECT l.language_name, up.languageID FROM languages l, user_progress up WHERE up.username = ? AND up.languageID = l.languageID ORDER BY up.language_level DESC";

            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(getProgress);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            int align = 0;
            while(resultSet.next()) {
                String languageID = resultSet.getString("languageID");
                int level = Level.getLevel(username, languageID);
                int exp = Level.getExp(username, languageID);

                try {
                    img = ImageIO.read(new File("img/flags/" + languageID + ".png"));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                assert img != null;
                Image resizeImg = img.getScaledInstance(120, 80, Image.SCALE_SMOOTH);
                ImageIcon flag = new ImageIcon(resizeImg);
                JButton languageFlag = new JButton(flag);
                languageFlag.setBounds(300, 280 + align * 100, 120, 80);
                languageFlag.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                languageFlag.addActionListener(
                        e -> {
                            frame.dispose();
                            Homepage.getHomepage(username, languageID);
                        }
                );
                panel.add(languageFlag);

                JLabel languageLabel = new JLabel(resultSet.getString("language_name"));
                languageLabel.setBounds(450, 280 + align * 100, 300, 30);
                languageLabel.setFont(new Font("Century Gothic", Font.BOLD, 28));
                languageLabel.setForeground(Color.BLACK);
                panel.add(languageLabel);

                JLabel languageLevel = new JLabel("Level " + level);
                languageLevel.setBounds(450, 320 + align * 100, 200, 30);
                languageLevel.setFont(new Font("Century Gothic", Font.BOLD, 28));
                languageLevel.setForeground(Color.GRAY);
                panel.add(languageLevel);

                JLabel languageExp = new JLabel(exp + "/" + ((level + 1) * 100) + " XP");
                languageExp.setBounds(650, 320 + align * 100, 200, 30);
                languageExp.setFont(new Font("Century Gothic", Font.BOLD, 28));
                languageExp.setForeground(Color.GRAY);
                panel.add(languageExp);
                align++;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
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