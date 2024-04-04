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

public class testgpt extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("3D Button");

        // Apply a gradient background
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#f0ff35")),
                new Stop(1, Color.web("#a9ff00")));
        button.setStyle("-fx-background-color: linear-gradient(#f0ff35, #a9ff00), radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #fbff8f 50%); " +
                "-fx-background-radius: 6, 5; " +
                "-fx-background-insets: 0, 1; " +
                "-fx-text-fill: #395306;");

        // Apply a drop shadow effect
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.rgb(0, 0, 0, 0.6));
        shadow.setOffsetX(0);
        shadow.setOffsetY(1);
        shadow.setRadius(5);
        button.setEffect(shadow);

        // Changing the shadow on hover to enhance the 3D effect
        button.setOnMouseEntered(e -> {
            shadow.setOffsetX(0);
            shadow.setOffsetY(2);
            shadow.setRadius(10);
            shadow.setColor(Color.rgb(0, 0, 0, 0.7));
        });

        // Reverting the shadow when the mouse exits
        button.setOnMouseExited(e -> {
            shadow.setOffsetX(0);
            shadow.setOffsetY(1);
            shadow.setRadius(5);
            shadow.setColor(Color.rgb(0, 0, 0, 0.6));
        });

        StackPane root = new StackPane(button);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("3D Button");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

