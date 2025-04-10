package br.jus.trt12.paulopinheiro.todoapp.control;

import br.jus.trt12.paulopinheiro.todoapp.database.DatabaseHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignupController implements Initializable {
    @FXML
    private Button signUpButton;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpLocation;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private TextField signUpUsername;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        signUpButton.setOnAction(event-> {
            databaseHandler.signUpUser(signUpFirstName.getText(),
                                       signUpLastName.getText(),
                                       signUpUsername.getText(),
                                       signUpPassword.getText(),
                                       signUpLocation.getText(),
                                       "Male");
        });
    }    
    
}
