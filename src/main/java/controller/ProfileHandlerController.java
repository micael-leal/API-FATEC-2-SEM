package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import model.User;
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileHandlerController implements Initializable {
    @FXML
    private Button loginAdmButton;
    @FXML
    private Button loginUserButton;
    @FXML
    private Hyperlink linkReturn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        linkReturn.setOnAction(actionEvent -> {
            try {
                User.getInstance().cleanUserSession();
                Main.changeScene("loginForm");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        loginAdmButton.setOnAction(actionEvent -> {
            try {
                Main.changeScene("admDefaultChannel");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        loginUserButton.setOnAction(actionEvent -> {
            try {
                Main.changeScene("userActiveConfig");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
