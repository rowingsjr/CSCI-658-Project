package com.example.csci658project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class SATMApplication extends Application
{
    @Override
    public void start(Stage satmFrame) throws Exception
    {
        //root framework
        StackPane root = new StackPane();
        Button b1 = new Button();
        b1.setPrefWidth(50);
        b1.setPrefHeight(50);
        b1.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");

        Button b2 = new Button();
        b2.setPrefWidth(50);
        b2.setPrefHeight(50);
        b2.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");

        Button b3 = new Button();
        b3.setPrefWidth(50);
        b3.setPrefHeight(50);
        b3.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");

        Button b4 = new Button();
        b4.setPrefWidth(50);
        b4.setPrefHeight(50);
        b4.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");

        Button b5 = new Button();
        b5.setPrefWidth(50);
        b5.setPrefHeight(50);
        b5.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");

        Button b6 = new Button();
        b6.setPrefWidth(50);
        b6.setPrefHeight(50);
        b6.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");

        Button b7 = new Button();
        b7.setPrefWidth(50);
        b7.setPrefHeight(50);
        b7.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");

        Button b8 = new Button();
        b8.setPrefWidth(50);
        b8.setPrefHeight(50);
        b8.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");

        GridPane buttonGridLeft = new GridPane(0,10);
        buttonGridLeft.setPadding(new Insets(10));
        buttonGridLeft.setHgap(10);
        buttonGridLeft.setVgap(30);

        buttonGridLeft.add(b1, 0,0);
        buttonGridLeft.add(b2, 0,1);
        buttonGridLeft.add(b3, 0,2);
        buttonGridLeft.add(b4, 0,3);
        buttonGridLeft.setTranslateX(215);
        buttonGridLeft.setTranslateY(75);

        GridPane buttonGridRight = new GridPane(0,10);
        buttonGridRight.setPadding(new Insets(10));
        buttonGridRight.setHgap(10);
        buttonGridRight.setVgap(30);

        buttonGridRight.add(b5, 0,0);
        buttonGridRight.add(b6, 0,1);
        buttonGridRight.add(b7, 0,2);
        buttonGridRight.add(b8, 0,3);
        buttonGridRight.setTranslateX(805);
        buttonGridRight.setTranslateY(75);

        //color for window
        root.setStyle("-fx-background-color: #bfc3bd;");

        Scene scene = new Scene(root, 1100,800);

        // Create a square using a Rectangle shape
        Rectangle square = new Rectangle(500, 300);
        square.setFill(Color.rgb(89,212,133));
        //square.setStyle("-fx-background-color: #8eccfc;");
        square.setArcWidth(20);
        square.setArcHeight(20);
        square.setTranslateY(-150);

        // Create the multiline text
        Text text = new Text("Welcome to\n\nO.I.G. Credit Union\n\nPlease insert your Platinum ATM card");
        text.setFont(Font.font("Georgia", 35));
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(square.getWidth() - 20); // Adjust the wrapping width
        text.setTranslateY(-150);

        root.getChildren().addAll(square, text);
        root.getChildren().add(buttonGridLeft);
        root.getChildren().add(buttonGridRight);
        StackPane.setAlignment(square, Pos.CENTER);


        satmFrame.setWidth(1100);
        satmFrame.setHeight(800);
        satmFrame.setScene(scene);

        satmFrame.show();


    }

    public static void main (String[] args)
    {
        launch(args);
    }
}
