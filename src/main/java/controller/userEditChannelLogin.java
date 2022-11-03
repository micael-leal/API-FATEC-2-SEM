package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.ConnectionFactory;
import model.RegisteredChannel;
import model.User;
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class userEditChannelLogin implements Initializable {
    @FXML
    private TextField Inputcanal;
    @FXML
    private TextField Inputuser;
    @FXML
    private TextField Inputsenha;
    @FXML
    private TextField Inputid;
    @FXML
    private Button channelConfig;
    @FXML
    private Button admProfileButton;

    @FXML
    private void goToFAQ() throws IOException {
        Main.changeScene("FAQ");
    }
    @FXML
    private void leaveButtonAction() throws IOException {
        Stage stage = (Stage) channelConfig.getScene().getWindow();
        stage.close();
        User.getInstance().cleanUserSession();
        Main.changeScene("loginForm");
    }

    @FXML
    private void goToUserChannelConfig() throws IOException {
        Stage stage = (Stage) channelConfig.getScene().getWindow();
        stage.close();
        Main.changeScene("userChannelConfig");
    }

    @FXML
    private void goToUserActiveChannels() throws IOException {
        Stage stage = (Stage) channelConfig.getScene().getWindow();
        stage.close();
        Main.changeScene("userActiveConfig");
    }

    @FXML
    private void goToProfileChannels() throws IOException {
        Stage stage = (Stage) channelConfig.getScene().getWindow();
        stage.close();
        Main.changeScene("userProfile");
    }

    @FXML
    public void goToAdmProfile(ActionEvent actionEvent) throws IOException {
        Main.changeScene("admDefaultChannel");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (!(User.getInstance().getEmail().equals("admin"))) {
            admProfileButton.setVisible(false);
        }

    }

    void setTextField(int id) {
        Inputid.setText(String.valueOf(id));
        PreparedStatement stmt;
        ResultSet resultSet;
        Connection conn;
        conn = ConnectionFactory.getConnection();
        ObservableList<RegisteredChannel> registeredChannelList = FXCollections.observableArrayList();
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        list.add("");

        try {
            stmt = conn.prepareStatement("SELECT * from registeredChannelLogin where registeredChannelLogin_id = (?)");
            stmt.setInt(1, id);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                registeredChannelList.add(new RegisteredChannel(
                        resultSet.getInt("registeredChannelLogin_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("channel_id"),
                        "null",
                        "null",
                        "null",
                        list.set(0, resultSet.getString("login")),
                        list.set(1, resultSet.getString("password"))
                ));
            }

            Inputuser.setText(list.get(0));
            Inputsenha.setText(list.get(1));

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUpdate() {
        String user, password, id;
        id = Inputid.getText();
        user = Inputuser.getText();
        password = Inputsenha.getText();

        PreparedStatement stmt;
        Connection conn;
        conn = ConnectionFactory.getConnection();
        try {
            stmt = conn.prepareStatement("update registeredChannelLogin set login = (?), password = (?) where registeredChannelLogin_id = (?)");
            stmt.setString(1, user);
            stmt.setString(2, password);
            stmt.setString(3, id);
            stmt.execute();
            stmt.close();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Edição Feita");
            alert.show();

            goToUserActiveChannels();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}





