package awt;

import pages.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Buttons {
    static Color buttonColor = new Color(245, 117, 5);
    static Color mouseEnteredColor = new Color(255, 143, 23);

    public static JButton[] getButtons(JFrame frame, String username, String language) {
        JButton[] buttons = new JButton[7];
        ImageIcon icon;
        Font font = new Font("Century Gothic", Font.BOLD, 23);

        icon = new ImageIcon("img/buttons/account.png");
        buttons[0] = new JButton("MY ACCOUNT", icon);
        buttons[0].setBounds(0, 0, 236, 110);
        buttons[0].addActionListener(
                e -> {
                    frame.dispose();
                    Account.getAccount(username, language);
                }

        );

        icon = new ImageIcon("img/buttons/home.png");
        buttons[1] = new JButton("HOME", icon);
        buttons[1].setBounds(0, 110, 180, 169);
        buttons[1].addActionListener(
                e -> {
                    frame.dispose();
                    Homepage.getHomepage(username, language);
                }
        );


        icon = new ImageIcon("img/buttons/chapters.png");
        buttons[2] = new JButton("LESSONS", icon);
        buttons[2].setBounds(0, 279, 180, 169);
        buttons[2].addActionListener(
                e -> {
                    frame.dispose();
                    Lessons.getLessons(username, language);
                }

        );

        icon = new ImageIcon("img/buttons/dictionary.png");
        buttons[3] = new JButton("DICTIONARY", icon);
        buttons[3].setBounds(0, 448, 180, 169);
        buttons[3].addActionListener(
                e -> {
                    frame.dispose();
                    Dictionary.getDictionary(username, language);
                }

        );


//        icon = new ImageIcon("img/buttons/practice.png");
//        buttons[4] = new JButton("PRACTICE", icon);
//        buttons[4].setBounds(0, 617, 180, 169);
//        buttons[4].addActionListener(
//                e -> {
//                    frame.dispose();
//                    Practice.getPractice(username, language);
//                }
//
//        );


        icon = new ImageIcon("img/buttons/languages.png");
        buttons[4] = new JButton("COURSES", icon);
        buttons[4].setBounds(0, 786, 180, 169);
        buttons[4].addActionListener(
                e -> {
                    frame.dispose();
                    Languages.getLanguages(username);
                }

        );

        icon = new ImageIcon("img/buttons/logout.png");
        buttons[5] = new JButton("LOGOUT", icon);
        buttons[5].setBounds(1346, 0, 236, 110);
        buttons[5].addActionListener(
                e -> {
                    frame.dispose();
                    Login.getLogin();
                }
        );

        for(int i = 0; i < 6; i++) {
            buttons[i].setFont(font);
            buttons[i].setVerticalTextPosition(SwingConstants.BOTTOM);
            buttons[i].setHorizontalTextPosition(SwingConstants.CENTER);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setBackground(buttonColor);
            buttons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttons[i].setBorderPainted(false);
            addButtonMouseAdapter(buttons[i]);
        }

        buttons[6] = new JButton();
        buttons[6].setFont(new Font("Century Gothic", Font.BOLD, 40));
        buttons[6].setBounds(236, 0, 1110, 110);
        buttons[6].setHorizontalTextPosition(SwingConstants.CENTER);
        buttons[6].setForeground(Color.WHITE);
        buttons[6].setBackground(buttonColor);
        buttons[6].setBorderPainted(false);

        return buttons;
    }

    public static void addButtonMouseAdapter(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(mouseEnteredColor);
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(buttonColor);
            }
        });

    }
}