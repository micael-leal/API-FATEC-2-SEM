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
    private static Scene channelConfigScreen;
    private static Scene activeConfigScreen;
    private static Scene channelRegisterScreen;

    private static Scene activeChannelsScreen;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setResizable(false);
        Image staqeIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png")));
        primaryStage.getIcons().add(staqeIcon);

        try {
            AnchorPane fxmlChannelConfigScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlConfigCanais.fxml")));
            channelConfigScreen = new Scene(fxmlChannelConfigScreen);

            AnchorPane fxmlActiveConfigScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlConfigAtiva.fxml")));
            activeConfigScreen = new Scene(fxmlActiveConfigScreen);

            AnchorPane fxmlChannelRegister = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlCadastroCanais.fxml")));
            channelRegisterScreen = new Scene(fxmlChannelRegister);

            AnchorPane fxmlActiveChannels = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlCanaisAtivos.fxml")));
            activeChannelsScreen = new Scene(fxmlActiveChannels);

        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.setScene(activeChannelsScreen);
        primaryStage.show();
    }

    public static void changeScene(String scene) {
        switch (scene) {
            case "ConfigCanais" -> stage.setScene(channelConfigScreen);
            case "ActiveConfig" -> stage.setScene(activeConfigScreen);
            case "CadastroCanais" -> stage.setScene(channelRegisterScreen);
        }
    }

    public static void main(String[] args) { launch(args); }
}