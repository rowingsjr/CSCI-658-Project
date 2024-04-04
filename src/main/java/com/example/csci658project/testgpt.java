package com.example.csci658project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class testgpt extends Application
{

    private boolean isHeld = false;
    private Timeline holdTimer;

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Click and Hold for 5 seconds");
        label.setStyle("-fx-font-size: 24px;");

        StackPane root = new StackPane();
        root.getChildren().add(label);

        Scene scene = new Scene(root, 300, 200);

        // Mouse Pressed Event Handler
        scene.setOnMousePressed(event -> {
            isHeld = true;
            startHoldTimer();
        });

        // Mouse Released Event Handler
        scene.setOnMouseReleased(event -> {
            isHeld = false;
            resetHoldTimer();
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Click and Hold Event Example");
        primaryStage.show();
    }

    private void startHoldTimer() {
        holdTimer = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            if (isHeld) {
                System.out.println("Click and Hold event triggered!");
                // Perform your desired action here
            }
        }));
        holdTimer.setCycleCount(1);
        holdTimer.play();
    }

    private void resetHoldTimer() {
        if (holdTimer != null) {
            holdTimer.stop();
            holdTimer = null;
        }
    }

        public static void main(String[] args) {
            launch(args);
        }
    }

