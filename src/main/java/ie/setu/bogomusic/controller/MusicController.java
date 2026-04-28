package ie.setu.bogomusic.controller;

import atlantafx.base.controls.Tile;
import ie.setu.bogomusic.main.MusicApplication;

import atlantafx.base.controls.CustomTextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MusicController implements Initializable {
    @FXML
    private BorderPane borderPane;
    @FXML
    private CustomTextField searchBar;
    @FXML
    private Tile playerTile;
    @FXML
    private Button musicTwinButton, aiRadioButton, forYouButton, exploreButton, importMusicButton;
    @FXML
    private Button playlistButton, artistButton, albumButton, songButton, profileButton, communityRoomButton;
    @FXML
    private Button seekBackButton, seekForwardButton, playPauseButton, aiShuffleButton, shuffleButton, volumeButton;
    @FXML
    private Slider playbackSlider;
    @FXML
    private Button backButton, forwardButton, albumBackButton, albumForwardButton;

    private final ImageView playingSongImageView = new ImageView();

    @FXML
    public void exitApplication() {
        MusicApplication.exit();
    }

    private void onBorderPaneMouseClicked(MouseEvent event) {
        // deselect the search bar
        if (searchBar.isFocused()) {
            searchBar.clear();
            borderPane.requestFocus();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        borderPane.setOnMouseClicked(this::onBorderPaneMouseClicked);

        searchBar.setLeft(new FontIcon(Feather.SEARCH));
        searchBar.setFocusTraversable(false);

        playingSongImageView.setImage(new Image(
                Objects.requireNonNull(
                        getClass().getResource("/images/the-strokes.jpg")).toExternalForm(), 32, 32, true, true));

        Rectangle clip = new Rectangle(playingSongImageView.getImage().getWidth(), playingSongImageView.getImage().getHeight());
        clip.setArcWidth(60);
        clip.setArcHeight(60);
        playingSongImageView.setClip(clip);
        playerTile.setGraphic(playingSongImageView);

        // player buttons
        seekBackButton.setGraphic(new FontIcon(Feather.SKIP_BACK));
        seekForwardButton.setGraphic(new FontIcon(Feather.SKIP_FORWARD));
        playPauseButton.setGraphic(new FontIcon(Feather.PAUSE));
        shuffleButton.setGraphic(new FontIcon(Feather.REPEAT));
        aiShuffleButton.setGraphic(new FontIcon(Feather.SHUFFLE));
        volumeButton.setGraphic(new FontIcon(Feather.VOLUME_2));

        // sidebar buttons
        playlistButton.setGraphic(new FontIcon(Feather.PLAY));
        aiRadioButton.setGraphic(new FontIcon(Feather.RADIO));
        artistButton.setGraphic(new FontIcon(Feather.USERS));
        albumButton.setGraphic(new FontIcon(Feather.FOLDER));
        songButton.setGraphic(new FontIcon(Feather.MUSIC));
        profileButton.setGraphic(new FontIcon(Feather.USER));
        communityRoomButton.setGraphic(new FontIcon(Feather.MESSAGE_SQUARE));
        musicTwinButton.setGraphic(new FontIcon(Feather.DISC));
        forYouButton.setGraphic(new FontIcon(Feather.HEART));
        exploreButton.setGraphic(new FontIcon(Feather.EYE));
        importMusicButton.setGraphic(new FontIcon(Feather.FOLDER_PLUS));

        backButton.setGraphic(new FontIcon(Feather.CHEVRON_LEFT));
        forwardButton.setGraphic(new FontIcon(Feather.CHEVRON_RIGHT));
        albumBackButton.setGraphic(new FontIcon(Feather.CHEVRON_LEFT));
        albumForwardButton.setGraphic(new FontIcon(Feather.CHEVRON_RIGHT));
    }
}
