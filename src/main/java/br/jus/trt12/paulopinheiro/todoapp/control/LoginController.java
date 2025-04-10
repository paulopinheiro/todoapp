package br.jus.trt12.paulopinheiro.todoapp.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        
    }    
    
}
