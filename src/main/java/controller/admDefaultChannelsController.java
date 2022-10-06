package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import model.Channel;
import model.ConnectionFactory;
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class admDefaultChannelsController implements Initializable {
    @FXML
    private Pagination pagination;
    @FXML
    private TableView<Channel> tableView;
    @FXML
    private TableColumn<Channel, Integer> columnID;
    @FXML
    private TableColumn<Channel, String> columnCHANNEL;
    @FXML
    private TableColumn<Channel, String> columnTYPE;
    @FXML
    private TableColumn<Channel, String> columnAUTH;
    @FXML
    private Text userLABEL;
    private final int rowsPerPage = 10;
    private int pages = 1;

    @FXML
    private void leaveButtonAction() throws IOException {
        Main.changeScene("loginForm");
    }

    @FXML
    private void goToAdmDefaultChannelRegister() throws IOException {
        Main.changeScene("admDefaultChannelRegister");
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

    private Node generatePages(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, getRegisteredChannelData().size());
        tableView.setItems(FXCollections.observableArrayList(getRegisteredChannelData().subList(fromIndex, toIndex)));
        return new BorderPane(tableView);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userLABEL.setText("Olá, admin");
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnCHANNEL.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnTYPE.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnAUTH.setCellValueFactory(new PropertyValueFactory<>("authType"));

        if (getRegisteredChannelData().size() % rowsPerPage == 0) {
            pages = getRegisteredChannelData().size() / rowsPerPage;
        } else if (getRegisteredChannelData().size() > rowsPerPage) {
            pages = getRegisteredChannelData().size() / rowsPerPage + 1;
        }
        pagination.setPageCount(pages);
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory(this::generatePages);
    }
}
