package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ConfigCanaisController implements Initializable {

    @FXML
    private ComboBox<String> choiceCHANNEL;
    @FXML
    private TextField fieldCHANNELID;
    @FXML
    private VBox dynamicVBox;
    @FXML
    private final ArrayList<String> channelList = new ArrayList<>();

    @FXML
    public void changeContent() {
        try {
            Connection conn;
            conn = ConnectionFactory.getConnection();
            PreparedStatement stmt;
            ResultSet result;
            stmt = conn.prepareStatement("SELECT auth FROM defaultChannels WHERE name = (?)");
            stmt.setString(1, choiceCHANNEL.getValue());
            result = stmt.executeQuery();
            result.next();

            if (result.getString("auth").equals("TOKEN")) {
                dynamicVBox.getChildren().clear();
                TextField tokenField = new TextField();
                Label tokenLabel = new Label("Insira o token:");
                tokenField.getStyleClass().add("searchField");
                tokenField.setPromptText("Ex: 43HBD39AB2UD4UEF...");
                tokenField.setPrefWidth(350);
                dynamicVBox.setAlignment(Pos.CENTER_LEFT);
                dynamicVBox.setPadding(new Insets(0, 0, 0, 80));

                dynamicVBox.getChildren().addAll(tokenLabel, tokenField);
            } else {
                dynamicVBox.getChildren().clear();
                Button btn1 = new Button("Teste 4");
                Button btn2 = new Button("Teste 5");
                Button btn3 = new Button("Teste 6");
                dynamicVBox.getChildren().addAll(btn1, btn2, btn3);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection conn;
            conn = ConnectionFactory.getConnection();
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT name FROM defaultChannels");

            while (resultSet.next()) {
                channelList.add(resultSet.getString("name"));
            }
            choiceCHANNEL.getItems().addAll(channelList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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