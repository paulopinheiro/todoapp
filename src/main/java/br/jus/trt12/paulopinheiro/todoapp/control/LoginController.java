package br.jus.trt12.paulopinheiro.todoapp.control;

import br.jus.trt12.paulopinheiro.todoapp.database.DatabaseHandler;
import br.jus.trt12.paulopinheiro.todoapp.model.User;
import br.jus.trt12.paulopinheiro.todoapp.control.animations.Shaker;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    private DatabaseHandler databaseHandler;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler = new DatabaseHandler();

        loginButton.setOnAction(event -> {
            loginUser(loginUsername.getText(),loginPassword.getText());
        });

        loginSignupButton.setOnAction(event -> {
            // Take users to signup screen
            loginSignupButton.getScene().getWindow().hide();
            loadSignupScreen();
        });
    }

    private void loginUser(String username, String password) {
            if (username.isBlank() || password.isBlank()) {
                System.out.println("Enter both username and password");
                shake(this.loginUsername);
                shake(this.loginPassword);
                return;
            }

            User user = databaseHandler.getUserFromUsername(username);

            if (user==null) {
                System.out.println("There's no user " + username);
                shake(this.loginUsername);
                return;
            }

            if (!password.equals(user.getPassword())) {
                System.out.println("Wrong password");
                shake(this.loginPassword);
                return;
            }

            loadTaskCrudFormScreen(user);
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

    private void loadTaskCrudFormScreen(User user) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/taskCrudForm.fxml"));
        try {
            TaskCrudFormController taskCrudFormController = new TaskCrudFormController(user);
            loader.setController(taskCrudFormController);
            loader.load();

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//
//    private void loadTasksScreen(User user) {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/addItem.fxml"));
//        try {
//            loader.load();
//            AddItemController addItemController = loader.getController();
//            addItemController.setUser(user);
//            Parent root = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.showAndWait();
//        } catch (IOException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    private void shake(Node node) {
        Shaker shaker = new Shaker(node);
        shaker.shake();
    }
}
