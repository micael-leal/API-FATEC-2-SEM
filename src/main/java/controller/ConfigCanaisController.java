package controller;

// import javafx.fxml.FXML;
// import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.*;
import model.RegisteredChannel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConfigCanaisController implements Initializable {

    @FXML
    private TextField channelName = new TextField();
    @FXML
    private TextField channelId = new TextField();
    @FXML
    private TextField channelUserLogin = new TextField();
    @FXML
    private TextField channelUserPassword = new TextField();
    @FXML
    private TextField channelToken = new TextField();
    @FXML
    private Button channelSubmitButton = new Button();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Channel channel = GetChannelById.GetChannel(1);

        channelSubmitButton.setOnAction(event -> {
            Connection conn;
            conn = ConnectionFactory.getConnection();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("INSERT INTO registeredChannelToken(user_id, channel_id, token) VALUES (1, ?, ?)");
                stmt.setString(1, channelId.getText());
                stmt.setString(2, channelToken.getText());
                stmt.execute();
                conn.close();

//                RegisteredChannel rc = getTableView().getItems().get(getIndex());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(("[INSERT] You have clicked: \n" ));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
//            if(channel.getAuthType().equals("TOKEN")) {
//                RegisteredChannelToken registeredChannelToken = GetRegisteredChannelById.GetChannelToken(channel.getId());
//                channelName.setText(channel.getName());
//                channelId.setText(Integer.toString(channel.getId()));
//                channelToken.setText(registeredChannelToken.getToken());
//            } else {
//                RegisteredChannelLogin registeredChannelLogin = GetRegisteredChannelById.GetChannelLogin(channel.getId());
//                channelName.setText(channel.getName());
//                channelId.setText(Integer.toString((channel.getId())));
//                channelUserLogin.setText(registeredChannelLogin.getLogin());
//                channelUserPassword.setText((registeredChannelLogin.getPassword()));
//            }
        });
    }
}