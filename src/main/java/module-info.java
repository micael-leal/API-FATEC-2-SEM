module APIFATEC2SEM {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;

    opens model;
    opens view;
    opens controller;
}