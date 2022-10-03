package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.ConnectionFactory;
import model.RegisteredChannel;
import view.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class userActiveConfigController implements Initializable {
    @FXML
    private Pagination pagination;
    @FXML
    private TableView<RegisteredChannel> tableView;
    @FXML
    private TableColumn<RegisteredChannel, Integer> columnID;
    @FXML
    private TableColumn<RegisteredChannel, String> columnCHANNEL;
    @FXML
    private TableColumn<RegisteredChannel, String> columnACTION;

    private final int rowsPerPage = 10;
    private int pages = 1;

    @FXML
    private void goToUserChannelConfig() {
        Main.changeScene("userChannelConfig");
    }

    private ObservableList<RegisteredChannel> getRegisteredChannelData() {
        PreparedStatement stmt;
        ResultSet resultSet;
        Connection conn;
        conn = ConnectionFactory.getConnection();
        ObservableList<RegisteredChannel> registeredChannelList = FXCollections.observableArrayList();

        try {
            stmt = conn.prepareStatement("SELECT registeredChannelToken.*, defaultChannels.name FROM registeredChannelToken INNER JOIN defaultChannels ON registeredChannelToken.channel_id=defaultChannels.channel_id AND registeredChannelToken.user_id=1");
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                registeredChannelList.add(new RegisteredChannel(
                        resultSet.getInt("registeredChannelToken_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("channel_id"),
                        resultSet.getString("name"),
                        resultSet.getString("token"),
                        "null",
                        "null"
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

    private void updateTable() {
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnCHANNEL.setCellValueFactory(new PropertyValueFactory<>("channel_name"));
        columnACTION.setCellFactory(param -> new TableCell<>() {
//            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
//                    editButton.setOnAction(event -> {
//                        RegisteredChannel rc = getTableView().getItems().get(getIndex());
//                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                        alert.setContentText("[EDIT] You have clicked:\n" + rc.getId() + " | " + rc.getChannel_name());
//                        alert.show();
//
//                    });
                    deleteButton.setOnAction(event -> {
                        RegisteredChannel rc = getTableView().getItems().get(getIndex());
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("[DELETE] You have clicked:\n" + rc.getId() + " | " + rc.getChannel_name());
                        Optional<ButtonType> result = alert.showAndWait();

                        if (result.orElse(null) == ButtonType.OK) {
                            PreparedStatement stmt;
                            Connection conn;
                            conn = ConnectionFactory.getConnection();
                            try {
                                stmt = conn.prepareStatement("delete from registeredChannelToken where registeredChannelToken_id = (?)");
                                stmt.setInt(1, rc.getId());
                                stmt.execute();
                                conn.close();
                                updateTable();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    //HBox buttonsPane = new HBox(editButton, deleteButton);
                    HBox buttonsPane = new HBox(deleteButton);
                    setGraphic(buttonsPane);
                }
            }
        });

        if (getRegisteredChannelData().size() % rowsPerPage == 0) {
            pages = getRegisteredChannelData().size() / rowsPerPage;
        } else if (getRegisteredChannelData().size() > rowsPerPage) {
            pages = getRegisteredChannelData().size() / rowsPerPage + 1;
        }
        pagination.setPageCount(pages);
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory(this::generatePages);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
    }
}
