package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.User;
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class userFAQController implements Initializable {
    @FXML
    private Button admProfileButton;

    @FXML
    private Text userLABEL;

    @FXML
    private void goToUserChannelConfig() throws IOException {
        Main.changeScene("userChannelConfig");
    }

    @FXML
    private void goToUserActiveChannels() throws IOException {
        Main.changeScene("userActiveConfig");
    }

    @FXML
    private void goToProfileChannels() throws IOException {
        Main.changeScene("userProfile");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!(User.getInstance().getType() == 1)) {
            admProfileButton.setVisible(false);
        }

        userLABEL.setText("Olá, " + User.getInstance().getName());
    }
}
