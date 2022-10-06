module APIFATEC2SEM {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires java.dotenv;
    requires java.mail;

    opens model;
    opens view;
    opens controller;
}