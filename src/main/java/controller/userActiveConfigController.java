package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ConnectionFactory;
import model.RegisteredChannel;
import model.User;
import view.Main;

import java.io.IOException;
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
    private TableColumn<RegisteredChannel, String> columnTYPE;
    @FXML
    private TableColumn<RegisteredChannel, String> columnACTION;
    @FXML
    private Text userLABEL;
    @FXML
    private TextField searchField;
    @FXML
    private Button admProfileButton;

    ObservableList<RegisteredChannel> registeredChannelObservableList = FXCollections.observableArrayList();
    FilteredList<RegisteredChannel> registeredChannelFilteredList;

    private final int rowsPerPage = 10;
    private int pages = 1;

    @FXML
    private void leaveButtonAction() throws IOException {
        User.getInstance().cleanUserSession();
        Main.changeScene("loginForm");
    }

    @FXML
    private void goToFAQ() throws IOException {
        Main.changeScene("FAQ");
    }

    @FXML
    private void goToUserChannelConfig() throws IOException {
        Main.changeScene("userChannelConfig");
    }
    @FXML
    private void goToProfileChannels() throws IOException {
        Main.changeScene("userProfile");
    }

    @FXML
    public void goToAdmProfile(ActionEvent actionEvent) throws IOException {
        Main.changeScene("admDefaultChannel");
    }

    private void getRegisteredChannelData() {
        PreparedStatement stmt;
        ResultSet resultSet;
        Connection conn;
        conn = ConnectionFactory.getConnection();
        ObservableList<RegisteredChannel> registeredChannelList = FXCollections.observableArrayList();

        try {
            int usuarioLogado = User.getInstance().getId();
            stmt = conn.prepareStatement("SELECT registeredChannelToken.channel_id, registeredChannelToken.user_id, registeredChannelToken.registeredChannelToken_id, defaultChannels.name, 'TOKEN' as `tipo` FROM registeredChannelToken INNER JOIN defaultChannels ON registeredChannelToken.channel_id=defaultChannels.channel_id AND registeredChannelToken.user_id=(?) UNION SELECT registeredChannelLogin.channel_id, registeredChannelLogin.user_id, registeredChannelLogin.registeredChannelLogin_id, defaultChannels.name, 'LOGIN' as `tipo` FROM registeredChannelLogin INNER JOIN defaultChannels ON registeredChannelLogin.channel_id=defaultChannels.channel_id AND registeredChannelLogin.user_id=(?)");
            stmt.setInt(1, usuarioLogado);
            stmt.setInt(2, usuarioLogado);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                registeredChannelList.add(new RegisteredChannel(
                        resultSet.getInt("registeredChannelToken_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("channel_id"),
                        resultSet.getString("name"),
                        resultSet.getString("tipo"),
                        "null",
                        "null",
                        "null"
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.registeredChannelObservableList = registeredChannelList;
        // return registeredChannelList;
    }

    private Node generatePages(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        if (this.registeredChannelFilteredList != null) {
            int toIndex = Math.min(fromIndex + rowsPerPage, registeredChannelFilteredList.size());
            tableView.setItems(FXCollections.observableArrayList(registeredChannelFilteredList.subList(fromIndex, toIndex)));
        } else {
            int toIndex = Math.min(fromIndex + rowsPerPage, registeredChannelObservableList.size());
            tableView.setItems(FXCollections.observableArrayList(registeredChannelObservableList.subList(fromIndex, toIndex)));
        }
        return new BorderPane(tableView);
    }

    private void updateTable() {
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnCHANNEL.setCellValueFactory(new PropertyValueFactory<>("channel_name"));
        columnTYPE.setCellValueFactory(new PropertyValueFactory<>("channel_type"));
        columnACTION.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    editButton.getStyleClass().add("actionButtons");
                    editButton.setOnAction(event -> {
                        RegisteredChannel rc = getTableView().getItems().get(getIndex());
                        if (rc.getChannel_type().equals("TOKEN")) {

                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/fxmlUserEditChannelToken.fxml"));
                            try {
                                loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            userEditChannelToken userToken = loader.getController();
                            userToken.setTextField(rc.getId());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        }else{
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/fxmlUserEditChannelLogin.fxml"));
                            try {
                                loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            userEditChannelLogin userLogin = loader.getController();
                            userLogin.setTextField(rc.getId());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                        }
                    });

                    deleteButton.getStyleClass().add("actionButtons");
                    deleteButton.setOnAction(event -> {
                        RegisteredChannel rc = getTableView().getItems().get(getIndex());
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("[DELETE] You have clicked:\n" + rc.getId() + " | " + rc.getChannel_name());
                        Optional<ButtonType> result = alert.showAndWait();

                        PreparedStatement stmt;
                        Connection conn = ConnectionFactory.getConnection();

                        if (result.orElse(null) == ButtonType.OK) {
                            if (rc.getChannel_type().equals("TOKEN")) {
                                try {
                                    stmt = conn.prepareStatement("delete from registeredChannelToken where registeredChannelToken_id = (?)");
                                    stmt.setInt(1, rc.getId());
                                    stmt.execute();
                                    conn.close();
                                    updateTable();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                try {
                                    stmt = conn.prepareStatement("delete from registeredChannelLogin where registeredChannelLogin_id = (?)");
                                    stmt.setInt(1, rc.getId());
                                    stmt.execute();
                                    conn.close();
                                    updateTable();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    });
                    HBox buttonsPane = new HBox(editButton, deleteButton);
                    setGraphic(buttonsPane);
                }
            }
        });

        if (tableView.getItems().isEmpty()) {
            pages = 1;
        } else if (registeredChannelObservableList.size() % rowsPerPage == 0) {
            pages = registeredChannelObservableList.size() / rowsPerPage;
        } else if (registeredChannelObservableList.size() > rowsPerPage) {
            pages = registeredChannelObservableList.size() / rowsPerPage + 1;
        }
        pagination.setPageCount(pages);
        pagination.setPageFactory(this::generatePages);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (!(User.getInstance().getEmail().equals("admin"))) {
            admProfileButton.setVisible(false);
        }

        getRegisteredChannelData();
        userLABEL.setText("Olá, " + User.getInstance().getName());

        FilteredList<RegisteredChannel> filteredData = new FilteredList<>(this.registeredChannelObservableList, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(registeredChannel -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if (registeredChannel.getChannel_name().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Integer.toString(registeredChannel.getId()).indexOf(searchKeyword) > -1) {
                    return true;
                } else if (registeredChannel.getChannel_type().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else {
                    return false;
                }
            });
            this.registeredChannelFilteredList = filteredData;
            updateTable();
        });

        SortedList<RegisteredChannel> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);

        updateTable();
    }
}