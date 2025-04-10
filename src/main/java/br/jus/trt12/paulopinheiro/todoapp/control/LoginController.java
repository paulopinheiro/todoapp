package br.jus.trt12.paulopinheiro.todoapp.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginSignupButton;

    @FXML
    private TextField loginUsername;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginButton.setOnAction(event -> {
            String loginText = loginUsername.getText().trim();
            String passwordText = loginPassword.getText().trim();
            if (!loginText.equals("") && !passwordText.equals("")) {
                loginUser(loginText,passwordText);
            } else {
                System.out.println("Login error");
            }
        });

        loginSignupButton.setOnAction(event -> {
            // Take users to signup screen
            loginSignupButton.getScene().getWindow().hide();
            loadSignupScreen();
        });
    }

    private void loginUser(String loginText, String passwordText) {
        // If user exists take it to the addItem screen
        
    }

    private void loadSignupScreen() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/signup.fxml"));
        try {
            loader.load();
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
