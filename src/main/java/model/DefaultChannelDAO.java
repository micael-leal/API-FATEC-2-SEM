package model;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

public class DefaultChannelDAO {
    private Connection connection = (new ConnectionFactory()).getConnection();

    public void addChannel(DefaultChannel defaultChannel){
        String sql = "INSERT INTO defaultchannels(name, type, auth) VALUES(?,?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, defaultChannel.getName());
            stmt.setString(2, defaultChannel.getType());
            stmt.setString(3, defaultChannel.getAuth());
            stmt.execute();
            stmt.close();
        }
        catch (SQLException u){
            throw new RuntimeException(u);
        }
    }
}
