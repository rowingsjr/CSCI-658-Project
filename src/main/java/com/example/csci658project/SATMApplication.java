package com.example.csci658project;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.DecimalFormat;

public class SATMApplication extends Application
{
    private Rectangle square; // The 'screen' of the ATM
    private Text text; // The text displayed on the 'screen'
    String originalStyle = "-fx-background-color: #282928; -fx-text-fill: #FFFFFF;";
    String clickedStyle = "-fx-background-color: #8F9494FF; -fx-text-fill: #FFFFFF;";
    private String pin = "";
    private String correctPin = "1906";
    private int count;
    private double balance = 3500.00;
    private double deposit = 0.00;
    private double withdrawal = 0.00;
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
        b6.setOnMouseClicked(mouseEvent ->
        {
            b6.setStyle(clickedStyle);
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String balString = decimalFormat.format(balance);
            updateScreenContent("Balance is:\n" + "$" +balString,
                    Color.rgb(32, 115, 69));
            PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
            clickPause.setOnFinished(e -> b6.setStyle(originalStyle));
            clickPause.play();

            // Transition to PIN entry after 5 seconds
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> updateTransactionScreen("Select transaction:\nbalance >\n\ndeposit >" +
                            "\n\nwithdrawal >",
                    Color.rgb(32, 115, 69)));
            pause.play();

        });


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

        // Create a square using a Rectangle shape
        square = new Rectangle(500, 300);
        //square.setFill(Color.rgb(32,115,69));
        square.setArcWidth(20);
        square.setArcHeight(20);
        square.setTranslateY(-150);

        // Create the multiline text
        text = new Text("Welcome to\n\nO.I.G. Credit Union\n\nPlease insert your Platinum ATM card");
        //text.setFont(Font.font("Georgia", 35));
        //text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(square.getWidth() - 20); // Adjust the wrapping width
        text.setTranslateY(-150);

        //print receipt button
        Button printReceipt = new Button();
        printReceipt.setPrefWidth(150);
        printReceipt.setPrefHeight(20);
        printReceipt.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        printReceipt.setText("Print Receipt");
        printReceipt.setFont(new Font(18));
        printReceipt.setTranslateX(-245);
        printReceipt.setTranslateY(55);

        //numberpad grid pane
        Button n1 = new Button("1");
        n1.setPrefWidth(50);
        n1.setPrefHeight(50);
        n1.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n1.setOnMouseClicked(mouseEvent ->
        {
            n1.setStyle(clickedStyle);
            handleNumberPress("1");
            PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
            clickPause.setOnFinished(e -> n1.setStyle(originalStyle));
            clickPause.play();

        });

        Button n2 = new Button("2");
        n2.setPrefWidth(50);
        n2.setPrefHeight(50);
        n2.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n2.setOnMouseClicked(mouseEvent ->
        {
            n2.setStyle(clickedStyle);
            handleNumberPress("2");
            PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
            clickPause.setOnFinished(e -> n2.setStyle(originalStyle));
            clickPause.play();

        });

        Button n3 = new Button("3");
        n3.setPrefWidth(50);
        n3.setPrefHeight(50);
        n3.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n3.setOnMouseClicked(mouseEvent ->
        {
            n3.setStyle(clickedStyle);
            handleNumberPress("3");
            PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
            clickPause.setOnFinished(e -> n3.setStyle(originalStyle));
            clickPause.play();

        });

        Button n4 = new Button("4");
        n4.setPrefWidth(50);
        n4.setPrefHeight(50);
        n4.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n4.setOnMouseClicked(mouseEvent ->
        {
            n4.setStyle(clickedStyle);
            handleNumberPress("4");
            PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
            clickPause.setOnFinished(e -> n4.setStyle(originalStyle));
            clickPause.play();

        });

        Button n5 = new Button("5");
        n5.setPrefWidth(50);
        n5.setPrefHeight(50);
        n5.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n5.setOnMouseClicked(mouseEvent ->
        {
            n5.setStyle(clickedStyle);
            handleNumberPress("5");
            PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
            clickPause.setOnFinished(e -> n5.setStyle(originalStyle));
            clickPause.play();

        });

        Button n6 = new Button("6");
        n6.setPrefWidth(50);
        n6.setPrefHeight(50);
        n6.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n6.setOnMouseClicked(mouseEvent ->
        {
            n6.setStyle(clickedStyle);
            handleNumberPress("6");
            PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
            clickPause.setOnFinished(e -> n6.setStyle(originalStyle));
            clickPause.play();

        });

        Button n7 = new Button("7");
        n7.setPrefWidth(50);
        n7.setPrefHeight(50);
        n7.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n7.setOnMouseClicked(mouseEvent ->
        {
            n7.setStyle(clickedStyle);
            handleNumberPress("7");
            PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
            clickPause.setOnFinished(e -> n7.setStyle(originalStyle));
            clickPause.play();

        });

        Button n8 = new Button("8");
        n8.setPrefWidth(50);
        n8.setPrefHeight(50);
        n8.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n8.setOnMouseClicked(mouseEvent ->
        {
            n8.setStyle(clickedStyle);
            handleNumberPress("8");
            PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
            clickPause.setOnFinished(e -> n8.setStyle(originalStyle));
            clickPause.play();

        });

        Button n9 = new Button("9");
        n9.setPrefWidth(50);
        n9.setPrefHeight(50);
        n9.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n9.setOnMouseClicked(mouseEvent ->
        {
            n9.setStyle(clickedStyle);
            handleNumberPress("9");
            PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
            clickPause.setOnFinished(e -> n9.setStyle(originalStyle));
            clickPause.play();

        });

        Button n0 = new Button("0");
        n0.setPrefWidth(50);
        n0.setPrefHeight(50);
        n0.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n0.setOnMouseClicked(mouseEvent ->
        {
            n0.setStyle(clickedStyle);
            handleNumberPress("0");
            PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
            clickPause.setOnFinished(e -> n0.setStyle(originalStyle));
            clickPause.play();

        });


        GridPane numberPad = new GridPane(0,10);
        numberPad.setPadding(new Insets(10));
        numberPad.setHgap(15);
        numberPad.setVgap(15);

        numberPad.add(n1,0,0);
        numberPad.add(n2,1,0);
        numberPad.add(n3,2,0);
        numberPad.add(n4,0,1);
        numberPad.add(n5,1,1);
        numberPad.add(n6,2,1);
        numberPad.add(n7,0,2);
        numberPad.add(n8,1,2);
        numberPad.add(n9,2,2);
        numberPad.add(n0,1,3);
        numberPad.setTranslateX(445);
        numberPad.setTranslateY(400);

        //Pin Screen Actions


        //CardSlot Button
        Button cardSlot = new Button();
        cardSlot.setPrefWidth(150);
        cardSlot.setPrefHeight(20);
        cardSlot.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        cardSlot.setText("Card Slot");
        cardSlot.setFont(new Font(18));
        cardSlot.setTranslateX(245);
        cardSlot.setTranslateY(55);

        // Set the event handler for the cardSlot button
        cardSlot.setOnAction(event -> {
            cardSlot.setStyle(clickedStyle);
            updateScreenContent("Processing...\nPlease Wait", Color.DARKGRAY);
            // Reset the button's style back to the original after a brief moment
            PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
            clickPause.setOnFinished(e -> cardSlot.setStyle(originalStyle));
            clickPause.play();

            // Transition to PIN entry after 5 seconds
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> promptForPin());
            pause.play();
        });


        //Enter Button
        Button enterButton = new Button();
        enterButton.setPrefWidth(100);
        enterButton.setPrefHeight(20);
        enterButton.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        enterButton.setText("Enter");
        enterButton.setFont(new Font(18));
        enterButton.setTranslateX(270);
        enterButton.setTranslateY(105);
        enterButton.setOnMouseClicked(mouseEvent ->
        {
            enterButton.setStyle(clickedStyle);
            // Reset the button's style back to the original after a brief moment
            PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
            clickPause.setOnFinished(e -> enterButton.setStyle(originalStyle));
            clickPause.play();
            if(count == 4)
            {
                count = 0;
                //check if the correct pin is entered
                if(pin.equals(correctPin))
                {
                    //Call Screen 5 - Transaction Screen
                    updateTransactionScreen("Select transaction:\nbalance >\n\ndeposit >\n\nwithdrawal >",
                            Color.rgb(32, 115, 69));
                    pin = "";
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.play();
                }
                else
                {
                    updateScreenContent("Your PIN is incorrect. \n Please try again.",
                            Color.rgb(32, 115, 69));
                    pin = "";
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(e -> promptForPin());
                    pause.play();
                }
            }
        });

        //Clear Button
        Button clearButton = new Button();
        clearButton.setPrefWidth(100);
        clearButton.setPrefHeight(20);
        clearButton.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        clearButton.setText("Clear");
        clearButton.setFont(new Font(18));
        clearButton.setTranslateX(270);
        clearButton.setTranslateY(155);
        // Set the event handler for the clear button
        clearButton.setOnAction(event -> {
            clearButton.setStyle(clickedStyle);
            pin = "";
            count = 0;
            // Reset the button's style back to the original after a brief moment
            PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
            clickPause.setOnFinished(e -> clearButton.setStyle(originalStyle));
            clickPause.play();
            promptForPin();
        });

        //Cancel Button
        Button cancelButton = new Button();
        cancelButton.setPrefWidth(100);
        cancelButton.setPrefHeight(20);
        cancelButton.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        cancelButton.setText("Cancel");
        cancelButton.setFont(new Font(18));
        cancelButton.setTranslateX(270);
        cancelButton.setTranslateY(205);

        //Cash Dispenser Rectangle
        Rectangle cashDispenser = new Rectangle();
        cashDispenser.setWidth(350);
        cashDispenser.setHeight(30);
        cashDispenser.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        cashDispenser.setArcHeight(20);
        cashDispenser.setArcWidth(20);
        cashDispenser.setTranslateX(-150);
        cashDispenser.setTranslateY(315);

        //Text for Cash Dispenser
        Text rectText = new Text("Cash Dispenser");
        rectText.setFont(new Font("Georgia",18));
        rectText.setFill(Color.WHITE);
        rectText.setTranslateX(-150);
        rectText.setTranslateY(315);

        //Deposit Slot Button
        Button depositSlot = new Button();
        depositSlot.setPrefWidth(250);
        depositSlot.setPrefHeight(30);
        depositSlot.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        depositSlot.setText("Deposit Slot");
        depositSlot.setFont(new Font(18));
        depositSlot.setTranslateX(200);
        depositSlot.setTranslateY(315);



        //color for window
        root.setStyle("-fx-background-color: #919186FF;");

        Scene scene = new Scene(root, 1100,800);

        //For loops to make buttons look more real
        for(Node node: buttonGridLeft.getChildren())
        {
            if(node instanceof Button)
            {
                apply3DButtonStyle((Button) node);
            }
        }

        for(Node node: buttonGridRight.getChildren())
        {
            if(node instanceof Button)
            {
                apply3DButtonStyle((Button) node);
            }
        }

        for(Node node: numberPad.getChildren())
        {
            if(node instanceof Button)
            {
                apply3DButtonStyle((Button) node);
            }
        }

        //manual calling of apply3dBS
        apply3DButtonStyle(printReceipt);
        apply3DButtonStyle(cardSlot);
        apply3DButtonStyle(enterButton);
        apply3DButtonStyle(clearButton);
        apply3DButtonStyle(cancelButton);
        apply3DButtonStyle(depositSlot);

        //run rectangles through
        applyScreenStyle(square, text);
        applySlotStyle(cashDispenser,rectText);



        root.getChildren().addAll(square, text);
        root.getChildren().add(buttonGridLeft);
        root.getChildren().add(buttonGridRight);
        root.getChildren().add(printReceipt);
        root.getChildren().add(numberPad);
        root.getChildren().add(cardSlot);
        root.getChildren().add(enterButton);
        root.getChildren().add(clearButton);
        root.getChildren().add(cancelButton);
        root.getChildren().addAll(cashDispenser, rectText);
        root.getChildren().add(depositSlot);
        StackPane.setAlignment(square, Pos.CENTER);


        satmFrame.setWidth(1100);
        satmFrame.setHeight(800);
        satmFrame.setScene(scene);

        satmFrame.show();


    }

    private void apply3DButtonStyle(Button button) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.rgb(0, 0, 0, 0.8));
        shadow.setOffsetX(2);
        shadow.setOffsetY(2);

        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#606060")),
                new Stop(1, Color.web("#202020")));

        button.setStyle("-fx-background-radius: 5;" +
                "-fx-background-insets: 0;" +
                "-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #606060, #202020);" +
                "-fx-text-fill: white;" +
                "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.8) , 2,2,2,2 );");

        button.setEffect(shadow);
    }

    private void applyScreenStyle(Rectangle screen, Text screenText) {
        // Create a gradient to simulate the glassy look of a real screen
        LinearGradient screenGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(20, 89, 50)),
                new Stop(1, Color.rgb(15, 75, 42)));

        // Apply the gradient as the fill for the rectangle
        screen.setFill(screenGradient);

        // Apply an inner shadow to give the rectangle depth like a screen
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(-2.0f);
        innerShadow.setOffsetY(-2.0f);
        innerShadow.setColor(Color.rgb(0, 0, 0, 0.5));
        screen.setEffect(innerShadow);

        // Style the text to look like it's on a screen
        screenText.setFont(Font.font("Consolas", FontWeight.BOLD, 35));
        screenText.setFill(Color.rgb(230, 240, 230)); // A light greenish color to simulate screen text
        screenText.setTextAlignment(TextAlignment.CENTER);
        screenText.setWrappingWidth(screen.getWidth() - 20); // Adjust to fit within the rectangle
    }

    private void applySlotStyle(Rectangle slot, Text slotText) {
        // Set the base color for the slot
        Color baseColor = Color.rgb(50, 50, 50); // A dark color for the slot

        // Apply an inner shadow to create the illusion of depth
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setColor(Color.rgb(0, 0, 0, 0.9));
        innerShadow.setOffsetX(2);
        innerShadow.setOffsetY(2);

        slot.setFill(baseColor);
        slot.setEffect(innerShadow);

        // Style the text
        slotText.setFont(Font.font("Georgia", 18));
        slotText.setFill(Color.WHITE);

        // Optional: Apply a gradient or pattern fill to suggest the slot texture
        // This is just a placeholder; adjust the stops and colors to fit your design
        slot.setFill(new LinearGradient(
                0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(60, 60, 60)),
                new Stop(0.5, Color.rgb(20, 20, 20)),
                new Stop(1, Color.rgb(60, 60, 60))
        ));
    }

    private void updateScreenContent(String newText, Color backgroundColor) {
        text.setText(newText); // Update the text
        square.setFill(backgroundColor); // Update the background color of the screen
        // If you have other styles/effects, update them here as needed
    }

    private void updateTransactionScreen(String newText, Color backgroundColor) {
        text.setText(newText); // Update the text
        text.setTextAlignment(TextAlignment.RIGHT);
        square.setFill(backgroundColor); // Update the background color of the screen
        // If you have other styles/effects, update them here as needed
    }

    private void promptForPin() {
        // This will be called after the "Processing..." message
        updateScreenContent("Please enter your PIN\n_ _ _ _", Color.rgb(32, 115, 69)); // Original screen color
    }

    private void handleNumberPress(String number)
    {
        if (pin.length() < 4)
        {
            pin += number; // Append the number to the PIN
            updatePinDisplay(); // Update the visual PIN display

        }
    }

    private void updatePinDisplay()
    {
        // Assuming the PIN entry prompt is on the next line after some text, e.g., "Please enter your PIN"
        String[] lines = text.getText().split("\n");
        StringBuilder display = new StringBuilder(lines[0]).append("\n"); // Retain the first line of the text
        for (int i = 0; i < pin.length(); i++) {
            display.append("* ");
        }
        for (int i = pin.length(); i < 4; i++) {
            display.append("_ ");
        }
        text.setText(display.toString().trim()); // Update the text to show the visual PIN
        count = count + 1;


    }

    public static void main (String[] args)
    {
        launch(args);
    }
}
