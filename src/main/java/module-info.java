module ie.setu.bogomusic {
    requires javafx.controls;
    requires javafx.fxml;


    opens ie.setu.bogomusic.main to javafx.fxml;

    exports ie.setu.bogomusic.main;
    exports ie.setu.bogomusic.controller;
}