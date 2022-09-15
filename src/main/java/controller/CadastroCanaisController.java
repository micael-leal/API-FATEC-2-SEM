package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.DefaultChannel;
import model.DefaultChannelDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroCanaisController implements Initializable {
    @FXML
    private ChoiceBox<String> type_channel;

    private String[] list_type_channel = {"Plataforma/ERP", "Marketplace", "Meio de Pagamento"};

    @FXML
    private ChoiceBox<String> authentication_type;

    private String[] list_type_authentication = {"Usuário e Senha", "Token"};

    @FXML
    private TextField channel_input_field;

    @FXML
    private Button saveButton;

    @FXML
    private void saveChannelAction(ActionEvent event){
        System.out.println("Você clicou!");

        String type_c = type_channel.getValue();
        String type_a = authentication_type.getValue();
        String channel = channel_input_field.getText();

        DefaultChannel defaultChannel = new DefaultChannel();
        defaultChannel.setName(channel);
        defaultChannel.setType(type_c);

        DefaultChannelDAO defaultChannelDAO = new DefaultChannelDAO();
        defaultChannelDAO.addChannel(defaultChannel);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type_channel.getItems().addAll(list_type_channel);
        authentication_type.getItems().addAll(list_type_authentication);
    }
}
