package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.ForgotPassword;
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForgotPasswordController implements Initializable {
    @FXML
    private Pane dynamicBox1;
    @FXML
    private Pane dynamicBox2;
    @FXML
    private Pane dynamicBox3;
    @FXML
    private TextField emailInputField;
    @FXML
    private TextField newPasswordField;
    @FXML
    private TextField newConfirmField;
    @FXML
    private TextField codeField;
    @FXML
    private Button recoveryButton;
    @FXML
    private Button tradeButton;
    @FXML
    private Button verifyButton;
    @FXML
    private Button returnButton;


    ForgotPassword forgot = new ForgotPassword();

    public void recoveryAction() {
        forgot.setEmail(emailInputField.getText());
        if (forgot.emailTest()) {
            forgot.sendEmail(forgot.getEmail());
            recoveryButton.setDefaultButton(false);
            verifyButton.setDefaultButton(true);
            dynamicBox1.setVisible(false);
            dynamicBox2.setVisible(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Email inexistente!");
            alert.show();
        }

    }

    public void verifyAction() {
        if (forgot.codeTest(codeField.getText())) {
            verifyButton.setDefaultButton(false);
            tradeButton.setDefaultButton(true);
            dynamicBox2.setVisible(false);
            dynamicBox3.setVisible(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Código incorreto!");
            alert.show();
        }
    }

    public void tradeAction() throws IOException {
        if (newPasswordField.getText().equals(newConfirmField.getText())) {
            forgot.passwordUpdate(newPasswordField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Senha alterada com sucesso!");
            alert.show();
            Main.changeScene("loginForm");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("A senha deve ser igual a sua confirmação!");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recoveryButton.setDefaultButton(true);
        recoveryButton.setOnAction(actionEvent -> {
            try {
                recoveryAction();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        verifyButton.setOnAction(actionEvent -> {
            try {
                verifyAction();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        tradeButton.setOnAction(actionEvent -> {
            try {
                tradeAction();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        returnButton.setOnAction(actionEvent -> {
            try {
                Main.changeScene("loginForm");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
