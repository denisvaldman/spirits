package online.uniqorn;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import online.uniqorn.services.UserService;
import online.uniqorn.utils.ConnectionStatus;
import org.apache.http.auth.AuthenticationException;

public class LoginController {

    @FXML
    Label errorLabel;
    @FXML
    TextField userNameField,passwordField;
    @FXML
    HBox loadingBox;

    @FXML
    private void login() {
        String errorMessage = "";
        LoadingDialogue loadingDialogue = new LoadingDialogue();
        errorLabel.setText("");
        Task<Integer> task = new Task<Integer>() {
            @Override public Integer call() {
                if(!ConnectionStatus.netIsAvailable()){
                    return 1;
                }
                String username = userNameField.getText();
                String password = passwordField.getText();
                UserService userService = new UserService();
                try {
                    userService.logIn(username,password);
                } catch (AuthenticationException e) {
                    return 2;
                } catch (ExecutionException e) {
                    return 2;
                } catch (InterruptedException e) {
                    return 3;
                }
                return 0;
            }
        };
        task.setOnRunning((e) -> loadingDialogue.showLoadingDialog());
        task.setOnSucceeded((e) -> {
            loadingDialogue.closeAlert();
            Integer returnValue = 0;
            try {
                returnValue = task.get();
            } catch (InterruptedException ex) {
                returnValue = 3;
            } catch (ExecutionException ex) {
                returnValue = 3;
            }
            if(returnValue == 0){
                try {
                    App.setRoot("primary");
                } catch (IOException ex) {
                    errorLabel.setText("Systémová chyba");
                    return;
                }
            }
            if (returnValue == 1){
                errorLabel.setText("Žiadne pripojenie na internet");
                return;
            }
            if (returnValue == 2){
                errorLabel.setText("Nesprávne údaje");
                return;
            }
            if (returnValue == 3){
                errorLabel.setText("Systémová chyba");
                return;
            }
        });
        task.setOnFailed((e) -> {
            errorLabel.setText(errorMessage);
        });
        new Thread(task).start();
    }
}