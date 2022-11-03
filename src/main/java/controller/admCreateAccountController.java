package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.ConnectionFactory;
import view.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class admCreateAccountController {
    @FXML
    private TextField InputNome;
    @FXML
    private TextField InputEmail;
    @FXML
    private TextField InputSenha;
    @FXML
    private TextField InputCSenha;

    public void enterUserOnAction() throws IOException {
        Main.changeScene("loginForm");
    }
    @FXML
    protected void Cadastrar() {
        String Nome, Email, Senha, Csenha;
        Nome = InputNome.getText();
        Email = InputEmail.getText();
        Senha = InputSenha.getText();
        Csenha = InputCSenha.getText();

        if (Senha.equals(Csenha)){
            Connection conn;
            PreparedStatement pstm;
            String sql = "insert into users(,name,email,password) values (?,?,?,?,?)";
            conn = ConnectionFactory.getConnection();
            try {
                pstm = conn.prepareStatement(sql);
                pstm.setString(2, Nome);
                pstm.setString(3, Email);
                pstm.setString(5, Senha);

                pstm.execute();
                pstm.close();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Cadastro Feito");
                alert.show();
                Main.changeScene("loginForm");
            } catch (Exception erro) {
                System.out.println("Cadastro" + erro);
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("As senhas não estão Corretas");
            alert.show();

        }

    }

    @FXML
    private void leaveButtonAction() throws IOException {
        Main.changeScene("loginForm");
    }

    @FXML
    private void goToAdmDefaultChannelRegister(ActionEvent event) throws IOException {
        Main.changeScene("admDefaultChannelRegister");
    }

    @FXML
    private void goToAdmActiveChannels(ActionEvent event) throws IOException {
        Main.changeScene("admDefaultChannel");
    }

    @FXML
    private void goToUserConfigChannel(ActionEvent event) throws IOException {
        Main.changeScene("userActiveConfig");
    }
}
