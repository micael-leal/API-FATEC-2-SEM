package model;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

public class DefaultChannelDAO {
    private Connection connection = (new ConnectionFactory()).getConnection();

    public void addChannel(DefaultChannel defaultChannel){
        String sql = "INSERT INTO defaultchannel(name, type) VALUES(?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, defaultChannel.getName());
            stmt.setString(2, defaultChannel.getType());
            stmt.execute();
            stmt.close();
        }
        catch (SQLException u){
            throw new RuntimeException(u);
        }
    }
}
