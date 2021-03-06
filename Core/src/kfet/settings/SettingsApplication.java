package kfet.settings;

import com.kfet.GeneratorApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


/**
 * The Settings application.
 */
public class SettingsApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/Settings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Settings");
        stage.getIcons().add(new Image(Objects.requireNonNull(GeneratorApplication.class.getResourceAsStream("/icon.png"))));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch();
    }


}