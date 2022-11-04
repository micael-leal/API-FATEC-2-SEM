package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.*;
import view.Main;

import java.io.IOException;
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
    private VBox dynamicVBox;
    @FXML
    private Button buttonSAVE;
    @FXML
    private Text userLABEL;
    @FXML
    private Button admProfileButton;
    private final ArrayList<String> channelList = new ArrayList<>();
    private String selectedType;
    private String token = "";
    private String usuario = "";
    private String senha = "";

    @FXML
    private void leaveButtonAction() throws IOException {
        User.getInstance().cleanUserSession();
        Main.changeScene("loginForm");
    }

    @FXML
    private void goToFAQ() throws IOException {
        Main.changeScene("FAQ");
    }

    @FXML
    public void goToProfileChannels(ActionEvent event) throws IOException{
        Main.changeScene("userProfile");
    }


   @FXML
    private void goToUserActiveChannels() throws IOException {
        Main.changeScene("userActiveConfig");
    }

    @FXML
    public void goToAdmProfile(ActionEvent actionEvent) throws IOException {
        Main.changeScene("admDefaultChannel");
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
                selectedType = "TOKEN";
                dynamicVBox.getChildren().clear();
                TextField tokenField = new TextField();
                Label tokenLabel = new Label("Insira o token:");
                tokenField.getStyleClass().add("inputField");
                tokenField.setPromptText("Ex: 43HBD39AB2UD4UEF...");
                tokenField.setPrefWidth(300);

                dynamicVBox.setAlignment(Pos.CENTER_LEFT);
                dynamicVBox.setPadding(new Insets(0, 0, 0, 47));
                dynamicVBox.getChildren().addAll(tokenLabel, tokenField);

                tokenField.setOnKeyTyped(actionEvent -> { token = tokenField.getText(); });
            } else {
                selectedType = "LOGIN";
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

                userField.setOnKeyTyped(actionEvent -> { usuario = userField.getText(); });
                passwordField.setOnKeyTyped(actionEvent -> { senha = passwordField.getText(); });
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (!(User.getInstance().getEmail().equals("admin"))) {
            admProfileButton.setVisible(false);
        }

        userLABEL.setText("Olá, " + User.getInstance().getName());
        try {
            Connection conn = ConnectionFactory.getConnection();
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT name FROM defaultChannels");

            while (resultSet.next()) {
                channelList.add(resultSet.getString("name"));
            }
            choiceCHANNEL.getItems().addAll(channelList);

            buttonSAVE.setOnAction(actionEvent -> {
                int usuarioLogado = User.getInstance().getId();
                PreparedStatement stmt1;
                PreparedStatement stmt2;
                ResultSet result;
                try {
                    stmt1 = conn.prepareStatement("SELECT channel_id FROM defaultChannels WHERE name LIKE (?)");
                    stmt1.setString(1, choiceCHANNEL.getValue());
                    result = stmt1.executeQuery();
                    result.next();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                if (selectedType == null)
                    return;

                if(selectedType.equals("TOKEN")) {
                    try {
                        stmt2 = conn.prepareStatement("INSERT INTO registeredChannelToken (user_id, channel_id, token) VALUES (?, ?, ?)");
                        stmt2.setInt(1, usuarioLogado);
                        stmt2.setInt(2, result.getInt("channel_id"));
                        stmt2.setString(3, token);
                        stmt2.execute();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert1.setContentText("OK");
                    alert1.show();
                } else {
                    try {
                        stmt2 = conn.prepareStatement("INSERT INTO registeredChannelLogin (user_id, channel_id, login, password) VALUES (?, ?, ?, ?)");
                        stmt2.setInt(1, usuarioLogado);
                        stmt2.setInt(2, result.getInt("channel_id"));
                        stmt2.setString(3, usuario);
                        stmt2.setString(4, senha);
                        stmt2.execute();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.setContentText("OK");
                    alert2.show();
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}