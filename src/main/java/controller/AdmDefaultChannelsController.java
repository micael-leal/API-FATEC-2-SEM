package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Channel;
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

public class AdmDefaultChannelsController implements Initializable {
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
    private TableColumn<Channel, String> columnACTION;
    @FXML
    private Text userLABEL;

    @FXML
    private ComboBox<String> searchChannel;

    private String[] list_type_channel = {"Plataforma/ERP", "Marketplace", "Meio de Pagamento", "Sem filtro"};

    @FXML
    private ComboBox<String> searchAuth;

    private String[] list_type_authentication = {"TOKEN", "LOGIN", "Sem filtro"};

    @FXML
    private Label filterChannelErrorMessage;

    @FXML
    private Label filterAuthErrorMessage;

    enum filterType {
        general,
        byAuth
    }

    private final int rowsPerPage = 10;
    private int pages = 1;

    @FXML
    private void leaveButtonAction() throws IOException {
        User.getInstance().cleanUserSession();
        Main.changeScene("loginForm");
    }

    @FXML
    private void goToAdmDefaultChannelRegister() throws IOException {
        Main.changeScene("admDefaultChannelRegister");
    }

    private void updateTable(filterType type){
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnCHANNEL.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnTYPE.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnAUTH.setCellValueFactory(new PropertyValueFactory<>("authType"));
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
                        Channel selectedChannel = getTableView().getItems().get(getIndex());
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/fxmlAdmEditChannel.fxml"));

                        try {
                            loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        AdmEditChannelController admEditChannelController = loader.getController();
                        admEditChannelController.fillSelectedChannel(selectedChannel);
                        admEditChannelController.setTableView(tableView);

                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.initStyle(StageStyle.UTILITY);
                        stage.show();

                    });

                deleteButton.getStyleClass().add("actionButtons");
                deleteButton.setOnAction(event -> {
                    Channel ch = getTableView().getItems().get(getIndex());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("[DELETE] You have clicked:\n" + ch.getId() + " | " + ch.getName());
                    Optional<ButtonType> result = alert.showAndWait();

                    PreparedStatement stmt;
                    Connection conn = ConnectionFactory.getConnection();

                    if (result.orElse(null) == ButtonType.OK) {
                        try {
                            stmt = conn.prepareStatement("delete from registeredchannellogin where channel_id = (?)");
                            stmt.setInt(1, ch.getId());
                            stmt.execute();
                            stmt = conn.prepareStatement("delete from registeredchanneltoken where channel_id = (?)");
                            stmt.setInt(1, ch.getId());
                            stmt.execute();
                            stmt = conn.prepareStatement("delete from defaultChannels where channel_id = (?)");
                            stmt.setInt(1, ch.getId());
                            stmt.execute();
                            conn.close();
                            updateTable(type);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            stmt = conn.prepareStatement("delete from defaultChannels where channel_id = (?)");
                            stmt.setInt(1, ch.getId());
                            stmt.execute();
                            conn.close();
                            updateTable(type);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                HBox buttonsPane = new HBox(editButton, deleteButton);
                setGraphic(buttonsPane);
                }
            }
        });

        if(type == filterType.general) {
            if (tableView.getItems().isEmpty()) {
                pages = 1;
            } else if (getRegisteredChannelData().size() % rowsPerPage == 0) {
                pages = getRegisteredChannelData().size() / rowsPerPage;
            } else if (getRegisteredChannelData().size() > rowsPerPage) {
                pages = getRegisteredChannelData().size() / rowsPerPage + 1;
            }
            pagination.setPageCount(pages);
            pagination.setPageFactory(this::generatePages);
        } else if (type == filterType.byAuth) {
            if (tableView.getItems().isEmpty()) {
                pages = 1;
            } else if (getRegisteredChannelFilteredByAuth().size() % rowsPerPage == 0) {
                pages = getRegisteredChannelFilteredByAuth().size() / rowsPerPage;
            } else if (getRegisteredChannelFilteredByAuth().size() > rowsPerPage) {
                pages = getRegisteredChannelFilteredByAuth().size() / rowsPerPage + 1;
            }
            pagination.setPageCount(pages);
            pagination.setPageFactory(this::generateFilteredPages);
        }
    }
    @FXML
    public void search(){
//        int fromIndex = pages * rowsPerPage;
//        int toIndex = Math.min(fromIndex + rowsPerPage, getRegisteredChannelData().size());
//        tableView.setItems(FXCollections.observableArrayList(getRegisteredChannelData().subList(fromIndex, toIndex)));

//        System.out.println("search");
//        System.out.println(fromIndex);
//        System.out.println(toIndex);
//        System.out.println("");

//        userLABEL.setText("Olá, admin");
//        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
//        columnCHANNEL.setCellValueFactory(new PropertyValueFactory<>("name"));
//        columnTYPE.setCellValueFactory(new PropertyValueFactory<>("type"));
//        columnAUTH.setCellValueFactory(new PropertyValueFactory<>("authType"));

        //System.out.println(getRegisteredChannelFilteredByAuth().size());
//        if (getRegisteredChannelFilteredByAuth().size() % rowsPerPage == 0) {
//            pages = getRegisteredChannelFilteredByAuth().size() / rowsPerPage;
//        } else if (getRegisteredChannelFilteredByAuth().size() % rowsPerPage > 0) {
//            pages = getRegisteredChannelFilteredByAuth().size() / rowsPerPage + 1;
//        }
//
//        pagination.setPageCount(pages);
//        pagination.setCurrentPageIndex(0);
//        pagination.setPageFactory(this::generateFilteredPages);
        updateTable(filterType.byAuth);
    }

    private ObservableList<Channel> getRegisteredChannelFilteredByAuth() {
        PreparedStatement stmt;
        ResultSet resultSet;
        Connection conn;
        conn = ConnectionFactory.getConnection();
        ObservableList<Channel> registeredChannelList = FXCollections.observableArrayList();

        try {
            if ((searchAuth.getValue() != null && searchChannel.getValue() == null) || (searchAuth.getValue() != "Sem filtro" && searchChannel.getValue() == "Sem filtro")) {
                stmt = conn.prepareStatement("SELECT * FROM defaultChannels WHERE auth = " + '"' + searchAuth.getValue().toUpperCase() + '"');
            } else if ((searchAuth.getValue() == null && searchChannel.getValue() != null) || (searchAuth.getValue() == "Sem filtro" && searchChannel.getValue() != "Sem filtro")) {
                stmt = conn.prepareStatement("SELECT * FROM defaultChannels WHERE type = " + '"' + searchChannel.getValue() + '"');
            } else if ((searchAuth.getValue() != "Sem filtro" && searchChannel.getValue() != "Sem filtro")) {
                stmt = conn.prepareStatement("SELECT * FROM defaultChannels WHERE auth = " + '"' + searchAuth.getValue().toUpperCase() + '"' + "AND type = " + '"' + searchChannel.getValue() + '"');
            } else {
                stmt = conn.prepareStatement("SELECT * FROM defaultChannels");
            }
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

    public void goToUserProfile(ActionEvent actionEvent) throws IOException {
        Main.changeScene("userActiveConfig");
    }

    @FXML
    private void goToCreateAdm(ActionEvent event) throws IOException {
        Main.changeScene("admCreateAccount");
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

//        System.out.println("generatePages");
//        System.out.println(fromIndex);
//        System.out.println(toIndex);
//        System.out.println("");

        tableView.setItems(FXCollections.observableArrayList(getRegisteredChannelData().subList(fromIndex, toIndex)));
        return new BorderPane(tableView);
    }

    private Node generateFilteredPages(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, getRegisteredChannelFilteredByAuth().size());

//        System.out.println("generateFilteredPages");
//        System.out.println(fromIndex);
//        System.out.println(toIndex);
//        System.out.println("");

        tableView.setItems(FXCollections.observableArrayList(getRegisteredChannelFilteredByAuth().subList(fromIndex, toIndex)));
        return new BorderPane(tableView);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userLABEL.setText("Olá, admin");
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnCHANNEL.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnTYPE.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnAUTH.setCellValueFactory(new PropertyValueFactory<>("authType"));
        searchChannel.getItems().addAll(list_type_channel);
        searchAuth.getItems().addAll(list_type_authentication);

        if (getRegisteredChannelData().size() % rowsPerPage == 0) {
            pages = getRegisteredChannelData().size() / rowsPerPage;
        } else if (getRegisteredChannelData().size() > rowsPerPage) {
            pages = getRegisteredChannelData().size() / rowsPerPage + 1;
        }

        pagination.setPageCount(pages);
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory(this::generatePages);

        updateTable(filterType.general);
    }
}
