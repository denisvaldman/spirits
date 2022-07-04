package online.uniqorn;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import online.uniqorn.services.UserService;
import online.uniqorn.utils.ConnectionStatus;
import org.apache.http.auth.AuthenticationException;

import javax.swing.text.html.ImageView;

public class LoginController {

    @FXML
    Label errorLabel;
    @FXML
    TextField userNameField,passwordField;

    @FXML
    ImageView loading;

    public LoginController() {
//        loading.setVisible(boolean)
    }

    @FXML
    private void login() {
        errorLabel.setText("");
        if(!ConnectionStatus.netIsAvailable()){
            errorLabel.setText("Žiadne pripojenie na internet");
            return;
        }
        String username = userNameField.getText();
        String password = passwordField.getText();
        UserService userService = new UserService();
        try {
            userService.logIn(username,password);
        } catch (AuthenticationException e) {
            errorLabel.setText("Nesprávne údaje");
            return;
        } catch (ExecutionException e) {
            errorLabel.setText("Nesprávne údaje");
            return;
        } catch (InterruptedException e) {
            errorLabel.setText("Systémová chyba");
            return;
        }
        try {
            App.setRoot("primary");
        } catch (IOException e) {
           errorLabel.setText("Systémová chyba");
           return;
        }
    }
}