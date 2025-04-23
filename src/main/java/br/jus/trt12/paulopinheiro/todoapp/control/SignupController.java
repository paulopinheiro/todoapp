package br.jus.trt12.paulopinheiro.todoapp.control;

import br.jus.trt12.paulopinheiro.todoapp.database.DatabaseHandler;
import br.jus.trt12.paulopinheiro.todoapp.model.User;
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

    private DatabaseHandler databaseHandler;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler = new DatabaseHandler();

        signUpButton.setOnAction(event-> {
            databaseHandler.signUpUser(getUser());
        });
    }

    private User getUser() {
        User user = new User();
        user.setFirstName(signUpFirstName.getText());
        user.setLastName(signUpLastName.getText());
        user.setUserName(signUpUsername.getText());
        user.setPassword(signUpPassword.getText());
        user.setLocation(signUpLocation.getText());
        user.setGender(getGender());

        return user;
    }

    private String getGender() {
        String gender = "Undefined";

        if (signUpCheckBoxMale.isSelected()) {
            if (!this.signUpCheckBoxFemale.isSelected()) {
                gender = "Male";
            }
        } else {
            if (signUpCheckBoxFemale.isSelected()) {
                gender = "Female";
            }
        }
        return gender;
    }
}
