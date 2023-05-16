package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Level {

    public static void calculateLevel(String username, String language) {

        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        int exp = 0, level = 0;
        String getProgress = "SELECT language_exp, language_level FROM user_progress WHERE username = ? AND languageID = ?";
        String updateProgress = "UPDATE user_progress SET language_exp = ?, language_level = ? WHERE username = ? AND languageID = ?";

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(getProgress);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, language);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                exp = Integer.parseInt(resultSet.getString("language_exp"));
                level = Integer.parseInt(resultSet.getString("language_level"));
            }

            exp += 10;
            if(exp == (level + 1) * 100) {
                level++;
                exp = 0;
            }

            preparedStatement = connection.prepareStatement(updateProgress);
            preparedStatement.setString(1, Integer.toString(exp));
            preparedStatement.setString(2, Integer.toString(level));
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, language);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
