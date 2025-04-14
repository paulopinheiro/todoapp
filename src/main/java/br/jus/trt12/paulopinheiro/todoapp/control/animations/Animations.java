package br.jus.trt12.paulopinheiro.todoapp.control.animations;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animations {
    public static void fadeOut(Node node){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000),node);
        fadeTransition.setFromValue(1f);
        fadeTransition.setToValue(0f);
        fadeTransition.setCycleCount(4);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();
    }
}
