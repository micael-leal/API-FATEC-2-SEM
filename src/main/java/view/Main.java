package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private static Stage stage;
    private static Scene channelConfigScreenToken;
    private static Scene channelConfigScreenUserPass;
    private static Scene activeConfigScreen;
    private static Scene cadastroCanais;

    @Override
    public void start(Stage primaryStage) {
        // TODO: Código principal inserido aqui dentro
        stage = primaryStage;
        primaryStage.setResizable(false);
        try {
            AnchorPane fxmlChannelConfigScreenToken = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlConfigCanaisToken.fxml")));
            channelConfigScreenToken = new Scene(fxmlChannelConfigScreenToken);

            AnchorPane fxmlChannelConfigScreenUserPass = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlConfigCanaisUsuarioESenha.fxml")));
            channelConfigScreenUserPass = new Scene(fxmlChannelConfigScreenUserPass);

            AnchorPane fxmlActiveConfigScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlConfigAtiva.fxml")));
            activeConfigScreen = new Scene(fxmlActiveConfigScreen);

            AnchorPane fxmlChannelRegister = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmlCadastroCanais.fxml")));
            cadastroCanais = new Scene(fxmlChannelRegister);

        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.setScene(cadastroCanais);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}