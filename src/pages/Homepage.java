package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Homepage extends JFrame {
    public static void getHomepage(String username) {

        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        ImageIcon icon;

        JButton[] buttons = Buttons.getButtons(frame, username);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("HOME");


//        newsTitles[0].setBounds(80, 347, 451, 60);
//        newsTitles[1].setBounds(650, 347, 451, 60);
//        newsTitles[2].setBounds(80, 647, 451, 60);
//        newsTitles[3].setBounds(650, 647, 451, 60);

    }

}