package model;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewChannel {
    private Connection connection = (new ConnectionFactory()).getConnection();

    public void addChannel(Channel channel){
        String insert = "INSERT INTO defaultChannels(name, type, auth) VALUES(?,?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setString(1, channel.getName());
            stmt.setString(2, channel.getType());
            stmt.setString(3, channel.getAuthType());
            stmt.execute();
            stmt.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Canal cadastrado com sucesso!");
            alert.show();
        }
        catch (SQLException u){
            throw new RuntimeException(u);
        }
    }
}
