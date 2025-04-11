package br.jus.trt12.paulopinheiro.todoapp.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AddItemController implements Initializable {
    @FXML
    private ImageView addButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Mouse clicked on Add icon");
        });
    }    
    
}
