package online.uniqorn;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;

public class LoadingDialogue {

    Alert alert;

    public void showLoadingDialog() {
        alert = new Alert(Alert.AlertType.NONE,"", ButtonType.CLOSE);
        Image image = new Image("/spirits-loading.png");
        BackgroundFill fill = new BackgroundFill(new ImagePattern(image), null, null);
        alert.getDialogPane().setBackground(new Background(fill));
        alert.getDialogPane().setMaxWidth(image.getWidth());
        alert.getDialogPane().setMinHeight(image.getHeight());
        alert.show();
    }

    public void closeAlert(){
        alert.close();
    }
}
