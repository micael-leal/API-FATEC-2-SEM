module APIFATEC2SEM {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires java.dotenv;

    opens model;
    opens view;
    opens controller;
}