package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;


public class ConnectionFactory {

    private static String username;
    private static String password;

    public static Connection getConnection() {

        Dotenv dotenv = Dotenv.load();
        username = dotenv.get("USER_NAME");
        password = dotenv.get("USER_PASSWORD");


        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/apiTrackCash", username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
