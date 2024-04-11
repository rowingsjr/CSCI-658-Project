package com.example.csci658project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobotInterface;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import javafx.scene.input.MouseButton;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.util.NodeQueryUtils.isVisible;
import static org.testfx.util.WaitForAsyncUtils.waitFor;
import org.testfx.util.WaitForAsyncUtils;
import java.util.concurrent.TimeUnit;
import javafx.scene.input.MouseButton;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.DebugUtils.informedErrorMessage;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;

import java.time.Duration;



@ExtendWith(ApplicationExtension.class)
public class SATMApplicationTestFX extends ApplicationTest
{
    // Override the start method to set up your application's stage
    @Start
    public void start(Stage stage) throws Exception {
        // Create an instance of your application class
        SATMApplication satmApplication = new SATMApplication();

        // Call the start method of your application class with the test stage
        satmApplication.start(stage);
    }

//    @Test
//    public void testValidATMCardInsertion() {
//        // Verify the welcome message is displayed
//        verifyThat("#WelcomeMessage", node -> {
//            if (node instanceof Text) {
//                Text textNode = (Text) node;
//                return "Welcome to\n\nO.I.G. Credit Union\n\nPlease insert your Platinum ATM card".equals(textNode.getText());
//            }
//            return false;
//        });
//
//        moveTo("#cardSlotButton");
//
//        press(MouseButton.PRIMARY);
//
//        // Pause for 5 seconds to simulate holding the button down
//        WaitForAsyncUtils.sleep(10, TimeUnit.SECONDS);
//
//        // Sleep to hold the click for 5 seconds
//        //sleep(10000);
//
//        // Release the mouse button
//        release(MouseButton.PRIMARY);
//
//        // Now check if the PIN prompt is displayed
//        verifyThat("#screenContent", node -> {
//            if (node instanceof Text) {
//                Text textNode = (Text) node;
//                return "Please enter your PIN\n_ _ _ _".equals(textNode.getText());
//            }
//            return false;
//        });
//
//    }
//
//    @Test
//    public void testInvalidATMCardInsertion() {
//
//        moveTo("#cardSlotButton");
//
//        press(MouseButton.PRIMARY);
//
//        // Pause for 5 seconds to simulate holding the button down
//        WaitForAsyncUtils.sleep(3, TimeUnit.SECONDS);
//
//        // Release the mouse button
//        release(MouseButton.PRIMARY);
//
//        // Now check if the PIN prompt is displayed
//        verifyThat("#screenContent", node -> {
//            if (node instanceof Text) {
//                Text textNode = (Text) node;
//                return "Invalid ATM card. It will be retained.".equals(textNode.getText());
//            }
//            return false;
//        });
//
//    }

//    @Test
//    public void correctPinEntered() {
//
//        moveTo("#cardSlotButton");
//
//        press(MouseButton.PRIMARY);
//
//        // Pause for 5 seconds to simulate holding the button down
//        WaitForAsyncUtils.sleep(10, TimeUnit.SECONDS);
//
//
//        // Release the mouse button
//        release(MouseButton.PRIMARY);
//
//        clickOn("#numpad1");
//        clickOn("#numpad9");
//        clickOn("#numpad0");
//        clickOn("#numpad6");
//
//        // After pressing the numbers, simulate clicking the Enter button
//        verifyThat("#enterButton", isEnabled());
//        clickOn("#enterButton");
//
//        WaitForAsyncUtils.waitForFxEvents();
//
//        // Now check if the PIN prompt is displayed
//        verifyThat("#transactionScreen", node -> {
//            if (node instanceof Text) {
//                Text textNode = (Text) node;
//                return "Select transaction:\nbalance >\n\ndeposit >\n\nwithdrawal >".equals(textNode.getText());
//            }
//            return false;
//        });
//
//    }

//    @Test
//    public void incorrectPinEntered() {
//
//        moveTo("#cardSlotButton");
//
//        press(MouseButton.PRIMARY);
//
//        // Pause for 5 seconds to simulate holding the button down
//        WaitForAsyncUtils.sleep(10, TimeUnit.SECONDS);
//
//        // Release the mouse button
//        release(MouseButton.PRIMARY);
//
//        clickOn("#numpad2");
//        clickOn("#numpad8");
//        clickOn("#numpad1");
//        clickOn("#numpad4");
//
//        // After pressing the numbers, simulate clicking the Enter button
//        verifyThat("#enterButton", isEnabled());
//        clickOn("#enterButton");
//
//        WaitForAsyncUtils.waitForFxEvents();
//
//        // Now check if the PIN prompt is displayed
//        verifyThat("#screenContent", node -> {
//            if (node instanceof Text) {
//                Text textNode = (Text) node;
//                return "Your PIN is incorrect. \n Please try again.".equals(textNode.getText());
//            }
//            return false;
//        });
//
//    }

//    @Test
//    public void balanceInquiry() {
//
//        moveTo("#cardSlotButton");
//
//        press(MouseButton.PRIMARY);
//
//        // Pause for 5 seconds to simulate holding the button down
//        WaitForAsyncUtils.sleep(10, TimeUnit.SECONDS);
//
//        // Release the mouse button
//        release(MouseButton.PRIMARY);
//
//        clickOn("#numpad1");
//        clickOn("#numpad9");
//        clickOn("#numpad0");
//        clickOn("#numpad6");
//
//        // After pressing the numbers, simulate clicking the Enter button
//        verifyThat("#enterButton", isEnabled());
//        clickOn("#enterButton");
//
//        WaitForAsyncUtils.waitForFxEvents();
//
//        verifyThat("#B6", isEnabled());
//        clickOn("#B6");
//
//
//
//        // Now check if the PIN prompt is displayed
//        verifyThat("#screenContent", node -> {
//            if (node instanceof Text) {
//                Text textNode = (Text) node;
//                return textNode.getText().equals("Balance is:\n$3,500.00");
//            }
//            return false;
//        });
//
//    }

    @Test
    public void deposit()
    {
        //insert card
        moveTo("#cardSlotButton");

        press(MouseButton.PRIMARY);

        // Pause for 10 seconds to simulate holding the button down
        WaitForAsyncUtils.sleep(10, TimeUnit.SECONDS);

        // Release the mouse button
        release(MouseButton.PRIMARY);

        //input pin
        clickOn("#numpad1");
        clickOn("#numpad9");
        clickOn("#numpad0");
        clickOn("#numpad6");

        // After pressing the numbers, simulate clicking the Enter button
        verifyThat("#enterButton", isEnabled());
        clickOn("#enterButton");

        WaitForAsyncUtils.waitForFxEvents();

        //press deposit button
        verifyThat("#B7", isEnabled());
        clickOn("#B7");

        WaitForAsyncUtils.sleep(1, TimeUnit.SECONDS);

        // verify deposit screen
        verifyThat("#screenContent", node -> {
            if (node instanceof Text) {
                Text textNode = (Text) node;
                return textNode.getText().equals("Enter amount you want to deposit.");
            }
            return false;
        });

        WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);

        //enter the deposit
        clickOn("#numpad1");
        clickOn("#numpad9");
        clickOn("#numpad0");

        // hit enter
        verifyThat("#enterButton", isEnabled());
        clickOn("#enterButton");

        WaitForAsyncUtils.sleep(1, TimeUnit.SECONDS);

        //verify insert deposit
        verifyThat("#screenContent", node -> {
            if (node instanceof Text) {
                Text textNode = (Text) node;
                return textNode.getText().equals("Please insert deposit into deposit slot");
            }
            return false;
        });

        //insert deposit
        verifyThat("#depositSlotButton", isEnabled());
        moveTo("#depositSlotButton");
        press(MouseButton.PRIMARY);

        WaitForAsyncUtils.sleep(7, TimeUnit.SECONDS);

        // Release the mouse button
        release(MouseButton.PRIMARY);

        verifyThat("#screenContent", node -> {
            if (node instanceof Text) {
                Text textNode = (Text) node;
                return textNode.getText().equals("Counting your deposit.\nPlease wait...");
            }
            return false;
        });

        WaitForAsyncUtils.sleep(1, TimeUnit.SECONDS);

        verifyThat("#screenContent", node -> {
            if (node instanceof Text) {
                Text textNode = (Text) node;
                return textNode.getText().equals("Deposit Received!");
            }
            return false;
        });

        WaitForAsyncUtils.sleep(1, TimeUnit.SECONDS);

        verifyThat("#screenContent", node -> {
            if (node instanceof Text) {
                Text textNode = (Text) node;
                return textNode.getText().equals("Processing...\nPlease Wait");
            }
            return false;
        });

        WaitForAsyncUtils.sleep(1, TimeUnit.SECONDS);

        verifyThat("#screenContent", node -> {
            if (node instanceof Text) {
                Text textNode = (Text) node;
                return textNode.getText().equals("Your new balance is:\n\n$3690.00\n\nAnother transaction?");
            }
            return false;
        });

    }

    private void showNextTestPopup(String nextTestName, Runnable nextTest) {
        // Pause the test thread
        WaitForAsyncUtils.waitForFxEvents();

        // Create the stage for the pop-up
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);

        // Create UI components
        VBox layout = new VBox(10);
        Text text = new Text("Next test: " + nextTestName);
        Button nextButton = new Button("Next");

        nextButton.setOnAction(event -> {
            // Run the next test when Next is clicked
            nextTest.run();
            popupStage.close();
        });

        layout.getChildren().addAll(text, nextButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 200);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }



//    @Test
//    public void testCardInsertion() throws Exception {
//        // Wait until the cardSlotButton is visible and ready to be clicked.
//        WaitForAsyncUtils.waitForFxEvents();
//
//        // Click on the cardSlotButton.
//        clickOn("#cardSlotButton");
//    }




    // More tests go here
}
