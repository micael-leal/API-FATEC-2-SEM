package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.*;
import view.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class userChannelConfigController implements Initializable {
    @FXML
    private ComboBox<String> choiceCHANNEL;
    @FXML
    private TextField fieldCHANNELID;
    @FXML
    private VBox dynamicVBox;
    @FXML
    private final ArrayList<String> channelList = new ArrayList<>();

    @FXML
    private void goToActiveChannels() {
        Main.changeScene("userActiveConfig");
    }

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
                tokenField.getStyleClass().add("inputField");
                tokenField.setPromptText("Ex: 43HBD39AB2UD4UEF...");
                tokenField.setPrefWidth(300);

                dynamicVBox.setAlignment(Pos.CENTER_LEFT);
                dynamicVBox.setPadding(new Insets(0, 0, 0, 47));
                dynamicVBox.getChildren().addAll(tokenLabel, tokenField);
            } else {
                dynamicVBox.getChildren().clear();
                HBox container = new HBox();
                VBox field1 = new VBox();
                VBox field2 = new VBox();
                TextField userField = new TextField();
                PasswordField passwordField = new PasswordField();
                Label userLabel = new Label("Usuário:");
                Label passwordLabel = new Label("Senha:");
                userField.getStyleClass().add("inputField");
                passwordField.getStyleClass().add("inputField");
                userField.setPrefWidth(300);
                passwordField.setPrefWidth(300);
                userField.setPromptText("Ex: seunome@email.com");
                passwordField.setPromptText("Ex: as!98fjHD91is@");

                field1.getChildren().addAll(userLabel, userField);
                field2.getChildren().addAll(passwordLabel, passwordField);
                container.getChildren().addAll(field1, field2);
                dynamicVBox.setAlignment(Pos.CENTER_LEFT);
                dynamicVBox.setPadding(new Insets(0, 0, 0, 47));
                dynamicVBox.getChildren().addAll(container);
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
    }
}