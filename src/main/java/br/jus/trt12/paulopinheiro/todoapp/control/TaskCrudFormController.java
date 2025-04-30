package br.jus.trt12.paulopinheiro.todoapp.control;

import br.jus.trt12.paulopinheiro.todoapp.database.DatabaseHandler;
import br.jus.trt12.paulopinheiro.todoapp.model.Task;
import br.jus.trt12.paulopinheiro.todoapp.model.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TaskCrudFormController implements Initializable {
    private final User user;
    private final DatabaseHandler dbHandler = new DatabaseHandler();
    private Task myTask;

    private static final ObservableList taskObList = FXCollections.observableArrayList();

    @FXML
    private Label tittleLabel;
    @FXML
    private ListView<Task> taskListView;
    @FXML
    private TextField taskTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private Button clearButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;

    TaskCrudFormController(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        writeTittle();
        populateList();
        setupControls();
        clearMyTask();
    }

    private void writeTittle() {
        tittleLabel.setText(user.getFirstName() + " tasks");
    }

    private void setupControls() {
        setupList();
        setupButtons();
    }

    private void setupList() {
        // Add event listener that fires when an item is selected
        taskListView.getSelectionModel().selectedItemProperty().addListener((var ov, var old_task, var new_task) -> {
            setMyTask(new_task);
        });
    }

    private void setupButtons() {
        clearButton.setOnAction(event -> {
            clearMyTask();
        });

        saveButton.setOnAction(event -> {
            saveMyTask();
        });

        deleteButton.setOnAction(event -> {
            deleteMyTask();
        });
    }

    private void clearMyTask() {
        setMyTask(null);
        clearListSelection();
    }

    private void saveMyTask() {
        // Criticize fields
        if (taskTextField.getText().isBlank()) {
            System.out.println("Task name is required!"); return;
        } else this.getMyTask().setTask(taskTextField.getText());
        if (descriptionTextArea.getText().isBlank()) {
            System.out.println("Task description is required!"); return;
        } else this.getMyTask().setDescription(descriptionTextArea.getText());

        // Redundant security
        if (myTask.getUserid()==null) myTask.setUserid(user.getUserid());

        // Insert if it is new. Update if it is existent.
        if (isNewTask()) {
            dbHandler.insertTask(this.getMyTask());
        } else {
            dbHandler.updateTask(this.getMyTask());
        }

        clearMyTask();
        populateList();
    }

    private void deleteMyTask() {
        if (!isNewTask()) {
            dbHandler.deleteTask(this.getMyTask());

            clearMyTask();
            populateList();
        }
    }

    private void populateList() {
        List<Task> taskList = dbHandler.getTasksByUser(user);
        taskObList.setAll(taskList);
        taskListView.setItems(taskObList);
    }

    private void updateFields() {
        if (isNewTask()) {
            taskTextField.setText("");
            descriptionTextArea.setText("");
        } else {
            taskTextField.setText(getMyTask().getTask());
            descriptionTextArea.setText(getMyTask().getDescription());
        }
    }

    private void updateButtons() {
        deleteButton.setDisable(isNewTask());
    }

    private void clearListSelection() {
        taskListView.getSelectionModel().clearSelection();        
    }

    private Task getMyTask() {
        if (myTask==null) {
            myTask = new Task();
            myTask.setUserid(this.user.getUserid());
        }
        return myTask;
    }

    private void setMyTask(Task myTask) {
        this.myTask = myTask;
        updateFields();
        updateButtons();
    }

    private boolean isNewTask() {
        return this.getMyTask().getTaskid()==null;
    }
}
