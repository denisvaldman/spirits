package online.uniqorn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import online.uniqorn.services.UserService;

import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws BackingStoreException {
        Preferences.userRoot().node("/spirits").removeNode();
        UserService userService = new UserService();
        String sceneToLoad = "login";
        if(userService.checkUser()){
            sceneToLoad = "primary";
        }
        this.stage = stage;
        try {
            scene = new Scene(loadFXML(sceneToLoad), 228, 320);
        } catch (IOException e) {
            return;
        }
        scene.getStylesheets().add(App.class.getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        stage.setWidth(640);
        stage.setHeight(480);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}