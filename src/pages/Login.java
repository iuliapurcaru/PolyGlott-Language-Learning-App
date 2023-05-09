package pages;

import database.LoginCheck;

import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {

    public static void getLogin() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        panel.setLayout(null);

        frame.setTitle("PolyGlott");
        frame.setSize(1600, 1000);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("img/logo.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);

        Color backgroundColor = new Color(242, 175, 136);
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("img/background.png"));
        Dimension size = background.getPreferredSize();
        background.setBounds(size.width - 70, 0, size.width, size.height);
        panel.add(background);
        panel.setBackground(Color.WHITE);

        JLabel title;
        title = new JLabel("SIGN IN");
        title.setBounds(140, 200, 200, 40);
        title.setFont(new Font("Century Gothic", Font.BOLD, 41));
        title.setForeground(Color.BLACK);
        panel.add(title);

        Color gray = new Color(247,247,247);
        JLabel username;
        JTextField usernameField;
        username = new JLabel("Username");
        username.setBounds(140,300,300,30);
        username.setForeground(Color.BLACK);
        username.setFont(new Font("Century Gothic", Font.BOLD, 27));
        panel.add(username);
        usernameField = new JTextField();
        usernameField.setBounds(140,342,400,40);
        usernameField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        usernameField.setBackground(gray);
        panel.add(usernameField);

        JLabel password;
        JPasswordField passwordField;
        password = new JLabel("Password");
        password.setBounds(140,410,300,30);
        password.setForeground(Color.BLACK);
        password.setFont(new Font("Century Gothic", Font.BOLD, 27));
        panel.add(password);
        passwordField = new JPasswordField();
        passwordField.setBounds(140,452,400,40);
        passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        passwordField.setBackground(gray);
        panel.add(passwordField);

        Color buttonColor = new Color(245, 117, 5);
        JButton signInButton = new JButton("SIGN IN");
        signInButton.setFont(new Font("Century Gothic", Font.BOLD, 23));
        signInButton.setBounds(140,520, 130,40);
        signInButton.setForeground(Color.BLACK);
        signInButton.setBackground(buttonColor);
        signInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signInButton.addActionListener(
                e -> LoginCheck.loginCheck(usernameField.getText(), String.valueOf(passwordField.getPassword()), frame)

        );
        panel.add(signInButton);

        JLabel noAccount = new JLabel("Don't have an account?");
        noAccount.setBounds(140, 598, 250,20);
        noAccount.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        noAccount.setForeground(Color.BLACK);
        panel.add(noAccount);

        JButton registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        registerButton.setBounds(140,638, 130,40);
        registerButton.setForeground(Color.BLACK);
        registerButton.setBackground(buttonColor);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(
                e -> {
                    frame.dispose();
                    Register.getRegister();
                }

        );
        panel.add(registerButton);

        frame.setVisible(true);
    }

}