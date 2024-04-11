package com.example.csci658project;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.text.DecimalFormat;

public class SATMApplication extends Application
{
    Rectangle square; // The 'screen' of the ATM
    Text text; // The text displayed on the 'screen'
    String originalStyle = "-fx-background-color: #282928; -fx-text-fill: #FFFFFF;";
    String clickedStyle = "-fx-background-color: #8F9494FF; -fx-text-fill: #FFFFFF;";
    String pin = "";
    String correctPin = "1906";
    int count;
    double balance = 3500.00;
    double deposit = 0.00;
    double withdrawal = 0.00;
    boolean isHeld = false;
    Timeline holdTimer;
    boolean timerDone = false;
    boolean depositFlag = false;
    private boolean withdrawalFlag = false;
    boolean enterButtonPressed = false;
    String depositString = "";
    private int enterCount = 0;
    String withdrawalString = "";
    double numCheck = 0.0;
    private boolean chuteClear = false;
    boolean transactionFlag = false;
    private double orgBalance = 0.0;
    private boolean processFlag = false;
    private Stage receiptWindow;
    private Rectangle cashDispenser;
    @Override
    public void start(Stage satmFrame) throws Exception
    {
        orgBalance = balance;
        receiptWindow = satmFrame;
        //root framework
        StackPane root = new StackPane();

        Button b1 = new Button("YES");
        b1.setId("B1");
        b1.setPrefWidth(50);
        b1.setPrefHeight(50);
        b1.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        b1.setOnMouseClicked(mouseEvent ->
        {
            if(transactionFlag)
            {
                b1.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> b1.setStyle(originalStyle));
                clickPause.play();

                updateTransactionScreen("Select transaction:\nbalance >\n\ndeposit >" +
                                "\n\nwithdrawal >",
                        Color.rgb(32, 115, 69));
            }

        });


        Button b2 = new Button("NO");
        b2.setId("B2");
        b2.setPrefWidth(50);
        b2.setPrefHeight(50);
        b2.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        b2.setOnMouseClicked(mouseEvent ->
        {
            if(transactionFlag)
            {
                b2.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> b2.setStyle(originalStyle));
                clickPause.play();
                updateScreenContent("Please take your receipt and ATM card.\nThank you.\nHave a nice day.",
                        Color.rgb(32, 115, 69));
                printReceipt();
                processFlag = false;
            }

        });

        Button b3 = new Button();
        b3.setId("B3");
        b3.setPrefWidth(50);
        b3.setPrefHeight(50);
        b3.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");

        Button b4 = new Button();
        b4.setId("B4");
        b4.setPrefWidth(50);
        b4.setPrefHeight(50);
        b4.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");

        Button b5 = new Button();
        b5.setId("B5");
        b5.setPrefWidth(50);
        b5.setPrefHeight(50);
        b5.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");

        Button b6 = new Button();
        b6.setId("B6");
        b6.setPrefWidth(50);
        b6.setPrefHeight(50);
        b6.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        b6.setOnMouseClicked(mouseEvent ->
        {
            if(enterCount >= 1)
            {
                processFlag = true;
            }
            if(processFlag)
            {
                b6.setStyle(clickedStyle);
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
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
            }

        });


        Button b7 = new Button();
        b7.setId("B7");
        b7.setPrefWidth(50);
        b7.setPrefHeight(50);
        b7.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        b7.setOnMouseClicked(mouseEvent ->
        {
            if(enterCount >= 2)
            {
                processFlag = true;
            }
            if(processFlag)
            {
                depositFlag = true;
                b7.setStyle(clickedStyle);
                updateScreenContent("Enter amount you want to deposit.",
                        Color.rgb(32, 115, 69));
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> b7.setStyle(originalStyle));
                clickPause.play();
            }

        });

        Button b8 = new Button();
        b8.setId("B8");
        b8.setPrefWidth(50);
        b8.setPrefHeight(50);
        b8.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        b8.setOnMouseClicked(mouseEvent ->
        {
            if(enterCount >= 2)
            {
                processFlag = true;
            }

            if(processFlag)
            {
                withdrawalFlag = true;
                b8.setStyle(clickedStyle);
                updateScreenContent("Enter amount.\nWithdrawals must be multiples of $10",
                        Color.rgb(32, 115, 69));
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> b8.setStyle(originalStyle));
                clickPause.play();
            }

        });

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
        square.setArcWidth(20);
        square.setArcHeight(20);
        square.setTranslateY(-150);

        // Create the multiline text
        text = new Text("Welcome to\n\nO.I.G. Credit Union\n\nPlease insert your Platinum ATM card");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(square.getWidth() - 20); // Adjust the wrapping width
        text.setTranslateY(-150);
        text.setId("WelcomeMessage");

        //print receipt button
        Button printReceipt = new Button();
        printReceipt.setId("printReceiptButton");
        printReceipt.setPrefWidth(150);
        printReceipt.setPrefHeight(20);
        printReceipt.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        printReceipt.setText("Print Receipt");
        printReceipt.setFont(new Font(18));
        printReceipt.setTranslateX(-245);
        printReceipt.setTranslateY(55);
        printReceipt.setOnMouseClicked(mouseEvent ->
        {
            if(transactionFlag)
            {
                printReceipt.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> printReceipt.setStyle(originalStyle));
                clickPause.play();
                printReceipt();
            }

        });

        //numberpad grid pane
        Button n1 = new Button("1");
        n1.setId("numpad1");
        n1.setPrefWidth(50);
        n1.setPrefHeight(50);
        n1.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n1.setOnMouseClicked(mouseEvent ->
        {
            if(processFlag)
            {
                if (!depositFlag && !withdrawalFlag)
                {
                    n1.setStyle(clickedStyle);
                    PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                    clickPause.setOnFinished(e -> n1.setStyle(originalStyle));
                    clickPause.play();
                    handleNumberPress("1");
                }
            }
            if(processFlag && depositFlag && !withdrawalFlag)
            {
                n1.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n1.setStyle(originalStyle));
                clickPause.play();
                depositAmt("1");
            }
            else if (processFlag && withdrawalFlag && !depositFlag)
            {
                n1.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n1.setStyle(originalStyle));
                clickPause.play();
                withdrawalAmt("1");
            }

        });

        Button n2 = new Button("2");
        n2.setId("numpad2");
        n2.setPrefWidth(50);
        n2.setPrefHeight(50);
        n2.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n2.setOnMouseClicked(mouseEvent ->
        {
            if(processFlag)
            {
                if (!depositFlag && !withdrawalFlag)
                {
                    n2.setStyle(clickedStyle);
                    PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                    clickPause.setOnFinished(e -> n2.setStyle(originalStyle));
                    clickPause.play();
                    handleNumberPress("2");
                }
            }
            if(processFlag && depositFlag && !withdrawalFlag)
            {
                n2.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n2.setStyle(originalStyle));
                clickPause.play();
                depositAmt("2");
            }
            else if (processFlag && withdrawalFlag && !depositFlag)
            {
                n2.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n2.setStyle(originalStyle));
                clickPause.play();
                withdrawalAmt("2");
            }

        });

        Button n3 = new Button("3");
        n3.setId("numpad3");
        n3.setPrefWidth(50);
        n3.setPrefHeight(50);
        n3.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n3.setOnMouseClicked(mouseEvent ->
        {
            if(processFlag)
            {
                if (!depositFlag && !withdrawalFlag)
                {
                    n3.setStyle(clickedStyle);
                    PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                    clickPause.setOnFinished(e -> n3.setStyle(originalStyle));
                    clickPause.play();
                    handleNumberPress("3");
                }
            }
            if(processFlag && depositFlag && !withdrawalFlag)
            {
                n3.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n3.setStyle(originalStyle));
                clickPause.play();
                depositAmt("3");
            }
            else if (processFlag && withdrawalFlag && !depositFlag)
            {
                n3.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n3.setStyle(originalStyle));
                clickPause.play();
                withdrawalAmt("3");
            }

        });

        Button n4 = new Button("4");
        n4.setId("numpad4");
        n4.setPrefWidth(50);
        n4.setPrefHeight(50);
        n4.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n4.setOnMouseClicked(mouseEvent ->
        {
            if(processFlag)
            {
                if (!depositFlag && !withdrawalFlag)
                {
                    n4.setStyle(clickedStyle);
                    PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                    clickPause.setOnFinished(e -> n4.setStyle(originalStyle));
                    clickPause.play();
                    handleNumberPress("4");
                }
            }
            if(processFlag && depositFlag && !withdrawalFlag)
            {
                n4.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n4.setStyle(originalStyle));
                clickPause.play();
                depositAmt("4");
            }
            else if (processFlag && withdrawalFlag && !depositFlag)
            {
                n4.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n4.setStyle(originalStyle));
                clickPause.play();
                withdrawalAmt("4");
            }

        });

        Button n5 = new Button("5");
        n5.setId("numpad5");
        n5.setPrefWidth(50);
        n5.setPrefHeight(50);
        n5.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n5.setOnMouseClicked(mouseEvent ->
        {
            if(processFlag)
            {
                if (!depositFlag && !withdrawalFlag)
                {
                    n5.setStyle(clickedStyle);
                    PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                    clickPause.setOnFinished(e -> n5.setStyle(originalStyle));
                    clickPause.play();
                    handleNumberPress("5");
                }
            }
            if(processFlag && depositFlag && !withdrawalFlag)
            {
                n5.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n5.setStyle(originalStyle));
                clickPause.play();
                depositAmt("5");
            }
            else if (processFlag && withdrawalFlag && !depositFlag)
            {
                n5.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n5.setStyle(originalStyle));
                clickPause.play();
                withdrawalAmt("5");
            }

        });

        Button n6 = new Button("6");
        n6.setId("numpad6");
        n6.setPrefWidth(50);
        n6.setPrefHeight(50);
        n6.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n6.setOnMouseClicked(mouseEvent ->
        {
            if(processFlag)
            {
                if (!depositFlag && !withdrawalFlag)
                {
                    n6.setStyle(clickedStyle);
                    PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                    clickPause.setOnFinished(e -> n6.setStyle(originalStyle));
                    clickPause.play();
                    handleNumberPress("6");
                }
            }
            if(processFlag && depositFlag && !withdrawalFlag)
            {
                n6.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n6.setStyle(originalStyle));
                clickPause.play();
                depositAmt("6");
            }
            else if (processFlag && withdrawalFlag && !depositFlag)
            {
                n6.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n6.setStyle(originalStyle));
                clickPause.play();
                withdrawalAmt("6");
            }

        });

        Button n7 = new Button("7");
        n7.setId("numpad7");
        n7.setPrefWidth(50);
        n7.setPrefHeight(50);
        n7.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n7.setOnMouseClicked(mouseEvent ->
        {
            if(processFlag)
            {
                if (!depositFlag && !withdrawalFlag)
                {
                    n7.setStyle(clickedStyle);
                    PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                    clickPause.setOnFinished(e -> n7.setStyle(originalStyle));
                    clickPause.play();
                    handleNumberPress("7");
                }
            }
            if(processFlag && depositFlag && !withdrawalFlag)
            {
                n7.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n7.setStyle(originalStyle));
                clickPause.play();
                depositAmt("7");
            }
            else if (processFlag && withdrawalFlag && !depositFlag)
            {
                n7.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n7.setStyle(originalStyle));
                clickPause.play();
                withdrawalAmt("7");
            }

        });

        Button n8 = new Button("8");
        n8.setId("numpad8");
        n8.setPrefWidth(50);
        n8.setPrefHeight(50);
        n8.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n8.setOnMouseClicked(mouseEvent ->
        {
            if(processFlag)
            {
                if (!depositFlag && !withdrawalFlag)
                {
                    n8.setStyle(clickedStyle);
                    PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                    clickPause.setOnFinished(e -> n8.setStyle(originalStyle));
                    clickPause.play();
                    handleNumberPress("8");
                }
            }
            if(processFlag && depositFlag && !withdrawalFlag)
            {
                n8.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n8.setStyle(originalStyle));
                clickPause.play();
                depositAmt("8");
            }
            else if (processFlag && withdrawalFlag && !depositFlag)
            {
                n8.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n8.setStyle(originalStyle));
                clickPause.play();
                withdrawalAmt("8");
            }

        });

        Button n9 = new Button("9");
        n9.setId("numpad9");
        n9.setPrefWidth(50);
        n9.setPrefHeight(50);
        n9.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n9.setOnMouseClicked(mouseEvent ->
        {
            if(processFlag)
            {
                if (!depositFlag && !withdrawalFlag)
                {
                    n9.setStyle(clickedStyle);
                    PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                    clickPause.setOnFinished(e -> n9.setStyle(originalStyle));
                    clickPause.play();
                    handleNumberPress("9");
                }
            }
            if(processFlag && depositFlag && !withdrawalFlag)
            {
                n9.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n9.setStyle(originalStyle));
                clickPause.play();
                depositAmt("9");
            }
            else if (processFlag && withdrawalFlag && !depositFlag)
            {
                n9.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n9.setStyle(originalStyle));
                clickPause.play();
                withdrawalAmt("9");
            }

        });

        Button n0 = new Button("0");
        n0.setId("numpad0");
        n0.setPrefWidth(50);
        n0.setPrefHeight(50);
        n0.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        n0.setOnMouseClicked(mouseEvent ->
        {
            if(processFlag)
            {
                if (!depositFlag && !withdrawalFlag)
                {
                    n0.setStyle(clickedStyle);
                    PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                    clickPause.setOnFinished(e -> n0.setStyle(originalStyle));
                    clickPause.play();
                    handleNumberPress("0");
                }
            }
            if(processFlag && depositFlag && !withdrawalFlag)
            {
                n0.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n0.setStyle(originalStyle));
                clickPause.play();
                depositAmt("0");
            }
            else if (processFlag && withdrawalFlag && !depositFlag)
            {
                n0.setStyle(clickedStyle);
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> n0.setStyle(originalStyle));
                clickPause.play();
                withdrawalAmt("0");
            }


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


        //CardSlot Button
        Button cardSlot = new Button();
        cardSlot.setId("cardSlotButton");
        cardSlot.setPrefWidth(150);
        cardSlot.setPrefHeight(20);
        cardSlot.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        cardSlot.setText("Card Slot");
        cardSlot.setFont(new Font(18));
        cardSlot.setTranslateX(245);
        cardSlot.setTranslateY(55);

        // Set the event handler for the cardSlot button
        cardSlot.setOnMousePressed(mouseEvent ->
        {
            cardSlot.setStyle(clickedStyle);
            updateScreenContent("Please leave your ATM card in.", Color.DARKGRAY);
            isHeld = true;
            startHoldTimer();
            PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
            clickPause.setOnFinished(e -> cardSlot.setStyle(originalStyle));
            clickPause.play();
        });

        cardSlot.setOnMouseReleased(mouseEvent ->
        {
            isHeld = false;
            resetHoldTimer();
        });


        //Enter Button
        Button enterButton = new Button();
        enterButton.setId("enterButton");
        enterButton.setPrefWidth(100);
        enterButton.setPrefHeight(20);
        enterButton.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        enterButton.setText("Enter");
        enterButton.setFont(new Font(18));
        enterButton.setTranslateX(270);
        enterButton.setTranslateY(105);
        enterButton.setOnMouseClicked(mouseEvent ->
        {
            if(processFlag)
            {
                enterCount = enterCount + 1;
                if (enterCount >= 2 && depositFlag) {
                    enterButtonPressed = true;
                    depositAmt(depositString);
                    enterButtonPressed = false;
                    processFlag = false;
                } else if (enterCount >= 2 && withdrawalFlag) {
                    enterButtonPressed = true;
                    withdrawalAmt(withdrawalString);
                    enterButtonPressed = false;
                    processFlag = false;

                } else {
                    enterButtonPressed = false;
                }
                enterButton.setStyle(clickedStyle);
                // Reset the button's style back to the original after a brief moment
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> enterButton.setStyle(originalStyle));
                clickPause.play();
                if (count == 4) {
                    count = 0;
                    //check if the correct pin is entered
                    if (pin.equals(correctPin)) {
                        //Call Screen 5 - Transaction Screen
                        updateTransactionScreen("Select transaction:\nbalance >\n\ndeposit >\n\nwithdrawal >",
                                Color.rgb(32, 115, 69));
                        pin = "";
                        PauseTransition pause = new PauseTransition(Duration.seconds(1));
                        pause.play();
                    } else {
                        updateScreenContent("Your PIN is incorrect. \n Please try again.",
                                Color.rgb(32, 115, 69));
                        pin = "";
                        PauseTransition pause = new PauseTransition(Duration.seconds(2));
                        pause.setOnFinished(e -> promptForPin());
                        pause.play();
                    }
                }
            }
        });

        //Clear Button
        Button clearButton = new Button();
        clearButton.setId("clearButton");
        clearButton.setPrefWidth(100);
        clearButton.setPrefHeight(20);
        clearButton.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        clearButton.setText("Clear");
        clearButton.setFont(new Font(18));
        clearButton.setTranslateX(270);
        clearButton.setTranslateY(155);
        // Set the event handler for the clear button
        clearButton.setOnAction(event ->
        {
            if(processFlag)
            {
                clearButton.setStyle(clickedStyle);
                pin = "";
                count = 0;
                // Reset the button's style back to the original after a brief moment
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> clearButton.setStyle(originalStyle));
                clickPause.play();
                promptForPin();
            }
        });

        //Cancel Button
        Button cancelButton = new Button();
        cancelButton.setId("cancelButton");
        cancelButton.setPrefWidth(100);
        cancelButton.setPrefHeight(20);
        cancelButton.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        cancelButton.setText("Cancel");
        cancelButton.setFont(new Font(18));
        cancelButton.setTranslateX(270);
        cancelButton.setTranslateY(205);
        cancelButton.setOnMouseClicked(mouseEvent ->
        {
            enterCount = 0;
            count = 0;
            satmFrame.close();
        });

        //Cash Dispenser Rectangle
        cashDispenser = new Rectangle();
        cashDispenser.setWidth(350);
        cashDispenser.setHeight(30);
        cashDispenser.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        cashDispenser.setArcHeight(20);
        cashDispenser.setArcWidth(20);
        cashDispenser.setTranslateX(-150);
        cashDispenser.setTranslateY(315);
        cashDispenser.setOnMouseClicked(mouseEvent ->
        {
            if(processFlag)
            {
                updateScreenContent("Your balance is ready for printing. Another transaction?",
                        Color.rgb(32, 115, 69));
            }
        });

        //Text for Cash Dispenser
        Text rectText = new Text("Cash Dispenser");
        rectText.setId("cashDispenser");
        rectText.setFont(new Font("Georgia",18));
        rectText.setFill(Color.WHITE);
        rectText.setTranslateX(-150);
        rectText.setTranslateY(315);
        rectText.setOnMouseClicked(mouseEvent ->
        {
            if(processFlag)
            {
                updateScreenContent("Your balance is ready for printing. Another transaction?",
                        Color.rgb(32, 115, 69));
            }
        });

        //Deposit Slot Button
        Button depositSlot = new Button();
        depositSlot.setId("depositSlotButton");
        depositSlot.setPrefWidth(250);
        depositSlot.setPrefHeight(30);
        depositSlot.setStyle("-fx-background-color: #282928; -fx-text-fill: #FFFFFF;");
        depositSlot.setText("Deposit Slot");
        depositSlot.setFont(new Font(18));
        depositSlot.setTranslateX(200);
        depositSlot.setTranslateY(315);
        // Set the event handler for the cardSlot button
        depositSlot.setOnMousePressed(mouseEvent ->
        {
            if(enterCount >= 1)
            {
                processFlag = true;
            }
            if(processFlag)
            {
                depositSlot.setStyle(clickedStyle);
                updateScreenContent("Counting your deposit.\nPlease wait...", Color.DARKGRAY);
                isHeld = true;
                startHoldTimer2();
                PauseTransition clickPause = new PauseTransition(Duration.seconds(0.2));
                clickPause.setOnFinished(e -> cardSlot.setStyle(originalStyle));
                clickPause.play();
            }
        });

        depositSlot.setOnMouseReleased(mouseEvent ->
        {
            isHeld = false;
            resetHoldTimer2();
        });



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

    void withdrawalAmt(String number)
    {
        if(!enterButtonPressed)
        {
            withdrawalString += number;
            numCheck = Double.parseDouble(withdrawalString);
        }
        else
        {
            if(numCheck % 10 == 0)
            {
                withdrawal = Double.parseDouble(withdrawalString);
                if(withdrawal > balance)
                {
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(e -> updateScreenContent("Insufficient Funds!\nPlease enter a new amount.", Color.DARKGRAY));
                    pause.play();


                    // Transition to PIN entry after 5 seconds
                    PauseTransition pause2 = new PauseTransition(Duration.seconds(3.5));
                    pause2.setOnFinished(e -> updateScreenContent("Select transaction:\nbalance >\n\ndeposit >" +
                                    "\n\nwithdrawal >",
                            Color.rgb(32, 115, 69)));
                    pause2.play();
                    withdrawal = 0.0;
                    withdrawalString = "";
                }
                else
                {
                    balance = balance - withdrawal;
                    transactionFlag = true;
                    updateScreenContent("Your balance is being updated. Please take cash from dispenser",
                            Color.rgb(32, 115, 69));

                    // Create a Timeline to change colors
                    Timeline blinkTimeline = new Timeline(
                            new KeyFrame(Duration.seconds(0), e -> cashDispenser.setFill(Color.GREEN)),
                            new KeyFrame(Duration.seconds(.5), e -> cashDispenser.setFill(Color.BLACK))
                    );
                    blinkTimeline.setCycleCount(Timeline.INDEFINITE);
                    blinkTimeline.setAutoReverse(true);
                    // Start the animation
                    blinkTimeline.play();

                    // Stop the blinking after 4 seconds using a PauseTransition
                    PauseTransition stopBlinking = new PauseTransition(Duration.seconds(4));
                    stopBlinking.setOnFinished(event -> blinkTimeline.stop());
                    stopBlinking.play();
                    withdrawalFlag = false;
                    withdrawalString = "";
                }

            }
            else
            {
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(e -> updateScreenContent("Machine can only dispense $10 notes.", Color.DARKGRAY));
                pause.play();


                // Transition to PIN entry after 5 seconds
                PauseTransition pause2 = new PauseTransition(Duration.seconds(3.5));
                pause2.setOnFinished(e -> updateScreenContent("Select transaction:\nbalance >\n\ndeposit >" +
                                "\n\nwithdrawal >",
                        Color.rgb(32, 115, 69)));
                pause2.play();
                withdrawalString = "";
                numCheck = 0.0;
            }



        }

    }

    void printReceipt()
    {
        // Create a new Stage for the pop-up
        Stage receiptStage = new Stage();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String receiptText = "Account: *****0504\n" +
                             "Original Balance: $" + decimalFormat.format(orgBalance) + "\n" +
                             "Deposits: +$" + decimalFormat.format(deposit) + "\n" +
                             "Withdrawals: -$" + decimalFormat.format(withdrawal) + "\n" +
                             "Total Remaining Balance: $" + decimalFormat.format(balance);

        // Make it modal so it blocks interaction with the main application until closed
        receiptStage.initModality(Modality.APPLICATION_MODAL);
        receiptStage.initOwner(receiptWindow); // Set the owner of the pop-up to the main application window
        receiptStage.initStyle(StageStyle.UTILITY); // Give it a 'utility' style

        // Create the content for the pop-up
        VBox content = new VBox(10);
        content.getChildren().add(new Label(receiptText));

        // Optional: Add a button or other controls to the content as needed

        // Set the scene and show the stage
        receiptStage.setScene(new Scene(content, 300, 200)); // Adjust size as needed
        receiptStage.setTitle("*****CUSTOMER RECEIPT*****"); // Set the title of the pop-up window
        receiptStage.showAndWait(); // Show the pop-up and wait for it to be closed before returning to the application

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

    void updateScreenContent(String newText, Color backgroundColor) {
        text.setText(newText); // Update the text
        text.setId("screenContent");
        square.setFill(backgroundColor); // Update the background color of the screen
        // If you have other styles/effects, update them here as needed
    }

    void updateTransactionScreen(String newText, Color backgroundColor) {
        text.setText(newText); // Update the text
        text.setId("transactionScreen");
        text.setTextAlignment(TextAlignment.RIGHT);
        square.setFill(backgroundColor); // Update the background color of the screen
        // If you have other styles/effects, update them here as needed
    }

    void promptForPin() {
        // This will be called after the "Processing..." message
        updateScreenContent("Please enter your PIN\n_ _ _ _", Color.rgb(32, 115, 69)); // Original screen color
    }

    void handleNumberPress(String number)
    {
        if (pin.length() < 4)
        {
            pin += number; // Append the number to the PIN
            updatePinDisplay(); // Update the visual PIN display

        }
    }

    void updatePinDisplay()
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

    void startHoldTimer()
    {
        holdTimer = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            if (isHeld)
            {
                timerDone = true;
                processFlag = true;
                updateScreenContent("Please removed your ATM card.", Color.DARKGRAY);
                // Reset the button's style back to the original after a brief moment
                // Transition to PIN entry after 5 seconds
                PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                pause.setOnFinished(e -> updateScreenContent("Processing...\nPlease Wait", Color.DARKGRAY));
                pause.play();


                // Transition to PIN entry after 5 seconds
                PauseTransition pause2 = new PauseTransition(Duration.seconds(3.5));
                pause2.setOnFinished(e -> promptForPin());
                pause2.play();
            }
        }));
        holdTimer.setCycleCount(1);
        holdTimer.play();


    }

    void resetHoldTimer() {
        if (holdTimer != null && !timerDone) {
            holdTimer.stop();
            holdTimer = null;
            updateScreenContent("Invalid ATM card. It will be retained.", Color.DARKGRAY);
            // Reset the button's style back to the original after a brief moment
            PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
            pause.setOnFinished(e -> {
                String welcomeMessage = "Welcome to\n\nO.I.G. Credit Union\n\nPlease insert your Platinum ATM card";
                updateScreenContent(welcomeMessage, Color.rgb(32, 115, 69));
            });
            pause.play();
        }
        timerDone = false;
        //processFlag = false;
    }

    void startHoldTimer2()
    {
        holdTimer = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            if (isHeld)
            {
                timerDone = true;
                updateScreenContent("Deposit Received!", Color.DARKGRAY);
                // Reset the button's style back to the original after a brief moment
                // Transition to PIN entry after 5 seconds
                PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                pause.setOnFinished(e -> updateScreenContent("Processing...\nPlease Wait", Color.DARKGRAY));
                pause.play();
                
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                PauseTransition pause2 = new PauseTransition(Duration.seconds(2.5));
                String finalBalanceString = decimalFormat.format(balance);
                pause2.setOnFinished(e -> updateScreenContent("Your new balance is:\n\n$" + finalBalanceString +
                                "\n\nAnother transaction?",
                        Color.rgb(32, 115, 69)));
                pause2.play();
            }

        }));
        holdTimer.setCycleCount(1);
        holdTimer.play();


    }

    void resetHoldTimer2() {
        if (holdTimer != null && !timerDone) {
            holdTimer.stop();
            holdTimer = null;
            updateScreenContent("Temporarily unable to process deposits.\nAnother transaction?", Color.DARKGRAY);
            // Reset the button's style back to the original after a brief moment
            PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
            pause.setOnFinished(e ->
            {

            });
            pause.play();
            deposit = 0.0;
            depositString = "";
        }
        timerDone = false;

    }

    void depositAmt(String num)
    {
        if(!enterButtonPressed)
        {
            depositString += num;
        }
        else
        {
            deposit = Double.parseDouble(depositString);
            balance = balance + deposit;
            updateScreenContent("Please insert deposit into deposit slot", Color.rgb(32, 115, 69));
            depositFlag = false;
            transactionFlag = true;
            depositString = "";
        }

    }



    public static void main (String[] args)
    {
        launch(args);
    }
}
