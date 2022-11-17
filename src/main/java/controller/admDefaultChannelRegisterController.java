package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Channel;
import model.ConnectionFactory;
import model.NewChannel;
import model.User;
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        User.getInstance().cleanUserSession();
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
    public void goToUserProfile(ActionEvent actionEvent) throws IOException {
        Main.changeScene("userActiveConfig");
    }

    @FXML
    private void saveChannelAction(ActionEvent event){
        String type_c = type_channel.getValue();
        String type_a = authentication_type.getValue();
        String channel_name = channel_input_field.getText();

        PreparedStatement stmt;
        ResultSet resultSet;
        Connection conn;
        conn = ConnectionFactory.getConnection();

        try {
            stmt = conn.prepareStatement( "select count(name) as countRegister from defaultChannels where defaultChannels.name = " +'"'+ channel_name.toUpperCase() +'"');
            resultSet = stmt.executeQuery();
            resultSet.next();
            int countRegister = resultSet.getInt("countRegister");
            conn.close();
            if (countRegister == 0){
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
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Já existe um canal cadastrado com esse nome!");
                alert.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userLABEL.setText("Olá, admin");
        type_channel.getItems().addAll(list_type_channel);
        authentication_type.getItems().addAll(list_type_authentication);
    }
}
