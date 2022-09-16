package controller;

// import javafx.fxml.FXML;
// import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.*;

import java.net.URL;
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

        if(channel.getAuthType().equals("TOKEN")) {
            RegisteredChannelToken registeredChannelToken = GetRegisteredChannelById.GetChannelToken(channel.getId());
            channelName.setText(channel.getName());
            channelId.setText(Integer.toString(channel.getId()));
            channelToken.setText(registeredChannelToken.getToken());
        } else {
            RegisteredChannelLogin registeredChannelLogin = GetRegisteredChannelById.GetChannelLogin(channel.getId());
            channelName.setText(channel.getName());
            channelId.setText(Integer.toString((channel.getId())));
            channelUserLogin.setText(registeredChannelLogin.getLogin());
            channelUserPassword.setText((registeredChannelLogin.getPassword()));
        }
    }
}