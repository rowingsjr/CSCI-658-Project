package com.example.csci658project;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SATMApplicationTest {

    private SATMApplication app;

    @BeforeEach
    public void setUp() {
        app = new SATMApplication();
        app.text = new Text(); // Initialize the text field
        app.square = new Rectangle();
    }

//    @Test
//    public void testWithdrawalAmt()
//    {
//        // Test case 1: enterButtonPressed is false
//        app.enterButtonPressed = false;
//        app.withdrawalAmt("100");
//        assertEquals("100", app.withdrawalString);
//        assertEquals(100.0, app.numCheck, 0.01);
//
//
//        // Test case 3: enterButtonPressed is true and withdrawal amount is valid
//        app.enterButtonPressed = true;
//        app.withdrawalString = "50";
//        app.balance = 100;
//        app.withdrawalAmt("0");
//        assertEquals(50.0, app.balance, 0.01);
//        assertTrue(app.transactionFlag);
//    }


    @Test
    public void testUpdateScreenContent() {
        // Call the method under test
        app.updateScreenContent("Test", Color.RED);

        // Assert the expected results
        assertEquals("Test", app.text.getText());
        assertEquals(Color.RED, app.square.getFill());
    }


    @Test
    public void testUpdateTransactionScreen() {
        app.updateTransactionScreen("Test", Color.BLUE);
        assertEquals("Test", app.text.getText());
        assertEquals(Color.BLUE, app.square.getFill());
    }

    @Test
    public void testPromptForPin() {
        app.promptForPin();
        assertEquals("Please enter your PIN\n_ _ _ _", app.text.getText());
        assertEquals(Color.rgb(32, 115, 69), app.square.getFill());
    }

    @Test
    public void testHandleNumberPress() {
        app.handleNumberPress("1");
        assertEquals("1", app.pin);
    }

    @Test
    public void testResetHoldTimer() {
        app.resetHoldTimer();
        assertNull(app.holdTimer);
    }

    @Test
    public void testResetHoldTimer2()
    {
        app.resetHoldTimer2();
        assertNull(app.holdTimer);
    }

}
