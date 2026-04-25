module ie.setu.bogomusic {
    requires javafx.controls;
    requires javafx.fxml;


    opens ie.setu.bogomusic to javafx.fxml;

    exports ie.setu.bogomusic.controller;
}