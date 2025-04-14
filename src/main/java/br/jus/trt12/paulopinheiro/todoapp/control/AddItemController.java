package br.jus.trt12.paulopinheiro.todoapp.control;

import br.jus.trt12.paulopinheiro.todoapp.control.animations.Animations;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AddItemController implements Initializable {
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private ImageView addButton;
   @FXML
    private Label noTasksLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            loadTaskForm();
        });
    }    

    private void loadTaskForm() {
        try {
            Animations.fadeOut(addButton);
            Animations.fadeOut(noTasksLabel);
            AnchorPane formAnchorPane = FXMLLoader.load(getClass().getResource("/addItemForm.fxml"));
            Animations.fadeIn(formAnchorPane);
            rootAnchorPane.getChildren().setAll(formAnchorPane);
        } catch (IOException ex) {
        }
    }
}
