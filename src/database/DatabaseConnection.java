package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/language_app_users", "db_user", "language-app-users"
            );
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}