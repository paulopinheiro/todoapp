package br.jus.trt12.paulopinheiro.todoapp.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddItemFormController implements Initializable {
    @FXML
    private TextField descriptionField;
    @FXML
    private Button saveTaskBUtton;
    @FXML
    private TextField taskField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
