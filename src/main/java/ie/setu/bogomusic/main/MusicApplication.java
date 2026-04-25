package ie.setu.bogomusic.main;

import atlantafx.base.theme.Dracula;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MusicApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MusicApplication.class.getResource("/music-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);

        // set the app icon
        stage.getIcons().add(new Image(Objects.requireNonNull(MusicApplication.class.getResource("/icon.png")).toExternalForm(), 64, 64, true, true));

        // Set the app theme
        Application.setUserAgentStylesheet(new Dracula().getUserAgentStylesheet());

        stage.setTitle("Bogo Music");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(_ -> exit());
    }

    public static void exit() {
        System.exit(0);
    }
}
