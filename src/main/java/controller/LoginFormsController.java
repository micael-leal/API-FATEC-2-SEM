
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.*;
import model.ConnectionFactory;
import model.User;
import view.Main;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginFormsController implements Initializable {
    @FXML
    private TextField emailInputField;
    @FXML
    private PasswordField passwordInputField;
    @FXML
    private Label saveMessageButton;

    @FXML
    public void loginButton(ActionEvent event) {
        String email = emailInputField.getText();
        String password = passwordInputField.getText();

        if (email.isBlank() || password.isBlank()) {
            saveMessageButton.setText("Os campos devem ser preenchidos!");
            System.out.println("Preenchimento obrigatório!");
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("Preencha todos os campos!");
//            alert.show();
        }
        else {
            PreparedStatement stmt;
            ResultSet resultSet;
            Connection conn;
            conn = ConnectionFactory.getConnection();
            try {
                stmt = conn.prepareStatement("SELECT * FROM users WHERE email=(?)  AND password=(?)");
                stmt.setString(1, email);
                stmt.setString(2, password);
                resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    User usuarioLogado = User.getInstance();
                    usuarioLogado.setId(resultSet.getInt("user_id"));
                    usuarioLogado.setName(resultSet.getString("name"));
                    usuarioLogado.setEmail(resultSet.getString("email"));
                    usuarioLogado.setPassword(resultSet.getString("password"));
                    usuarioLogado.setPassword(resultSet.getString("phone"));
                    usuarioLogado.setDocument(resultSet.getString("document"));
                    usuarioLogado.setType(resultSet.getInt("type_adm"));
                    Main.changeScene("userActiveConfig");
                } else {
                    saveMessageButton.setText("Essa combinação de e-mail e senha está incorreta.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
    }

    public void forgotPasswordOnAction(ActionEvent actionEvent) {
        Main.changeScene("ChannelRegister");//alterar para a tela de req senha!!!!
    }

    public void createAccountOnAction(ActionEvent actionEvent) {
        Main.changeScene("userRegisterUser");//alterar para a tela user register - depois.
    }
}



