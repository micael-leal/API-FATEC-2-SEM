package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Channel;
import model.NewChannel;
import model.User;
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class admDefaultChannelRegisterController implements Initializable {
    @FXML
    private ComboBox<String> type_channel;

    private String[] list_type_channel = {"Plataforma/ERP", "Marketplace", "Meio de Pagamento"};

    @FXML
    private ComboBox<String> authentication_type;

    private String[] list_type_authentication = {"TOKEN", "LOGIN"};

    @FXML
    private TextField channel_input_field;

    @FXML
    private Text userLABEL;

    @FXML
    private void leaveButtonAction() throws IOException {
        Main.changeScene("loginForm");
    }

    @FXML
    private void goToAdmActiveChannels(ActionEvent event) throws IOException {
        Main.changeScene("admDefaultChannel");
    }

    @FXML
    private void goToCreateAdm(ActionEvent event) throws IOException {
        Main.changeScene("admCreateAccount");
    }

    @FXML
    private void saveChannelAction(ActionEvent event){
        String type_c = type_channel.getValue();
        String type_a = authentication_type.getValue();
        String channel_name = channel_input_field.getText();

        Channel channel = new Channel(
            0,
            channel_name,
            type_c,
            type_a
        );

        NewChannel newChannel = new NewChannel();
        newChannel.addChannel(channel);
        channel_input_field.setText("");
        authentication_type.valueProperty().set(null);
        type_channel.valueProperty().set(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userLABEL.setText("Olá, admin");
        type_channel.getItems().addAll(list_type_channel);
        authentication_type.getItems().addAll(list_type_authentication);
    }
}
