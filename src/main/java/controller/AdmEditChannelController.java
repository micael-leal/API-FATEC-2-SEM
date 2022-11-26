package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Channel;
import model.ConnectionFactory;
import model.User;
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdmEditChannelController implements Initializable {
    private int selectedID;
    @FXML
    private Text userLABEL;

    @FXML
    private Text channelInputField;
    @FXML
    private TextField selectedChannel;
    @FXML
    private ComboBox<String> typeChannel;

    private String[] list_type_channel = {"Plataforma/ERP", "Marketplace", "Meio de Pagamento"};
    @FXML
    private ComboBox<String> typeAuthentication;

    private String[] list_type_authentication = {"TOKEN", "LOGIN"};
    @FXML
    private Button buttomEditChannel;

    @FXML
    private Button saveButton;

    private TableView<Channel> tableView;

    @FXML
    private void leaveButtonAction() throws IOException {
        User.getInstance().cleanUserSession();
        Main.changeScene("loginForm");
    }

    @FXML
    private void goToAdmActiveChannels(ActionEvent event) throws IOException {
        Main.changeScene("admDefaultChannel");
    }

    @FXML
    private void goToAdmDefaultChannelRegister() throws IOException {
        Main.changeScene("admDefaultChannelRegister");
    }

    @FXML
    private void goToCreateAdm(ActionEvent event) throws IOException {
        Main.changeScene("admCreateAccount");
    }

    @FXML
    public void goToUserProfile(ActionEvent actionEvent) throws IOException {
        Main.changeScene("userActiveConfig");
    }
    @FXML
    private void goToAdmDefaultChannels() throws IOException {
        Stage stage = (Stage) buttomEditChannel.getScene().getWindow();
        stage.close();
        Main.changeScene("AdmDefaultChannel");
    }

    public void fillSelectedChannel(Channel channel) {
        this.selectedChannel.setText(channel.getName());
        this.typeChannel.setValue(channel.getType());
        this.typeAuthentication.setValue(channel.getAuthType());
        selectedID = channel.getId();
    }

    public void setTableView(TableView<Channel> tableView) {
        this.tableView = tableView;
    }

    @FXML
    public void onSaveButtonClicked(){
        saveUpdate();
    }

    public void saveUpdate() {
        String newChannelName, newTypeChannel, newTypeAuthentication;
        newChannelName = selectedChannel.getText();
        newTypeChannel = typeChannel.getValue();
        newTypeAuthentication = typeAuthentication.getValue();

        PreparedStatement stmt;
        Connection conn;
        conn = ConnectionFactory.getConnection();
        try {
            stmt = conn.prepareStatement("update defaultChannels set name = (?), type = (?), auth = (?) where channel_id = (?)");
            stmt.setString(1, newChannelName);
            stmt.setString(2, newTypeChannel);
            stmt.setString(3, newTypeAuthentication);
            stmt.setString(4, String.valueOf(selectedID));
            stmt.execute();
            stmt.close();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Edição Feita");
            alert.show();

            goToAdmDefaultChannels();

//            if (tableView.getItems().isEmpty()) {
//                pages = 1;
//            } else if(getRegisteredChannelData().size() % rowsPerPage == 0) {
//                pages = getRegisteredChannelData().size() / rowsPerPage;
//            } else if (getRegisteredChannelData().size() > rowsPerPage) {
//                pages = getRegisteredChannelData().size() / rowsPerPage + 1;
//            }
//
//            int fromIndex = pages * rowsPerPage;
//            int toIndex = Math.min(fromIndex + rowsPerPage, getRegisteredChannelData().size());
            tableView.setItems(FXCollections.observableArrayList(getRegisteredChannelData()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<Channel> getRegisteredChannelData() {
        PreparedStatement stmt;
        ResultSet resultSet;
        Connection conn;
        conn = ConnectionFactory.getConnection();
        ObservableList<Channel> registeredChannelList = FXCollections.observableArrayList();

        try {
            stmt = conn.prepareStatement("SELECT * FROM defaultChannels");
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                registeredChannelList.add(new Channel(
                        resultSet.getInt("channel_id"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getString("auth")
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registeredChannelList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userLABEL.setText("Olá, admin");
        typeChannel.getItems().addAll(list_type_channel);
        typeAuthentication.getItems().addAll(list_type_authentication);
    }
}
