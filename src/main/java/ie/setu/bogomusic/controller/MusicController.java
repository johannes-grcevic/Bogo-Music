package ie.setu.bogomusic.controller;

import ie.setu.bogomusic.main.MusicApplication;

import atlantafx.base.controls.CustomTextField;
import javafx.scene.layout.BorderPane;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MusicController implements Initializable {
    @FXML
    private BorderPane borderPane;

    @FXML
    private CustomTextField searchBar;

    @FXML
    public void exitApplication() {
        MusicApplication.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchBar.setLeft(new FontIcon(Feather.SEARCH));
        searchBar.setFocusTraversable(false);
    }
}
