package controller;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.ConnectionFactory;
import view.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userRegisterController {
    @FXML
    private TextField InputDocument;
    @FXML
    private TextField InputNome;
    @FXML
    private TextField InputEmail;
    @FXML
    private TextField InputTele;
    @FXML
    private TextField InputSenha;
    @FXML
    private TextField InputCSenha;

    public void enterUserOnAction() throws IOException {
        Main.changeScene("loginForm");
    }
    @FXML
    protected void Cadastrar() {
        String Docum, Nome, Email, Tele, Senha, Csenha;
        Docum = InputDocument.getText();
        Nome = InputNome.getText();
        Email = InputEmail.getText();
        Tele = InputTele.getText();
        Senha = InputSenha.getText();
        Csenha = InputCSenha.getText();

        Connection conn;
        PreparedStatement stmt;
        conn = ConnectionFactory.getConnection();
        try {
            stmt = conn.prepareStatement("select email from users where email = (?)");
            stmt.setString(1, Email);
            ResultSet rs = stmt.executeQuery();
            String result = null;
            if (rs.next())
                result = rs.getString(1);

            if (Email.equals(result)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Email já cadastrado");
                alert.show();

            } else {

                if (Senha.equals(Csenha)) {
                    String sql = "insert into users(document,name,email,phone,password) values (?,?,?,?,?)";
                    conn = ConnectionFactory.getConnection();
                    try {
                        stmt = conn.prepareStatement(sql);
                        stmt.setString(1, Docum);
                        stmt.setString(2, Nome);
                        stmt.setString(3, Email);
                        stmt.setString(4, Tele);
                        stmt.setString(5, Senha);

                        stmt.execute();
                        stmt.close();
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Cadastro Feito");
                        alert.show();
                        Main.changeScene("loginForm");
                    } catch (Exception erro) {
                        System.out.println("Cadastro" + erro);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("As senhas não estão Corretas");
                    alert.show();

                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }}