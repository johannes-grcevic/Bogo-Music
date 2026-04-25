module ie.setu.bogomusic {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.feather;

    opens ie.setu.bogomusic.main to javafx.fxml;
    opens ie.setu.bogomusic.controller to javafx.fxml;

    exports ie.setu.bogomusic.main;
    exports ie.setu.bogomusic.controller;
}