package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.ConnectionFactory;
import model.User;
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class userProfileController implements Initializable {
    @FXML
    private Button buttonSaveChange;
    @FXML
    private TextField cpfField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private Text userLABEL;
    @FXML
    private TextField nameField;
    @FXML
    private Button userProfileButton;
    @FXML
    private Button admProfileButton;
    @FXML
    private void goToUserActiveChannels() throws IOException {
        Main.changeScene("userActiveConfig");
    }
    @FXML
    private void goToUserChannelConfig() throws IOException {
        Main.changeScene("userChannelConfig");
    }
    @FXML
    public void goToAdmProfile(ActionEvent actionEvent) throws IOException {
        Main.changeScene("admDefaultChannel");
    }
    @FXML
    private void leaveButtonAction() throws IOException {
        User.getInstance().cleanUserSession();
        Main.changeScene("loginForm");
    }
    @FXML
    public void buttonSaveChange(ActionEvent event) throws IOException {
        String Nome = nameField.getText();
        String Docum = cpfField.getText();
        String Email = emailField.getText();
        String Phone = phoneField.getText();

        Connection conn;
        PreparedStatement pstm;
        String sql = "UPDATE users SET email=(?), phone=(?), name=(?), document=(?) WHERE user_id=?";
        conn = ConnectionFactory.getConnection();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, Email);
            pstm.setString(2, Phone);
            pstm.setString(3, Nome);
            pstm.setString(4, Docum);
            pstm.setInt(5, User.getInstance().getId());

            pstm.executeUpdate();
            pstm.close();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Alteração feita com sucesso!");
            alert.show();
        } catch (Exception erro) {
            System.out.println(erro);
        }
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {

        if (!(User.getInstance().getEmail().equals("admin"))) {
            admProfileButton.setVisible(false);
        }

        userLABEL.setText("Olá, " + User.getInstance().getName());
        nameField.setText(User.getInstance().getName());
        cpfField.setText(User.getInstance().getDocument());
        emailField.setText(User.getInstance().getEmail());
        phoneField.setText(User.getInstance().getPhone());


        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}



