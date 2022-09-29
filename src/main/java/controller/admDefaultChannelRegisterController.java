package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Channel;
import model.NewChannel;

import java.net.URL;
import java.util.ResourceBundle;

public class admDefaultChannelRegisterController implements Initializable {
    @FXML
    private ChoiceBox<String> type_channel;

    private String[] list_type_channel = {"Plataforma/ERP", "Marketplace", "Meio de Pagamento"};

    @FXML
    private ChoiceBox<String> authentication_type;

    private String[] list_type_authentication = {"TOKEN", "LOGIN"};

    @FXML
    private TextField channel_input_field;

    @FXML
    private Button saveButton;

    @FXML
    private void saveChannelAction(ActionEvent event){
        System.out.println("Você clicou!");

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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type_channel.getItems().addAll(list_type_channel);
        authentication_type.getItems().addAll(list_type_authentication);
    }
}
