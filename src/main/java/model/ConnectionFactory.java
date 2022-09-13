package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/apiTrackCash", "root", "Leandr@1997");
        } catch (SQLException excecao) {
            throw new RuntimeException(excecao);
        }
    }
}
