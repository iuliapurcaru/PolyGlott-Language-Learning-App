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

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("img/background.png"));
        Dimension sizeBkg = background.getPreferredSize();
        background.setBounds(sizeBkg.width - 70, 228, sizeBkg.width, sizeBkg.height);
        panel.add(background);
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon("img/logo-title.png"));
        Dimension sizeLogo = logo.getPreferredSize();
        logo.setBounds(sizeLogo.width - 70, 0, sizeLogo.width, sizeLogo.height);
        panel.add(logo);
        panel.setBackground(Color.WHITE);

        JLabel title;
        title = new JLabel("SIGN IN");
        title.setBounds(140, 200, 200, 40);
        title.setFont(new Font("Century Gothic", Font.BOLD, 41));
        title.setForeground(Color.BLACK);
        panel.add(title);

        Color gray = new Color(247,247,247);
        Font textFont = new Font("Century Gothic", Font.BOLD, 27);
        Font textFieldFont = new Font("Century Gothic", Font.PLAIN, 20);

        JLabel username;
        JTextField usernameField;
        username = new JLabel("Username");
        username.setBounds(140,300,300,30);
        username.setForeground(Color.BLACK);
        username.setFont(textFont);
        panel.add(username);
        usernameField = new JTextField();
        usernameField.setBounds(140,342,400,40);
        usernameField.setFont(textFieldFont);
        usernameField.setBackground(gray);
        panel.add(usernameField);

        JLabel password;
        JPasswordField passwordField;
        password = new JLabel("Password");
        password.setBounds(140,410,300,30);
        password.setForeground(Color.BLACK);
        password.setFont(textFont);
        panel.add(password);
        passwordField = new JPasswordField();
        passwordField.setBounds(140,452,400,40);
        passwordField.setFont(textFieldFont);
        passwordField.setBackground(gray);
        panel.add(passwordField);

        Color buttonColor = new Color(245, 117, 5);
        JButton signInButton = new JButton("SIGN IN");
        signInButton.setFont(textFont);
        signInButton.setBounds(140,520, 150,50);
        signInButton.setForeground(Color.WHITE);
        signInButton.setBackground(buttonColor);
        signInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signInButton.addActionListener(
                e -> LoginCheck.loginCheck(usernameField.getText(), String.valueOf(passwordField.getPassword()), frame)

        );
        panel.add(signInButton);

        JLabel noAccount = new JLabel("Don't have an account?");
        noAccount.setBounds(140, 640, 400,30);
        noAccount.setFont(new Font("Century Gothic", Font.PLAIN, 27));
        noAccount.setForeground(Color.BLACK);
        panel.add(noAccount);

        JButton registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Century Gothic", Font.PLAIN, 27));
        registerButton.setBounds(140,690, 150,50);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(buttonColor);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(
                e -> {
                    frame.dispose();
                    Register.getRegister();
                }

        );
        panel.add(registerButton);

        JButton guestButton;
        guestButton = new JButton();
        guestButton.setBounds(0,0,50,50);
        guestButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        guestButton.addActionListener(
                e -> {
                    frame.dispose();
                    Homepage.getHomepage("a");
                }

        );
        panel.add(guestButton);

        frame.setVisible(true);
    }

}