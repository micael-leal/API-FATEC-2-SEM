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
    private static Scene userChannelConfigScreen;
    private static Scene userActiveConfigScreen;
    private static Scene admDefaultChannelRegisterScreen;
    private static Scene admDefaultChannelsScreen;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setResizable(false);
        Image staqeIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png")));
        primaryStage.getIcons().add(staqeIcon);

        try {
            AnchorPane fxmlUserChannelConfigScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlUserChannelConfig.fxml")));
            userChannelConfigScreen = new Scene(fxmlUserChannelConfigScreen);

            AnchorPane fxmlUserActiveConfigScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlActiveConfig.fxml")));
            userActiveConfigScreen = new Scene(fxmlUserActiveConfigScreen);

            AnchorPane fxmlAdmDefaultChannelRegister = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlAdmDefaultChannelRegister.fxml")));
            admDefaultChannelRegisterScreen = new Scene(fxmlAdmDefaultChannelRegister);

            AnchorPane fxmlAdmDefaultChannels = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlAdmDefaultChannels.fxml")));
            admDefaultChannelsScreen = new Scene(fxmlAdmDefaultChannels);

        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.setScene(admDefaultChannelRegisterScreen);
        primaryStage.show();
    }

    public static void changeScene(String scene) {
        switch (scene) {
            case "userChannelConfig" -> {
                stage.setScene(userChannelConfigScreen);
            }
            case "userActiveConfig" -> {
                stage.setScene(userActiveConfigScreen);
            }
            case "admDefaultChannelRegister" -> {
                stage.setScene(admDefaultChannelRegisterScreen);
            }
            case "admDefaultChannel" -> {
                stage.setScene(admDefaultChannelsScreen);
            }
        }
    }

    public static void main(String[] args) { launch(args); }
}