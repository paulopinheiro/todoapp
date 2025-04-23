package br.jus.trt12.paulopinheiro.todoapp.control;

import br.jus.trt12.paulopinheiro.todoapp.control.animations.Shaker;
import br.jus.trt12.paulopinheiro.todoapp.database.DatabaseHandler;
import br.jus.trt12.paulopinheiro.todoapp.model.Task;
import br.jus.trt12.paulopinheiro.todoapp.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AddItemFormController implements Initializable {
    private DatabaseHandler databaseHandler;
    private User user;

    @FXML
    private AnchorPane formRootPane;
    @FXML
    private TextField descriptionField;
    @FXML
    private Button saveTaskButton;
    @FXML
    private TextField taskField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.databaseHandler = new DatabaseHandler();

        saveTaskButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            insertTask(this.taskField.getText(),this.descriptionField.getText());
        });
    }

    private void insertTask(String taskText, String description) {
        if (taskText.isBlank()) {
            System.out.println("Enter a tittle for the task");
            shake(this.taskField);
            return;
        }

        if (description.isBlank()) {
            System.out.println("Enter a description for the task");
            shake(this.descriptionField);
            return;
        }

        Task task = new Task(this.user.getUserid(), taskText, description);

        this.databaseHandler.insertTask(task);
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    private static void shake(Node node) {
        Shaker shaker = new Shaker(node);
        shaker.shake();
    }
}
