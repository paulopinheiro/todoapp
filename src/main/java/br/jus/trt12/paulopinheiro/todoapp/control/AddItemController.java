package br.jus.trt12.paulopinheiro.todoapp.control;

import br.jus.trt12.paulopinheiro.todoapp.control.animations.Animations;
import br.jus.trt12.paulopinheiro.todoapp.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AddItemController implements Initializable {
    private User user;

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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/addItemForm.fxml"));
            AnchorPane formAnchorPane = loader.load();

            Animations.fadeIn(formAnchorPane);
            rootAnchorPane.getChildren().setAll(formAnchorPane);

            AddItemFormController addItemFormController = loader.getController();
            addItemFormController.setUser(user);
        } catch (IOException ex) {
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
