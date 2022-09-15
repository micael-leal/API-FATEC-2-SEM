package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.ConnectionFactory;
import model.RegisteredChannel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ConfigAtivaController implements Initializable {
    @FXML
    private Pagination pagination;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<RegisteredChannel, Integer> columnID;
    @FXML
    private TableColumn<RegisteredChannel, String> columnCHANNEL;
    @FXML
    private TableColumn<RegisteredChannel, String> columnACCOUNT;
    @FXML
    private TableColumn<RegisteredChannel, String> columnACTION;

    private final int rowsPerPage = 10;
    private int pages = 1;

    private ObservableList<RegisteredChannel> getRegisteredChannelData() {
        PreparedStatement stmt;
        ResultSet resultSet;
        Connection conn;
        conn = ConnectionFactory.getConnection();
        ObservableList registeredChannelList = FXCollections.observableArrayList();

        try {
            stmt = conn.prepareStatement("SELECT * FROM registeredChannelToken");
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                registeredChannelList.add(new RegisteredChannel(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("channel_id"),
                        resultSet.getString("channel_name"),
                        resultSet.getString("token"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
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

    }
}
