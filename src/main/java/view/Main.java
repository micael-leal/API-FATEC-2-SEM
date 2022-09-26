package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    private static Stage stage;
    private static Scene channelConfigTokenScreen;
    private static Scene channelConfigLoginScreen;
    private static Scene activeConfigScreen;
    private static Scene channelRegisterScreen;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setResizable(false);
        Image staqeIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png")));
        primaryStage.getIcons().add(staqeIcon);

        try {
            AnchorPane fxmlChannelConfigScreenToken = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlConfigCanaisToken.fxml")));
            channelConfigTokenScreen = new Scene(fxmlChannelConfigScreenToken);

            AnchorPane fxmlChannelConfigScreenUserPass = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlConfigCanaisUsuarioESenha.fxml")));
            channelConfigLoginScreen = new Scene(fxmlChannelConfigScreenUserPass);

            AnchorPane fxmlActiveConfigScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlConfigAtiva.fxml")));
            activeConfigScreen = new Scene(fxmlActiveConfigScreen);

            AnchorPane fxmlChannelRegister = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlCadastroCanais.fxml")));
            channelRegisterScreen = new Scene(fxmlChannelRegister);
        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.setScene(activeConfigScreen);
        primaryStage.show();
    }

    public static void changeScene(String scene) {
        switch (scene) {
            case "ConfigToken" -> stage.setScene(channelConfigTokenScreen);
            case "ConfigLogin" -> stage.setScene(channelConfigLoginScreen);
            case "ActiveConfig" -> stage.setScene(activeConfigScreen);
            case "ChannelRegister" -> stage.setScene(channelRegisterScreen);
        }
    }

    public static void main(String[] args) { launch(args); }
}