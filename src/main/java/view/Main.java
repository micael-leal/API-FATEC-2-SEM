package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stage;
    private static Scene channelConfigScreenToken;
    private static Scene channelConfigScreenUserPass;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // TODO: Código principal inserido aqui dentro
        stage = primaryStage;
        try {
            AnchorPane fxmlChannelConfigScreenToken = FXMLLoader.load(getClass().getResource("/fxmlConfigCanaisToken.fxml"));
            AnchorPane fxmlchannelConfigScreenUserPass = FXMLLoader.load(getClass().getResource("/fxmlConfigCanaisUsuarioESenha.fxml"));
            channelConfigScreenToken = new Scene(fxmlChannelConfigScreenToken);
            channelConfigScreenUserPass = new Scene(fxmlchannelConfigScreenUserPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // primaryStage.setScene(channelConfigScreenToken);
        primaryStage.setScene(channelConfigScreenUserPass);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}