package ie.setu.bogomusic.controller;

import atlantafx.base.controls.Tile;
import ie.setu.bogomusic.main.MusicApplication;

import atlantafx.base.controls.CustomTextField;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private Button backButton, forwardButton, newMusicBackButton, newMusicForwardButton, playlistBackButton, playlistForwardButton, albumBackButton, albumForwardButton;
    @FXML
    private Button profileSettingsButton;

    @FXML
    private HBox playlistImageContainer, albumImageContainer;
    @FXML
    private VBox newMusicContainer;

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

    private void setImageClip(Node node, double width, double height) {
        Rectangle clip = new Rectangle(width, height);
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        node.setClip(clip);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        borderPane.setOnMouseClicked(this::onBorderPaneMouseClicked);

        searchBar.setLeft(new FontIcon(Feather.SEARCH));
        searchBar.setFocusTraversable(false);

        // player tile
        ImageView playingSongImageView = new ImageView(new Image(Objects.requireNonNull(
                        getClass().getResourceAsStream("/images/albums/cover_angles_the_strokes.jpg")), 32, 32, true, true));

        Rectangle playerIconClip = new Rectangle(playingSongImageView.getImage().getWidth(), playingSongImageView.getImage().getHeight());
        setImageClip(playingSongImageView, playerIconClip.getWidth(), playerIconClip.getHeight());
        playerTile.setGraphic(playingSongImageView);

        // profile settings button
        ImageView profileImageView = new ImageView(new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/images/profile/profile_icon.png")),
                42, 42, true, true));

        profileSettingsButton.setGraphic(profileImageView);

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
        importMusicButton.setGraphic(new FontIcon(Feather.FOLDER_PLUS));

        musicTwinButton.setGraphic(new FontIcon(Feather.DISC));
        forYouButton.setGraphic(new FontIcon(Feather.HEART));
        exploreButton.setGraphic(new FontIcon(Feather.EYE));
        communityRoomButton.setGraphic(new FontIcon(Feather.MESSAGE_SQUARE));

        // main center buttons
        backButton.setGraphic(new FontIcon(Feather.CHEVRON_LEFT));
        forwardButton.setGraphic(new FontIcon(Feather.CHEVRON_RIGHT));
        newMusicBackButton.setGraphic(new FontIcon(Feather.CHEVRON_LEFT));
        newMusicForwardButton.setGraphic(new FontIcon(Feather.CHEVRON_RIGHT));
        playlistBackButton.setGraphic(new FontIcon(Feather.CHEVRON_LEFT));
        playlistForwardButton.setGraphic(new FontIcon(Feather.CHEVRON_RIGHT));
        albumBackButton.setGraphic(new FontIcon(Feather.CHEVRON_LEFT));
        albumForwardButton.setGraphic(new FontIcon(Feather.CHEVRON_RIGHT));

        // playlist images
        for (Node child : playlistImageContainer.getChildren()) {
            if (child instanceof ImageView imageView) {
                Rectangle playlistImageClip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
                setImageClip(imageView, playlistImageClip.getWidth(), playlistImageClip.getHeight());
            }
        }

        // album images
        for (Node child : albumImageContainer.getChildren()) {
            if (child instanceof ImageView imageView) {
                Rectangle albumImageClip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
                setImageClip(imageView, albumImageClip.getWidth(), albumImageClip.getHeight());
            }
        }

        ObservableList<Node> children = newMusicContainer.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);
            if (!(child instanceof Tile tile)) continue;

            // set the first tile as playing, otherwise not playing
            if (i == 0) {
                tile.setGraphic(new FontIcon(Feather.PAUSE));
            }
            else {
                tile.setGraphic(new FontIcon(Feather.PLAY));
            }
        }
    }
}
