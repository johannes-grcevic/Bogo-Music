package ie.setu.bogomusic.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MusicApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MusicApplication.class.getResource("/music-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);

        stage.setTitle("Bogo Music");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(_ -> exit());
    }

    public static void exit() {
        System.exit(0);
    }
}
