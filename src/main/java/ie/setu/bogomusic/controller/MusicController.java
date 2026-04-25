package ie.setu.bogomusic.controller;

import ie.setu.bogomusic.main.MusicApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MusicController implements Initializable {

    @FXML
    public void exitApplication() {
        MusicApplication.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
